package dk.bankdata.api.types;

import org.junit.Assert;
import org.junit.Test;

public class AccountNumberTest {
    @Test
    public void shouldCreateSimpleAccountNumber() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .build();

        Assert.assertEquals("some-regno", number.getRegNo());
        Assert.assertEquals("some-accountno", number.getAccountNo());
        Assert.assertNull(number.getShadowAccountId());
        Assert.assertNull(number.getPublicId());
    }

    @Test
    public void shouldCreateShadowAccountNumber() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .shadowAccountId("some-shadow")
                .build();

        Assert.assertEquals("some-regno", number.getRegNo());
        Assert.assertEquals("some-accountno", number.getAccountNo());
        Assert.assertEquals("some-shadow", number.getShadowAccountId());
        Assert.assertNull(number.getPublicId());
    }

    @Test
    public void shouldCreatePublicIdWithoutShadowAccountNumber() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .cipherKey("ThisIsPossiblyTheWorstCreatedKey")
                .build();

        Assert.assertEquals("some-regno", number.getRegNo());
        Assert.assertEquals("some-accountno", number.getAccountNo());
        Assert.assertNull(number.getShadowAccountId());
        Assert.assertEquals("gSM4IML5DaaCi3ctaFlP1jrTbpByjMGH9iD28Z96i4gOti8cx0tiaBNwJyDV-YHQj9GYU" +
                "_OCMwvmh4t0gIv38F3Ddz6GoiAy5UJG-WJO3B4pcAac_wmYQJbFnlNDd8cs", number.getPublicId());
    }

    @Test
    public void shouldCreateFullAccountNumber() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .shadowAccountId("some-shadowAccountId")
                .cipherKey("ThisIsPossiblyTheWorstCreatedKey")
                .build();

        Assert.assertEquals("some-regno", number.getRegNo());
        Assert.assertEquals("some-accountno", number.getAccountNo());
        Assert.assertEquals("some-shadowAccountId", number.getShadowAccountId());
        Assert.assertEquals("gSM4IML5DaaCi3ctaFlP1jrTbpByjMGH9iD28Z96i4gOti8cx0tiaBNwJyDV-YHQj9GYU" +
                "_OCMwvmh4t0gIv38PlXvMqlUbY7A4Zwan9EBhW_xOxtkZ3Zqneey0DXknf6qV8V-wBFGg5wT-GzHrRn7A==",
                number.getPublicId());
    }

    @Test
    public void shouldBeShadowAccount() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .shadowAccountId("some-shadowaccountid")
                .build();

        Assert.assertTrue(number.isShadowAccount());
    }

    @Test
    public void shouldNotBeShadowAccount() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .build();

        Assert.assertFalse(number.isShadowAccount());
    }

    @Test
    public void shouldDecrypt() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .accountNo("some-accountno")
                .shadowAccountId("some-shadowaccountid")
                .cipherKey("ThisIsPossiblyTheWorstCreatedKey")
                .build();

        AccountNumber decrypted = AccountNumber.decrypt("ThisIsPossiblyTheWorstCreatedKey",
                number.getPublicId());

        Assert.assertEquals("some-regno", decrypted.getRegNo());
        Assert.assertEquals("some-accountno", decrypted.getAccountNo());
        Assert.assertEquals("some-shadowaccountid", decrypted.getShadowAccountId());
        Assert.assertNull(decrypted.getPublicId());

    }

    @Test
    public void shouldFailCreation() throws Exception {
        AccountNumber number = new AccountNumber.Builder()
                .regNo("some-regno")
                .shadowAccountId("some-id")
                .build();
    }
}
