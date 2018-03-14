package eu.greyson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class contains payment data and logic for payment manipulation.
 */
public class Payments {

    /** Payments validity regex */
    private final String CURRENCY_VALIDITY_REGEX = "^[A-Z]{3}$";

    /** Storage of payments */
    private ConcurrentHashMap<String, BigDecimal> payments = new ConcurrentHashMap<>();

    /**
     * Loads payments from file
     * @param file File with payments
     */
    public void loadPaymentsFromFile(String file) {
        if (file != null) {

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                // Read all payments record
                String record = null;
                while ((record = reader.readLine()) != null) {
                    addPaymentRecord(record);
                }
            } catch (IOException e) {
                System.err.println("Unable to load payments from file (" + file + ")");
            }
        }
    }

    /**
     * Inserts a payment record
     * @param record Record to insert
     */
    public void addPaymentRecord(String record) {
        if (record != null && !record.isEmpty()) {

            // Split record to elements
            String[] elements = record.trim().split(" ");
            if (elements != null && elements.length == 2) {

                // Check currency validity
                String currency = getValidCurrency(elements[0]);

                // Convert value
                BigDecimal value = convertPaymentValue(elements[1]);

                // Store payment
                if (currency != null && value != null) {
                    addPayment(currency, value);
                }
            } else {
                System.err.println("Invalid payment record (" + record + "). Record is ignored. ");
            }
        }
    }

    /**
     * Checks currency validity
     * @param currency Currecy to check
     * @return currency (if it's valid); false otherwise
     */
    private String getValidCurrency(String currency) {
        String result = null;

        if (isCurrencyValid(currency)) {
            result = currency;
        } else {
            System.err.println("Invalid currency (" + currency + "). Record is ignored.");
        }

        return result;
    }

    /**
     * Coverts payment value from string to number
     * @param value Value to convert
     * @return converted value or null
     */
    private BigDecimal convertPaymentValue(String value) {
        BigDecimal result = null;

        try {
            result = new BigDecimal(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid payment value (" + value + ")");
        }

        return result;
    }

    /**
     * Inserts a payment
     * @param currency Payment currency
     * @param value Payment value
     */
    private void addPayment(String currency, BigDecimal value) {
        if (payments != null) {

            if (payments.containsKey(currency)) {

                // Calculate new payment value
                BigDecimal newValue = payments.get(currency).add(value);
                if (newValue.compareTo(BigDecimal.ZERO) == 0) {

                    // Remove record from storage
                    payments.remove(currency);
                } else {

                    // Update payment value
                    payments.replace(currency, payments.get(currency).add(value));
                }
            } else {
                // Add new currency and value
                payments.put(currency, value);
            }
        }
    }

    /**
     * Checks if a currency is valid.
     * Currency is valid if it consists from 3 upper case letters
     * @param currency Currency to check
     * @return true if currency is valid; false otherwise
     */
    private boolean isCurrencyValid(String currency) {
        boolean result = false;

        if (currency != null && currency.matches(CURRENCY_VALIDITY_REGEX)) {
            result = true;
        }

        return result;
    }

    /**
     * Returns all payment records
     */
    public Set<String> getPaymentsForPrint() {
        Set<String> result = new LinkedHashSet<>();

        if (payments != null && !payments.isEmpty()) {
            for (Map.Entry<String, BigDecimal> entry: payments.entrySet()) {
                result.add(entry.getKey() + " " + entry.getValue());
            }
        }

        return result;
    }
}
