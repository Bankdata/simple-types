package dk.bankdata.api.types;

import java.util.Objects;

public class PhoneNumber {
    private String countryCode;
    private String number;

    public PhoneNumber(String phoneNumber) {
        String numberWithNoSpaces = phoneNumber.replace(" ", "");

        if (phoneNumber.contains("+")) {
            countryCode = numberWithNoSpaces.substring(0, 3);
            number = numberWithNoSpaces.substring(3);
        } else {
            countryCode = "+45";
            number = numberWithNoSpaces;
        }

    }

    public PhoneNumber(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryCode='" + countryCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return countryCode.equals(that.countryCode)
                && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }
}
