package dk.bankdata.api.types;

import java.util.Objects;

public class Account {
    private String regNo;
    private String accountNo;
    private String name;
    private CurrencyAmount balance;

    public Account(String regNo, String accountNo, String name, CurrencyAmount balance) {
        this.regNo = regNo;
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }
    
    public String getRegNo() {
        return regNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public CurrencyAmount getBalance() {
        return balance;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Account account = (Account) object;
        return Objects.equals(regNo, account.regNo) 
                && Objects.equals(accountNo, account.accountNo) 
                && Objects.equals(name, account.name) 
                && Objects.equals(balance, account.balance);
    }

    public int hashCode() {
        return Objects.hash(regNo, accountNo, name, balance);
    }

    @Override
    public java.lang.String toString() {
        return "Account{" +
                "regNo='" + regNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}