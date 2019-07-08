package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.bankdata.api.jaxrs.encryption.DecodingType;
import dk.bankdata.api.jaxrs.encryption.EncodingType;
import dk.bankdata.api.jaxrs.encryption.Encryption;

import java.io.IOException;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Representing (Danish) account number. Instances should be constructed using the <code>valueOf</code>
 * methods to ensure normalization of the properties.
 *
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">Bank Account Number</a>
 */
public class AccountNumber implements Serializable {
    static final long serialVersionUID = 1L;

    @NotNull
    private final String regNo;
    @NotNull
    private final String accountNo;

    private final String shadowAccountId;
    private final String publicId;

    @JsonCreator
    private AccountNumber(@JsonProperty("regNo") String regNo,
                          @JsonProperty("accountNo") String accountNo,
                          @JsonProperty("shadowAccountId") String shadowAccountId,
                          @JsonProperty("publicId") String publicId) {

        this.regNo = regNo;
        this.accountNo = accountNo;
        this.shadowAccountId = shadowAccountId;
        this.publicId = publicId;
    }

    private AccountNumber(Builder<?> builder) {
        this.regNo = builder.regNo;
        this.accountNo = builder.accountNo;
        this.shadowAccountId = builder.shadowAccountId;
        this.publicId = builder.publicId;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getShadowAccountId() {
        return shadowAccountId;
    }

    public String getPublicId() {
        return publicId;
    }

    public boolean isShadowAccount() {
        return shadowAccountId != null && !shadowAccountId.isEmpty();
    }

    public static AccountNumber decrypt(String cipherKey, String publicId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Encryption encryption = new Encryption(cipherKey);
        String decrypted = encryption.decrypt(publicId, DecodingType.URL_ENCODE);

        return objectMapper.readValue(decrypted, AccountNumber.class);
    }

    /**
     * String representation of account number on the form of <code>reg-account</code>.
     */
    @Override
    public String toString() {
        return String.format("%1$s-%2$s", regNo, accountNo);
    }

    public static class Builder<T extends Builder<T>> {
        @JsonProperty("regNo")
        private String regNo;
        @JsonProperty("accountNo")
        private String accountNo;
        @JsonProperty("shadowAccountId")
        private String shadowAccountId;
        @JsonProperty("publicId")
        private String publicId;

        private String cipherKey;

        public Builder<T> regNo(String regNo) {
            this.regNo = regNo;
            return this;
        }

        public Builder<T> accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder<T> shadowAccountId(String shadowAccountId) {
            this.shadowAccountId = shadowAccountId;
            return this;
        }

        public Builder<T> cipherKey(String cipherKey) {
            this.cipherKey = cipherKey;
            return this;
        }

        public AccountNumber build() throws JsonProcessingException {
            if (this.cipherKey != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Encryption encryption = new Encryption(this.cipherKey);
                this.publicId = encryption.encrypt(objectMapper.writeValueAsString(this), EncodingType.URL_ENCODE);
            }

            return new AccountNumber(this);
        }
    }
}
