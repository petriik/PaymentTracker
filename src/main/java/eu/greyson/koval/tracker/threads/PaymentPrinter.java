package eu.greyson.koval.tracker.threads;

import eu.greyson.koval.tracker.data.PaymentsStorage;
import eu.greyson.koval.tracker.model.Payment;
import eu.greyson.koval.tracker.utilities.PaymentCalculator;

import java.math.BigDecimal;
import java.util.List;

/**
 * PaymentPrinter prints payment sums to the standard output in variable intervals.
 *
 * Sometimes there can be a conflict between PaymentReader and PaymentPrinter (Printing of output and reading of user input at the same time).
 * PaymentPrinter tries to reduce number of these conflicts by appending of the output right behind the user input after specified time period.
 *
 * At first, PaymentPrinter is a sleeping for a while ({@link PaymentPrinter#SLEEPING_INTERVAL} ). Output cannot be printed at this moment.
 * After that PaymentPrinter is a waiting for a notification of suitable time for printing for a specified time period ({@link PaymentPrinter#WAITING_INTERVAL} ).
 * If notification arrives at this interval, output will be printed immediately.
 * If notification doesn't arrive, output will be printed at the and of this interval.
 */
public class PaymentPrinter extends BasicPaymentThread {

    /** A number of milliseconds for which thread is sleeping and printing of the output is not possible */
    private final long SLEEPING_INTERVAL = 50000;

    /** A number of milliseconds for which thread is waiting for a notification of suitable time for printing */
    private long WAITING_INTERVAL = 20000;

    /**
     * PaymentPrinter Constructor
     * @param payments Shared payments storage
     * @param printRecommender Synchronization object
     */
    public PaymentPrinter(PaymentsStorage payments, Object printRecommender) {
        super(payments, printRecommender);
    }

    /**
     * Prints states of payment accounts to the standard output in the variable intervals.
     */
    @Override
    public void run() {
        try {
            while (true) {

                // Sleep - printing is not allowed now
                Thread.sleep(SLEEPING_INTERVAL);

                // Wait for notification of suitable time to print
                waitsForSuitableTimeToPrintNotification();

                // Print payments statistics
                printCurrencySums();
            }
        } catch (InterruptedException e) {
            // Nothing to do, just quit
        }
    }

    /**
     * Waits for notification of suitable time for printing from reader
     * @throws InterruptedException It's thrown if thread is interrupted during waiting
     */
    private synchronized void waitsForSuitableTimeToPrintNotification() throws InterruptedException {
        synchronized (printRecommender) {
            printRecommender.wait(WAITING_INTERVAL);
        }
    }

    /**
     * Prints all payments to standard output
     */
    private void printCurrencySums() {
        if (payments != null) {

            System.out.println();
            System.out.println("----------------------------------");

            // Calculates sums of amounts for each currency
            List<Payment> currencySums = PaymentCalculator.sumAmountsForEachCurrency(payments.getPayments());
            if (currencySums != null) {

                // Filter out zero sums and print it to the output
                currencySums.stream()
                        .filter(payment -> payment.getAmount().compareTo(BigDecimal.ZERO) != 0)
                        .forEach(payment -> System.out.println(payment));
            }

            System.out.println("----------------------------------");
            System.out.println();
        }
    }
}

