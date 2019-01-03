package dk.bankdata.api.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Date and time representation for JSON communication models. The output will contain both
 * the epoch millisecond timestamp as well as a human-readable representation.
 */
public class DateTime {
    private final long epochMilli;
    private final String utc;

    /**
     * Creates new instance based on timestamp from unix epoch (1970-01-01T00:00:00Z)
     * @param epochMilli Milliseconds since 1970-01-01T00:00:00Z
     */
    public DateTime(long epochMilli) {
        this.epochMilli = epochMilli;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        this.utc = df.format(new Date(epochMilli));
    }

    public long getEpochMilli() {
        return epochMilli;
    }

    public String getUtc() {
        return utc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return epochMilli == dateTime.epochMilli;
    }

    @Override
    public int hashCode() {
        return Objects.hash(epochMilli);
    }

    public static DateTime fromInstant(Instant instant) {
        return new DateTime(instant.toEpochMilli());
    }

}
