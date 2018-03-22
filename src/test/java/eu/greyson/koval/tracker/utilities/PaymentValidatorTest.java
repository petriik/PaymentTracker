package eu.greyson.koval.tracker.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    @Test
    void testValidCurrencies() {
        assertTrue(PaymentValidator.isCurrencyValid("CZK"));
        assertTrue(PaymentValidator.isCurrencyValid("USD"));
        assertTrue(PaymentValidator.isCurrencyValid("RUB"));
        assertTrue(PaymentValidator.isCurrencyValid("AAA"));
    }

    @Test
    void testInvalidCurrencies() {

        assertFalse(PaymentValidator.isCurrencyValid(null));
        assertFalse(PaymentValidator.isCurrencyValid(""));
        assertFalse(PaymentValidator.isCurrencyValid("CZ K"));
        assertFalse(PaymentValidator.isCurrencyValid("czk"));
        assertFalse(PaymentValidator.isCurrencyValid("Czk"));
        assertFalse(PaymentValidator.isCurrencyValid("US"));
        assertFalse(PaymentValidator.isCurrencyValid("A"));
        assertFalse(PaymentValidator.isCurrencyValid("AAAA"));
    }

    @Test
    void testValidAmounts() {

        assertTrue(PaymentValidator.isAmountValid("1"));
        assertTrue(PaymentValidator.isAmountValid("1000"));
        assertTrue(PaymentValidator.isAmountValid("-10000"));
        assertTrue(PaymentValidator.isAmountValid("1.00"));
        assertTrue(PaymentValidator.isAmountValid("-9999.99"));
    }

    @Test
    void testInvalidAmounts() {

        assertFalse(PaymentValidator.isAmountValid(null));
        assertFalse(PaymentValidator.isAmountValid(""));
        assertFalse(PaymentValidator.isAmountValid("AA"));
        assertFalse(PaymentValidator.isAmountValid("A3000"));
        assertFalse(PaymentValidator.isAmountValid("-3000A"));
        assertFalse(PaymentValidator.isAmountValid("99cd565"));
    }
}