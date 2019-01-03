package dk.bankdata.api.types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.Instant;

/**
 * Custom serializer formatting {@link Instant} instances. This can be applied to POJO as
 * <code>
 *     public class Model {
 *         &commat;JsonSerialize(using = DateTimeSerializer)
 *         private Instant dateTime;
 *
 *         public Instant getDateTime() {
 *             return dateTime;
 *         }
 *     }
 * </code>
 * And will then output:
 * <pre>
 *     {
 *         'dateTime': {
 *             'epochMilli': 1000,
 *             'utc': '1970-01-01T00:00:01Z'
 *         }
 *     }
 * </pre>
 */
public class DateTimeSerializer extends StdSerializer<Instant> {

    public DateTimeSerializer() {
        super(Instant.class);
    }

    @Override
    public void serialize(Instant instant, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("epochMilli", instant.toEpochMilli());
        gen.writeStringField("utc", instant.toString());
        gen.writeEndObject();
    }
}
