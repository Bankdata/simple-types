package dk.bankdata.api.types;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Amount representation including currencyCode code as specified by ISO 4217.
 */
public class CurrencyAmount {

    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");

    private final BigDecimal amount;
    private final String currencyCode;

    public CurrencyAmount(BigDecimal amount, String currencyCode) {
        if (!CURRENCY_PATTERN.matcher(currencyCode).matches()) {
            throw new IllegalArgumentException("Invalid currencyCode code: " + currencyCode);
        }
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Returns a CurrencyAmount whose value is: this + currencyAmount.
     * @param currencyAmount - the amount to add
     * @return a CurrencyAmount whose value is: this + currencyAmount - using BigDecimal.add (so be aware of scale)
     * @throws IllegalArgumentException if the currencies are not equal
     */
    public CurrencyAmount add(CurrencyAmount currencyAmount) {
        Objects.requireNonNull(currencyAmount, "CurrencyAmount must not be null");
        if (!currencyCode.equals(currencyAmount.getCurrencyCode())) {
            throw new IllegalArgumentException("Can't add amounts with different currencies");
        }

        return new CurrencyAmount(amount.add(currencyAmount.getAmount()), currencyCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyAmount that = (CurrencyAmount) o;
        return Objects.equals(amount, that.amount)
                && Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyCode);
    }

    @Override
    public String toString() {
        return "CurrencyAmount{" +
            "amount=" + amount +
            ", currencyCode='" + currencyCode + '\'' +
            '}';
    }
}
