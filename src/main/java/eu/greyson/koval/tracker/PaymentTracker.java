package eu.greyson.koval.tracker;

import eu.greyson.koval.tracker.data.PaymentsStorage;
import eu.greyson.koval.tracker.data.impl.PaymentsStorageImpl;
import eu.greyson.koval.tracker.model.Payment;
import eu.greyson.koval.tracker.threads.PaymentPrinter;
import eu.greyson.koval.tracker.threads.PaymentReader;
import eu.greyson.koval.tracker.utilities.PaymentParser;

import java.util.List;

/**
 * Main class of the PaymentTracker application.
 * Application reads payments from the standard input and prints cumulative sums of these payments to the standard output.
 * It tries to prevent to read/write conflicts by appending of the output right behind some of the user inputs.
 * For more information about these prevention of conflicts see ({@link PaymentPrinter})
 */
public class PaymentTracker {

    /**
     * Main project method. Creates and runs payment reader and printer.
     * Application has only one optional parameter - initial file with payments.
     * @param args File with payment can be provided as an optional parameter.
     */
    public static void main(String[] args) {

        // Create payments storage
        PaymentsStorage payments = new PaymentsStorageImpl();

        // Check if payment file is available
        if (args != null && args.length > 0) {

            // Load payments from file
            loadPaymentsFromFile(payments, args[0]);
        }

        // Create and start printer
        Object printRecommender = new Integer(1);
        PaymentPrinter printer = new PaymentPrinter(payments, printRecommender);
        printer.start();

        // Create and start reader
        new PaymentReader(payments, printer, printRecommender).start();
    }

    /**
     * Loads payments from file to the payments storage
     * @param paymentsStorage Payments storage
     * @param file File with payments
     */
    private static void loadPaymentsFromFile(PaymentsStorage paymentsStorage, String file) {
        if (paymentsStorage != null && file != null) {

            // Obtain all payments from file
            List<Payment> payments = PaymentParser.parsePaymentsFile(file);
            if (payments != null) {

                // Insert payments to the storage
                paymentsStorage.addPayments(payments);
            }
        }
    }
}
