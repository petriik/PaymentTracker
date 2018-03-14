package eu.greyson;

/**
 * Main class of the PaymentTracker project
 */
public class PaymentTracker {

    /**
     * Main project method. Creates and runs payment reader and printer.
     * @param args File with payment can be provided as optional parameter.
     */
    public static void main(String[] args) {

        // Create payments storage
        Payments payments = new Payments();

        // Check if payment file is available
        if (args != null && args.length > 0) {

            // Load payments from file
            payments.loadPaymentsFromFile(args[0]);
        }

        // Create and start printer
        Thread printer = new Thread(new PaymentPrinter(payments,60000));
        printer.start();

        // Create and start reader
        new Thread(new PaymentReader(payments, printer)).start();
    }
}
