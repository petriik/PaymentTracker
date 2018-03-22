package eu.greyson.koval.tracker.threads;

import eu.greyson.koval.tracker.data.PaymentsStorage;
import eu.greyson.koval.tracker.model.Payment;
import eu.greyson.koval.tracker.utilities.PaymentParser;

import java.util.Scanner;


/**
 * PaymentReader is reading payments from the standard input and store them to the shared storage.
 *
 * It also recommends a suitable time for printing to PaymentReader by calling notify() on the shared object.
 */
public class PaymentReader extends BasicPaymentThread {

    /** Command for end of processing */
    private final static String QUIT_COMMAND = "quit";

    /** Payment printer thread */
    private Thread printer = null;


    /**
     * PaymentReader Constructor
     * @param payments Shared payments storage
     * @param printer Printer payment thread
     * @param printRecommender Synchronization object
     */
    public PaymentReader(PaymentsStorage payments, Thread printer, Object printRecommender) {
        super(payments, printRecommender);
        this.printer = printer;
    }

    /**
     * Reads payments from the standard input and store them to the storage.
     */
    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            // Read next line
            String line = scanner.nextLine();

            // Check if program should quit
            if (QUIT_COMMAND.equals(line)) {
                break;
            }

            // Add payment
            Payment payment = PaymentParser.parsePaymentLine(line);
            if (payment != null) {
                payments.addPayment(payment);
            }

            // Notify printer
            notifySuitableTimeToPrint();
        }

        // Stop printer thread
        printer.interrupt();
    }

    /**
     * Notifies to printer that now is the right time to print the results
     */
    private void notifySuitableTimeToPrint() {
        synchronized (printRecommender) {
            printRecommender.notify();
        }
    }
}

