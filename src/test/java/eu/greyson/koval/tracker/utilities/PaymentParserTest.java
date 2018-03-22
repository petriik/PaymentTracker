package eu.greyson.koval.tracker.utilities;

import eu.greyson.koval.tracker.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaymentParserTest {

    /** File with valid payments */
    private static final String VALID_PAYMENTS = "src/test/resources/valid_payments.txt";

    /** File with invalid payments */
    private static final String INVALID_PAYMENTS = "src/test/resources/invalid_payments.txt";

    /** File with invalid payments */
    private static final String INVALID_FILE_NAME = "src/test/resources/invalid_file_name.txt";


    @Test
    void parseValidPaymentLines() {

        Payment payment1 = PaymentParser.parsePaymentLine("CZK 1000000");
        assertTrue("CZK".equals(payment1.getCurrency()) && new BigDecimal("1000000").equals(payment1.getAmount()));

        Payment payment2 = PaymentParser.parsePaymentLine("USD 45555.99");
        assertTrue("USD".equals(payment2.getCurrency()) && new BigDecimal("45555.99").equals(payment2.getAmount()));

        Payment payment3 = PaymentParser.parsePaymentLine("RUB 0");
        assertTrue("RUB".equals(payment3.getCurrency()) && new BigDecimal("0").equals(payment3.getAmount()));

        Payment payment4 = PaymentParser.parsePaymentLine("AAA -450");
        assertTrue("AAA".equals(payment4.getCurrency()) && new BigDecimal("-450").equals(payment4.getAmount()));
    }

    @Test
    void parseInvalidPaymentLines() {

        Payment payment1 = PaymentParser.parsePaymentLine(null);
        assertTrue(payment1 == null);

        Payment payment2 = PaymentParser.parsePaymentLine(" ");
        assertTrue(payment2 == null);

        Payment payment3 = PaymentParser.parsePaymentLine("CZK  -50");
        assertTrue(payment3 == null);

        Payment payment4 = PaymentParser.parsePaymentLine("czk 500");
        assertTrue(payment4 == null);

        Payment payment5 = PaymentParser.parsePaymentLine("AAAA -450");
        assertTrue(payment5 == null);
    }

    @Test
    void parseValidPaymentsFile() {

        // Try to load payments from invalid file name
        List<Payment> payments = PaymentParser.parsePaymentsFile(INVALID_FILE_NAME);
        assertTrue(payments.isEmpty());
    }

    @Test
    void parseInvalidPaymentsFile() {

        // Load invalid payments from file
        List<Payment> payments = PaymentParser.parsePaymentsFile(INVALID_PAYMENTS);
        assertTrue(payments.isEmpty());
    }

    @Test
    void parsePaymentsInvalidFile() {

        // Load valid payments from file
        List<Payment> payments = PaymentParser.parsePaymentsFile(VALID_PAYMENTS);

        // Create expected result set
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(new Payment("EUR", new BigDecimal(100)));
        expectedPayments.add(new Payment("CZK", new BigDecimal("2000.11")));
        expectedPayments.add(new Payment("USD", new BigDecimal(-500)));
        expectedPayments.add(new Payment("HKD", new BigDecimal(0)));
        expectedPayments.add(new Payment("EUR", new BigDecimal("-299.50")));
        expectedPayments.add(new Payment("USD", new BigDecimal(50123456)));
        expectedPayments.add(new Payment("CZK", new BigDecimal(1)));

        // Check results
        assertTrue(expectedPayments.size() == 7);
        payments.forEach(payment -> {
            assertTrue(PaymentTestUtilities.containsPayment(expectedPayments, payment));
        });
    }

}