package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.bankdata.api.jaxrs.encryption.EncodingType;
import dk.bankdata.api.jaxrs.encryption.Encryption;

import java.io.IOException;
import java.io.Serializable;

/**
 * Representing (Danish) account number. Instances should be constructed using the <code>valueOf</code>
 * methods to ensure normalization of the properties.
 *
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">Bank Account Number</a>
 */
public class AccountNumber implements Serializable {
    static final long serialVersionUID = 1L;

    private final String regNo;
    private final String accountNo;
    private final String shadowAccountId;
    private final String publicId;

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

    /**
     * String representation of account number on the form of <code>reg-account</code>.
     */
    @Override
    public String toString() {
        return String.format("%1$s-%2$s", regNo, accountNo);
    }

    /**
     * Generate a JSON representation of the account number.
     * @return JSON representation of account number
     * @throws JsonProcessingException if the generation of JSON fails
     */
    private String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    /**
     * Generate an Account instance from a JSON representation.
     * @param jsonString JSON representation of account number
     * @return Account instance representing account number
     * @throws IOException if the given json string is not properly formatted
     */
    private static AccountNumber fromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, AccountNumber.class);
    }

    public static class Builder<T extends Builder<T>> {
        private String regNo;
        private String accountNo;
        private String shadowAccountId;
        private String publicId;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
