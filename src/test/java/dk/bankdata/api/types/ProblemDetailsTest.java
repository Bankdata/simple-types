package dk.bankdata.api.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProblemDetailsTest {
    @Test
    public void testSerialization() throws Exception {
        ProblemDetails pd = new ProblemDetails.Builder()
                .type(URI.create("https://type"))
                .title("Error Occurred")
                .detail("An unrecoverable error occurred")
                .status(500)
                .instance(URI.create("https://instance/1"))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String serialized = mapper.writeValueAsString(pd);
        assertEquals("{\"type\":\"https://type\",\"title\":\"Error Occurred\",\"status\":500,\"detail\":\"An unrecoverable error occurred\",\"instance\":\"https://instance/1\"}", serialized);
    }
}
