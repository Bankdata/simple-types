package dk.bankdata.api.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.bankdata.api.jaxrs.encryption.DecodingType;
import dk.bankdata.api.jaxrs.encryption.EncodingType;
import dk.bankdata.api.jaxrs.encryption.Encryption;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

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

        this.regNo = trimLeadingZeroes(regNo);
        this.accountNo = trimLeadingZeroes(accountNo);
        this.shadowAccountId = shadowAccountId;
        this.publicId = publicId;
    }

    private AccountNumber(Builder<?> builder) {
        this(builder.regNo, builder.accountNo, builder.shadowAccountId, builder.publicId);
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

    @JsonIgnore
    public boolean isShadowAccount() {
        return shadowAccountId != null && !shadowAccountId.isEmpty();
    }

    public static AccountNumber decrypt(String cipherKey, String publicId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Encryption encryption = new Encryption(cipherKey);
        String decrypted = encryption.decrypt(publicId, DecodingType.URL_ENCODE);

        return objectMapper.readValue(decrypted, AccountNumber.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountNumber that = (AccountNumber) o;
        return regNo.equals(that.regNo)
            && accountNo.equals(that.accountNo)
            && Objects.equals(shadowAccountId, that.shadowAccountId);
    }


    /**
     * Compare if regNo and accountNo are equals.
     *
     * @param accountNumber to compare
     * @return true if the regNo and accountNo are equals. Note the shadowaccountId is not part of this comparison. If this is needed use
     *     the equal method instead.
     */
    public boolean isSameRegNoAndAccountNo(AccountNumber accountNumber) {
        if (this == accountNumber) {
            return true;
        }
        if (accountNumber == null) {
            return false;
        }
        return regNo.equals(accountNumber.regNo)
            && accountNo.equals(accountNumber.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo, accountNo, shadowAccountId);
    }

    @Override
    public String toString() {
        return "AccountNumber{" +
            "regNo='" + regNo + '\'' +
            ", accountNo='" + accountNo + '\'' +
            ", shadowAccountId='" + shadowAccountId + '\'' +
            ", publicId='" + publicId + '\'' +
            '}';
    }

    private String trimLeadingZeroes(String stringToTrim) {
        return stringToTrim.replaceFirst("^0+(?!$)", "");
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

        public AccountNumber build() throws IllegalArgumentException {
            Objects.requireNonNull(this.regNo);
            Objects.requireNonNull(this.accountNo);
            if (this.cipherKey != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Encryption encryption = new Encryption(this.cipherKey);

                try {
                    this.publicId = encryption.encrypt(objectMapper.writeValueAsString(this), EncodingType.URL_ENCODE);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException(e);
                }
            }

            return new AccountNumber(this);
        }
    }
}
