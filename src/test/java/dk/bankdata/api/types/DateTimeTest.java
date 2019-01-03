package dk.bankdata.api.types;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.Test;

public class DateTimeTest {

    @Test
    public void testSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(new DateTime(Instant.ofEpochMilli(3600000)));
        assertEquals("{\"epochMilli\":3600000,\"utc\":\"1970-01-01T01:00:00Z\"}", serialized);
    }

    @Test
    public void testOfEpochMilli() {
        DateTime dateTime = DateTime.ofEpochMilli(3600000);
        assertEquals(3600000, dateTime.getEpochMilli());
    }

}
