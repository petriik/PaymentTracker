package eu.greyson;

/**
 * Runnable class which prints payment records to the standard output in regular intervals.
 */
public class PaymentPrinter implements Runnable {

    /** Storage of Payments */
    private Payments payments = null;

    /** Number of milliseconds fro payment printing */
    private long printingInterval = 0;

    /**
     * Constructor
     * @param payments Shared payment storage
     * @param printingInterval Interval for printing of payments in milliseconds
     */
    public PaymentPrinter(Payments payments, long printingInterval) {
        this.payments = payments;
        this.printingInterval = printingInterval;
    }

    public void run() {
        try {
            while (true) {

                // Print payments sum to the stream
                printPayments();

                // Sleep for an interval
                Thread.sleep(printingInterval);
            }
        } catch (InterruptedException e) {
            // Nothing to do, just quit
        }
    }

    /**
     * Prints all payments to standard output
     */
    private void printPayments() {

        if (payments != null) {

            // Print all payments
            payments.getPaymentsForPrint().forEach(payment -> System.out.println(payment));
        }
    }
}
