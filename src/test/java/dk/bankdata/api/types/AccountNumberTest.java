package dk.bankdata.api.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountNumberTest {

    @Test
    public void testToString() {
        AccountNumber an = AccountNumber.valueOf("0123", "1234567890");
        assertEquals("0123-1234567890", an.toString());
    }

    @Test
    public void testValueOfString() {
        AccountNumber an = AccountNumber.valueOf("1234-1234567890");
        assertEquals("1234-1234567890", an.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfStringIllegal() {
        AccountNumber.valueOf("xx34-1234567890");
    }

    @Test
    public void testValueOfStringString() {
        AccountNumber an = AccountNumber.valueOf("1234", "1234567890");
        assertEquals("1234-1234567890", an.toString());
    }

    @Test
    public void testValueOfIntLong() {
        AccountNumber an = AccountNumber.valueOf(12, 3456);
        assertEquals("0012-0000003456", an.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        AccountNumber a1 = AccountNumber.valueOf("1234", "56789");
        AccountNumber a2 = AccountNumber.valueOf("1234", "0056789");
        AccountNumber a3 = AccountNumber.valueOf("4321", "56789");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, a3);
        assertNotEquals(a1.hashCode(), a3.hashCode());
    }

}
