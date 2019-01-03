package dk.bankdata.api.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
