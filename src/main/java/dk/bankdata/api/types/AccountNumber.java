package dk.bankdata.api.types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dk.bankdata.api.jaxrs.encryption.EncodingType;
import dk.bankdata.api.jaxrs.encryption.Encryption;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representing (Danish) account number. Instances should be constructed using the <code>valueOf</code>
 * methods to ensure normalization of the properties.
 *
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">Bank Account Number</a>
 */
public class AccountNumber implements Serializable {
    static final long serialVersionUID = 1L;

    private String regNo;
    private String accountNo;
    private String publicId;

    public AccountNumber(String regNo, String accountNo) {
        this.regNo = regNo;
        this.accountNo = accountNo;
    }

    public AccountNumber(String regNo, String accountNo, String encryptionKey) throws JsonProcessingException {
        this.regNo = regNo;
        this.accountNo = accountNo;
        this.publicId = generatePublicId(encryptionKey);
    }

    private String generatePublicId(String encryptionKey) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Encryption encryption = new Encryption(encryptionKey);

        return encryption.encrypt(objectMapper.writeValueAsString(this), EncodingType.URL_ENCODE);
    }

    public String getRegNo() {
        return regNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getPublicId() {
        return publicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNumber accountId = (AccountNumber) o;
        return regNo.equals(accountId.regNo)
                && accountNo.equals(accountId.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo, accountNo);
    }

    /**
     * String representation of account number on the form of <code>reg-account</code>.
     */
    @Override
    public String toString() {
        return String.format("%1$s-%2$s", regNo, accountNo);
    }

    /**
     * Parse a string representation of Danish account number on the form <code>reg-account</code>.
     * @param id String representation of account number
     * @return Account instance representing account number
     * @throws IllegalArgumentException if the given string is not properly formatted
     */
    public static AccountNumber valueOf(String id) throws IllegalArgumentException {
        Pattern p = Pattern.compile("(\\d+)-(\\d+)");
        Matcher m = p.matcher(id);
        if (m.matches()) {
            return valueOf(Integer.valueOf(m.group(1)), Long.valueOf(m.group(2)));
        } else {
            throw new IllegalArgumentException("Unable to match id: " + id);
        }
    }

    /**
     * Return {@link AccountNumber} based on string registration number and string account number.
     * @param reg Registration number
     * @param account Account number
     * @return new account number instance
     * @throws NumberFormatException if given strings cannot be parsed into number
     */
    public static AccountNumber valueOf(String reg, String account) throws NumberFormatException {
        return valueOf(Integer.valueOf(reg), Long.valueOf(account));
    }

    /**
     * Return {@link AccountNumber} based on registration number (bank identifier) and account number.
     * @param reg Registration number
     * @param account Account number
     * @return new account number instance
     */
    public static AccountNumber valueOf(int reg, long account) {
        return new AccountNumber(String.format("%04d", reg), String.format("%010d", account));
    }

}
