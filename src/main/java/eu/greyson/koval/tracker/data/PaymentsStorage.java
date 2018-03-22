package eu.greyson.koval.tracker.data;

import eu.greyson.koval.tracker.model.Payment;

import java.util.List;

/**
 * Interface for a storage of payments. It provides basic payment storage operations.
 */
public interface PaymentsStorage {

    /**
     * Adds a payment to the storage
     * @param payment Payment to add
     */
    public void addPayment(Payment payment);

    /**
     * Adds payments to the storage
     * @param payment Payments to add
     */
    public void addPayments(List<Payment> payment);

    /**
     * Gets all payment records
     * @return A list of all payment records
     */
    public List<Payment> getPayments() ;
}
