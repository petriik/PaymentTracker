package eu.greyson.koval.tracker.utilities;

import java.math.BigDecimal;

/**
 * PaymentValidator validates payment elements like currencies amounts.
 */
public class PaymentValidator {

    /** Currency validation regex */
    private final static String CURRENCY_VALIDITY_REGEX = "^[A-Z]{3}$";

    /**
     * Checks validity of payment's currency
     * Currency is valid if it consists from 3 upper case letters
     * @param currency Currency to check
     * @return true if currency is valid; false otherwise
     */
    public static boolean isCurrencyValid(String currency) {
        boolean result = false;

        if (currency != null && currency.matches(CURRENCY_VALIDITY_REGEX)) {
            result = true;
        }

        return result;
    }

    /**
     * Checks validity of payment's amount
     * @param amount Payment's amount to check
     * @return true if amount is valid; false otherwise
     */
    public static boolean isAmountValid(String amount) {
        boolean result = false;

        if (amount != null) {

            // TODO better validation without creating of object
            try {
                new BigDecimal(amount);
                result = true;
            } catch (NumberFormatException e) {}
        }

        return result;
    }
}
