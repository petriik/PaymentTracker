package eu.greyson.koval.tracker.utilities;

import eu.greyson.koval.tracker.model.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class contains required operations with payments.
 */
public class PaymentCalculator {

    /**
     * Make a sum of amounts for each currency separately
     * @param payments All payment records
     * @return A list of payments where amount means a sum of all payments with the same currency
     */
    public static List<Payment> sumAmountsForEachCurrency(List<Payment> payments) {
        List<Payment> result = null;

        if (payments != null) {

            // Group payments by currencies and convert it back to list of payments
            result = payments.stream()
                    .collect(Collectors.groupingBy(Payment::getCurrency, Collectors.reducing(BigDecimal.ZERO, Payment::getAmount, BigDecimal::add)))
                    .entrySet().stream()
                    .map(entry -> new Payment(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }

        return result;
    }
}
