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
        CurrencyAmount ca1 = new CurrencyAmount(new BigDecimal("10.99"), "DKK");
        CurrencyAmount ca2 = new CurrencyAmount(new BigDecimal("10"), "DKK");

        CurrencyAmount sut = ca1.add(ca2);

        assertEquals(new BigDecimal("20.99"), sut.getAmount());
        // Ensure that a new instance is returned
        assertNotSame(sut, ca1);
        assertNotSame(sut, ca2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenAddingDifferentCurrencies() {
        CurrencyAmount ca1 = new CurrencyAmount(new BigDecimal("10.99"), "DKK");
        CurrencyAmount ca2 = new CurrencyAmount(new BigDecimal("10"), "EUR");

        CurrencyAmount sut = ca1.add(ca2);

    }

}
