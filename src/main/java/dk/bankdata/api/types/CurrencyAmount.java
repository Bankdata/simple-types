package dk.bankdata.api.types;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Amount representation including currency code as specified by ISO 4217.
 */
public class CurrencyAmount {

    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");

    private final BigDecimal amount;
    private final String currency;

    public CurrencyAmount(BigDecimal amount, String currency) {
        if (!CURRENCY_PATTERN.matcher(currency).matches()) {
            throw new IllegalArgumentException("Invalid currency code: " + currency);
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyAmount that = (CurrencyAmount) o;
        return Objects.equals(amount, that.amount)
                && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}