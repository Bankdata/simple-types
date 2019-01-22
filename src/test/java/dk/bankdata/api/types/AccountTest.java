package dk.bankdata.api.types;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Test;

public class AccountTest {

    @Test
    public void testSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(new Account("0123", "1234567", "somename", 
                                                      new CurrencyAmount(BigDecimal.valueOf(123), "DKK")));
        String r = "{\"regNo\":\"0123\",\"accountNo\":\"1234567\",\"name\":\"somename\",\"balance\":{\"amount\":123,\"currency\":\"DKK\"}}";

        assertEquals(r, serialized);
    }
}
