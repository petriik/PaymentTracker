package eu.greyson.koval.tracker.data.impl;

import eu.greyson.koval.tracker.data.PaymentsStorage;
import eu.greyson.koval.tracker.model.Payment;
import eu.greyson.koval.tracker.utilities.PaymentTestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentsStorageImplTest {

    // Tested payment storage
    PaymentsStorage storage = null;


    @BeforeEach
    void initPaymentStorage() {
        storage = new PaymentsStorageImpl();
    }

    @Test
    void simpleAdditionTest() {
        storage.addPayment(new Payment("EUR", new BigDecimal(450)));
        assertTrue(storage.getPayments().size() == 1);
        assertTrue("EUR 450".equals(storage.getPayments().get(0).toString()));
    }

    @Test
    void simpleNullTest() {
        storage.addPayment(null);
        assertTrue(storage.getPayments().isEmpty());
    }

    @Test
    void multipleAdditionTest() {

        // Create temp payment list
        List<Payment> payments = new ArrayList();
        payments.add(new Payment("EUR", new BigDecimal(154)));
        payments.add(new Payment("CZK", new BigDecimal(-450)));
        payments.add(new Payment("CZK", new BigDecimal(487)));
        payments.add(new Payment("USD", new BigDecimal(0)));

        // Add payments
        storage.addPayments(payments);

        // Check result
        assertTrue(storage.getPayments().size() == 4);
        assertTrue(PaymentTestUtilities.containsPayment(storage.getPayments(), new Payment("EUR", new BigDecimal(154))));
        assertTrue(PaymentTestUtilities.containsPayment(storage.getPayments(), new Payment("CZK", new BigDecimal(-450))));
        assertTrue(PaymentTestUtilities.containsPayment(storage.getPayments(), new Payment("CZK", new BigDecimal(487))));
        assertTrue(PaymentTestUtilities.containsPayment(storage.getPayments(), new Payment("USD", new BigDecimal(0))));
    }
}