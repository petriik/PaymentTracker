package eu.greyson;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentsTest {

    /** File with valid payments */
    private static final String VALID_PAYMENTS = "src/test/resources/valid_payments.txt";

    /** File with invalid payments */
    private static final String INVALID_PAYMENTS = "src/test/resources/invalid_payments.txt";


    @org.junit.jupiter.api.Test
    void loadValidPaymentsFromFile() {
        Payments payments = new Payments();

        // Load payments from file
        payments.loadPaymentsFromFile(VALID_PAYMENTS);

        // Check results
        assertTrue(payments.getPaymentsForPrint().contains("EUR 300"));
        assertTrue(payments.getPaymentsForPrint().contains("CZK 1980"));
        assertTrue(payments.getPaymentsForPrint().contains("USD 550"));
        assertTrue(payments.getPaymentsForPrint().size() == 3);
    }

    @org.junit.jupiter.api.Test
    void loadInvalidPaymentsFromFile() {
        Payments payments = new Payments();

        // Load payments from file
        payments.loadPaymentsFromFile(INVALID_PAYMENTS);

        // Check results
        assertTrue(payments.getPaymentsForPrint().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testPayments() {
        Payments payments = new Payments();
        assertTrue(payments.getPaymentsForPrint().isEmpty());

        payments.addPaymentRecord("CZK 200");
        assertTrue(payments.getPaymentsForPrint().contains("CZK 200"));
        assertTrue(payments.getPaymentsForPrint().size() == 1);

        payments.addPaymentRecord("CZK 500");
        assertTrue(payments.getPaymentsForPrint().contains("CZK 700"));
        assertTrue(payments.getPaymentsForPrint().size() == 1);

        payments.addPaymentRecord("CZK -700");
        assertTrue(payments.getPaymentsForPrint().size() == 0);

        payments.addPaymentRecord("CZK -X450");
        assertTrue(payments.getPaymentsForPrint().size() == 0);

        payments.addPaymentRecord("CZK -500");
        assertTrue(payments.getPaymentsForPrint().contains("CZK -500"));
        assertTrue(payments.getPaymentsForPrint().size() == 1);
    }
}

