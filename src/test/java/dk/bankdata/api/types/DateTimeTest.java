package dk.bankdata.api.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTimeTest {

    @Test
    public void testSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(new DateTime(3600));
        assertEquals("{\"epochMilli\":3600,\"utc\":\"1970-01-01T01:00:03+0100\"}", serialized);
    }

    @Test
    public void testFromInstant() {
        Instant now = Instant.now();
        DateTime dateTime = DateTime.fromInstant(now);
        assertEquals(now.toEpochMilli(), dateTime.getEpochMilli());
    }

}
