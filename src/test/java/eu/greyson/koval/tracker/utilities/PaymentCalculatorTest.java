package eu.greyson.koval.tracker.utilities;

import eu.greyson.koval.tracker.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCalculatorTest {


    @Test
    void simpleSumAmountsTest() {

        // Create payments list
        List<Payment> payments = new ArrayList<>();

        payments.add(new Payment("EUR", new BigDecimal(100)));
        payments.add(new Payment("EUR", new BigDecimal(550)));
        payments.add(new Payment("EUR", new BigDecimal(-200)));
        payments.add(new Payment("EUR", new BigDecimal(-10)));
        payments.add(new Payment("EUR", new BigDecimal(-1100)));

        // Make a sum
        List<Payment> sums = PaymentCalculator.sumAmountsForEachCurrency(payments);

        // Check results
        assertTrue(sums != null && sums.size() == 1);
        assertTrue(PaymentTestUtilities.containsPayment(sums, new Payment("EUR", new BigDecimal(-660))));
    }

    @Test
    void multipleSumAmountsTest() {

        // Create payments list
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("EUR", new BigDecimal(100)));
        payments.add(new Payment("CZK", new BigDecimal("2000.00")));
        payments.add(new Payment("USD", new BigDecimal(-500)));
        payments.add(new Payment("EUR", new BigDecimal(-100)));
        payments.add(new Payment("CZK", new BigDecimal("-200.50")));
        payments.add(new Payment("USD", new BigDecimal(50)));
        payments.add(new Payment("USD", new BigDecimal(1)));

        // Make a sum
        List<Payment> sums = PaymentCalculator.sumAmountsForEachCurrency(payments);

        // Check results
        assertTrue(sums != null && sums.size() == 3);
        assertTrue(PaymentTestUtilities.containsPayment(sums, new Payment("EUR", BigDecimal.ZERO)));
        assertTrue(PaymentTestUtilities.containsPayment(sums, new Payment("CZK", new BigDecimal("1799.50"))));
        assertTrue(PaymentTestUtilities.containsPayment(sums, new Payment("USD", new BigDecimal(-449))));
    }

}