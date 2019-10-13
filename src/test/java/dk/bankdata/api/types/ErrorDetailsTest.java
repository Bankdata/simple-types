package dk.bankdata.api.types;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class ErrorDetailsTest {
    @Test
    public void shouldConstructErrorDetails() throws Exception {
        ErrorDetails ed = new ErrorDetails.Builder()
                .messageId("drb.general.error")
                .detail("An unrecoverable error occurred")
                .status(500)
                .extensionMember("balance", 23)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(ed);

        assertEquals("{\"messageId\":\"drb.general.error\",\"status\":500,\"detail\":\"" +
                "An unrecoverable error occurred\",\"balance\":23}", serialized);
    }

    @Test
    public void shouldParseJson() throws Exception {
        String json = "{\"messageId\":\"drb.general.error\",\"status\":500,\"detail\":\"" +
                "An unrecoverable error occurred\"}";

        ObjectMapper mapper = new ObjectMapper();
        ErrorDetails error = mapper.readValue(json, ErrorDetails.class);

        assertEquals("drb.general.error", error.getMessageId());
    }
}
