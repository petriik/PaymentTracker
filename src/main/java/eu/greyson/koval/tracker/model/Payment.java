package eu.greyson.koval.tracker.model;

import java.math.BigDecimal;

/**
 * Class represents a payment containing a currency and an amount
 */
public class Payment {

    /** Payment currency */
    private String currency;

    /** Payment amount */
    private BigDecimal amount;

    /**
     * New payment's constructor
     * @param currency Payment currency
     * @param amount Payment amount
     */
    public Payment(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return currency + " " + amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
