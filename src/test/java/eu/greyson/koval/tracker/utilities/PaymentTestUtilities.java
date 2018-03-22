package eu.greyson.koval.tracker.utilities;

import eu.greyson.koval.tracker.model.Payment;

import java.util.List;

public class PaymentTestUtilities {

    /**
     * Basic check if list contains the given payment
     * @param payments List of payments
     * @param payment Payment to check
     * @return true if payment is in the list; false otherwise
     */
    public static boolean containsPayment(List<Payment> payments, Payment payment) {

        if (payments != null && payment != null) {
            for (Payment p: payments) {
                if (p.getCurrency().equals(payment.getCurrency()) && p.getAmount().equals(payment.getAmount())) {
                    return true;
                }
            }
        }

        return false;
    }
}
