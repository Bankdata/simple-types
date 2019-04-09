package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.Instant;

/**
 * Date and time representation for JSON communication models. The output will contain both
 * the epoch millisecond timestamp as well as a human-readable representation.
 */
public class DateTime {

    private final Instant instant;

    /**
     * Creates new instance based on timestamp from unix epoch (1970-01-01T00:00:00Z).
     * @param instant Instant to be represented by this date time
     */
    public DateTime(Instant instant) {
        this.instant = instant;
    }

    @JsonValue
    @JsonSerialize(using = DateTimeSerializer.class)
    public Instant getInstant() {
        return instant;
    }

    public static DateTime ofEpochMilli(long epochMilli) {
        return new DateTime(Instant.ofEpochMilli(epochMilli));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return instant.equals(dateTime.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }
}
