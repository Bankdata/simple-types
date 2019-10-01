package dk.bankdata.api.types;

import org.junit.Assert;
import org.junit.Test;

public class BankTest {
    @Test
    public void shouldGetJyskebankById() {
        Bank bank = Bank.byId(51);

        Assert.assertEquals(7858, bank.getMainRegNo());
    }

    @Test
    public void shouldGetJyskebankByMainRegNo() {
        Bank bank = Bank.byMainRegNo(7858);

        Assert.assertEquals(51, bank.getId());
    }

}