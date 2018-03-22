package eu.greyson.koval.tracker.threads;

import eu.greyson.koval.tracker.data.PaymentsStorage;

/**
 * Basic abstract thread contains all attributes needed in payment reader and writer.
 * Especially, shared payment storage and an object used for wait/notify thread communication (Recommendation of suitable time to print).
 */
public abstract class BasicPaymentThread extends Thread {

    /** Storage of PaymentsStorage */
    protected PaymentsStorage payments = null;

    /**
     * An object used as a recommendation of suitable printing from reader to printer.
     * Writer is waiting on this object for a notification from reader for a while.
     * */
    protected Object printRecommender = null;

    /**
     * Constructor
     * @param payments Shared payment storage
     * @param printRecommender Object used for threads synchronization
     */
    public BasicPaymentThread(PaymentsStorage payments, Object printRecommender) {
        this.payments = payments;
        this.printRecommender = printRecommender;
    }
}
