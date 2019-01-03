package dk.bankdata.api.types;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.Instant;
import org.junit.Test;

public class DateTimeSerializerTest {

    @Test
    public void testSerialization() throws Exception {
        Instant now = Instant.ofEpochMilli(3600000);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new DateTimeSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(now);
        assertEquals("{\"epochMilli\":3600000,\"utc\":\"1970-01-01T01:00:00Z\"}", serialized);
    }

}
