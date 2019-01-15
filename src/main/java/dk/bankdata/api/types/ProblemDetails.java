package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of <a href="https://tools.ietf.org/html/rfc7807">RFC 7807 - Problem Details for HTTP APIs</a>.
 * The class may be extended to allow for extension members by extending {@link ProblemDetails} and {@link ProblemDetails.Builder}
 * <code>
 * public class ProblemDetailsExtension extends ProblemDetails {
 *
 *   private final String error;
 *
 *   protected ProblemDetailsExtension(Builder builder) {
 *     super(builder);
 *     this.error = builder.error;
 *   }
 *
 *   public String getError() {
 *     return error;
 *   }
 *
 *   public static class Builder extends ProblemDetails.Builder&lt;Builder&gt; {
 *
 *     private String error;
 *
 *     public Builder error(String error) {
 *       this.error = error;
 *       return this;
 *     }
 *
 *     public ProblemDetailsExtension build() {
 *       return new ProblemDetailsExtension(this);
 *     }
 *   }
 * }
 * </code>
 *
 * <p>Extension members may also be added by utilizing the builder.
 */
public class ProblemDetails {

    private final URI type;
    private final String title;
    private final Integer status;
    private final String detail;
    private final URI instance;
    private final Map<String, ?> extension;

    protected ProblemDetails(Builder<?> builder) {
        this.type = builder.type;
        this.title = builder.title;
        this.status = builder.status;
        this.detail = builder.detail;
        this.instance = builder.instance;
        this.extension = builder.extension;
    }

    public URI getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public URI getInstance() {
        return instance;
    }

    @JsonAnyGetter
    public Map<String, ?> getExtension() {
        return extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemDetails that = (ProblemDetails) o;
        return Objects.equals(type, that.type)
                && Objects.equals(title, that.title)
                && Objects.equals(status, that.status)
                && Objects.equals(detail, that.detail)
                && Objects.equals(instance, that.instance)
                && Objects.equals(extension, extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, status, detail, instance, extension);
    }

    @Override
    public String toString() {
        return "ProblemDetails{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", instance=" + instance +
                ", extension=" + extension +
                '}';
    }
    
    @SuppressWarnings("unchecked")
    public static class Builder<T extends Builder<T>> {
        private URI type = URI.create("about:blank");
        private String title;
        private int status;
        private String detail;
        private URI instance;
        private Map<String, Object> extension = new ConcurrentHashMap<>();

        public T type(URI type) {
            this.type = type;
            return (T) this;
        }

        public T title(String title) {
            this.title = title;
            return (T) this;
        }

        public T detail(String detail) {
            this.detail = detail;
            return (T) this;
        }

        public T instance(URI instance) {
            this.instance = instance;
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

        public ProblemDetails build() {
            return new ProblemDetails(this);
        }
    }
}
