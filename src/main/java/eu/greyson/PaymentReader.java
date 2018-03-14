package eu.greyson;

import java.util.Scanner;

/**
 * Runnable class which is reading payment record from the standard input and store them to the shared storage
 */
public class PaymentReader implements Runnable {

    /** Command for end of processing */
    private final static String QUIT_COMMAND = "quit";

    /** Payment printer thread */
    private Thread printer = null;

    /** Storage of Payments */
    private Payments payments = null;

    /**
     * Constructor
     * @param payments Payments storage
     * @param printer Printer payment thread
     */
    public PaymentReader(Payments payments, Thread printer) {
        this.payments = payments;
        this.printer = printer;
    }

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
            payments.addPaymentRecord(line);
        }

        // Stop printer thread
        printer.interrupt();
    }
}
