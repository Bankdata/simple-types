package dk.bankdata.api.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Test;

public class CurrencyAmountTest {

    @Test
    public void testSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(new CurrencyAmount(BigDecimal.valueOf(123), "DKK"));
        assertEquals("{\"amount\":123,\"currency\":\"DKK\"}", serialized);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCurrency() {
        new CurrencyAmount(BigDecimal.ONE, "invalid");
    }

    @Test
    public void shouldAddAnotherCurrencyAmount() {
        CurrencyAmount currencyAmount = new CurrencyAmount(new BigDecimal("10.99"), "DKK");
        CurrencyAmount currencyAmountToAdd = new CurrencyAmount(new BigDecimal("10"), "DKK");

        CurrencyAmount sut = currencyAmount.add(currencyAmountToAdd);

        assertEquals(new BigDecimal("20.99"), sut.getAmount());
        // Ensure that a new instance is returned
        assertNotSame(sut, currencyAmount);
        assertNotSame(sut, currencyAmountToAdd);

    }

    @Test
    public void shouldReturnZeroIfAmountIsNull() {
        CurrencyAmount currencyAmount = new CurrencyAmount(null, "DKK");

        assertEquals(BigDecimal.ZERO, currencyAmount.getAmount());
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenAddingNullObject() {
        CurrencyAmount currencyAmount = new CurrencyAmount(new BigDecimal("10.99"), "DKK");
        CurrencyAmount sut = currencyAmount.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenAddingDifferentCurrencies() {
        CurrencyAmount currencyAmount = new CurrencyAmount(new BigDecimal("10.99"), "DKK");
        CurrencyAmount currencyAmountToAdd = new CurrencyAmount(new BigDecimal("10"), "EUR");

        CurrencyAmount sut = currencyAmount.add(currencyAmountToAdd);

    }

}
