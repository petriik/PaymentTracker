package eu.greyson.koval.tracker.data.impl;

import eu.greyson.koval.tracker.data.PaymentsStorage;
import eu.greyson.koval.tracker.model.Payment;

import java.util.ArrayList;
import java.util.List;


/**
 * Basic implementation of PaymentsStorage interface.
 * This class contains a list of all payments added during the running of the application.
 */
public class PaymentsStorageImpl implements PaymentsStorage {

    /** Storage of all payments */
    private List<Payment> payments = new ArrayList<>();

    /** {@inheritDoc} */
    public synchronized void addPayment(Payment payment) {
        if (payments != null && payment != null) {
            payments.add(payment);
        }
    }

    /** {@inheritDoc} */
    public synchronized void addPayments(List<Payment> payments) {
        if (this.payments != null && payments != null) {
            payments.forEach(this::addPayment);
        }
    }

    /** {@inheritDoc} */
    public synchronized List<Payment> getPayments() {
        return payments;
    }

}
