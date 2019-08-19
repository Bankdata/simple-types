package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.validation.constraints.NotNull;

public class ErrorDetails {
    @NotNull(message = "MessageId has to be populated")
    private final String messageId;
    @NotNull(message = "Status has to be populated")
    private final Integer status;
    @NotNull(message = "Detail has to be populated")
    private final String detail;
    private final Map<String, ?> extension;

    protected ErrorDetails(ErrorDetails.Builder<?> builder) {
        this.messageId = builder.messageId;
        this.status = builder.status;
        this.detail = builder.detail;
        this.extension = builder.extension;
    }

    public String getMessageId() {
        return messageId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    @JsonAnyGetter
    public Map<String, ?> getExtension() {
        return extension;
    }

    /**
     * MessageId : Identifies the messages to be handled in the frontend. fx app.area.function
     * This will both cover the key to the translation and
     * a way for the frontend to identify and deal with certain messages in a specific way
     * Status : The http error code
     * Details : details are used the debug and logging
     * Extensions : values that can be merged into the message in the spa (optional)
     */
    @SuppressWarnings("unchecked")
    public static class Builder<T extends ErrorDetails.Builder<T>> {
        private String messageId;
        private int status;
        private String detail;
        private Map<String, Object> extension = new ConcurrentHashMap<>();

        public T messageId(String messageId) {
            this.messageId = messageId;
            return (T) this;
        }

        public T detail(String detail) {
            this.detail = detail;
            return (T) this;
        }

        public T status(int status) {
            this.status = status;
            return (T) this;
        }

        public T extensionMember(String member, Object value) {
            this.extension.put(member, value);
            return (T) this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this);
        }
    }

}
