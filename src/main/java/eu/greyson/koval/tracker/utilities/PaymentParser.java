package eu.greyson.koval.tracker.utilities;

import eu.greyson.koval.tracker.model.Payment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * PaymentParser contains methods for parsing of payments from an initial file and from the one line of user input.
 */
public class PaymentParser {

    /** Separator which is used between currency and amount */
    private static final String PAYMENT_PARTS_SEPARATOR = " ";

    /**
     * Parses payment one line of payment record
     * @param line A line containing payment record
     * @return New Payment object if the given payment record is valid; null otherwise
     */
    public static Payment parsePaymentLine(String line) {
        Payment result = null;

        if (line != null && !line.isEmpty()) {

            // Split payment record to elements
            String[] paymentElements = line.split(PAYMENT_PARTS_SEPARATOR);
            if (paymentElements != null && paymentElements.length == 2) {

                // Check validity of payment's elements
                if(PaymentValidator.isCurrencyValid(paymentElements[0])) {
                    if (PaymentValidator.isAmountValid(paymentElements[1])) {

                        // Create payment
                        result = new Payment(paymentElements[0], new BigDecimal(paymentElements[1]));
                    } else {
                        System.err.println("Amount is not valid.");
                    }
                } else {
                    System.err.println("Currency is not valid.");
                }
            } else {
                System.err.println("Invalid payment format");
            }
        }

        return result;
    }

    /**
     * Parses payments from the file.
     * Invalid payments are ignored.
     * @param file File with payments
     * @return A list of all valid payments from the given file
     */
    public static List<Payment> parsePaymentsFile(String file) {
        List<Payment> result = new ArrayList<>();

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                // Read all payments from file
                String line = null;
                while ((line = reader.readLine()) != null) {

                    // Parse payment line
                    Payment payment = parsePaymentLine(line);
                    if (payment != null) {

                        // Add payment to storage
                        result.add(payment);
                    }
                }
            } catch (IOException e) {
                System.err.println("Unable to load payments from file (" + file + ")");
            }
        }

        return result;
    }
}
