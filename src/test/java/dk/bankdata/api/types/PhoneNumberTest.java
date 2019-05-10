package dk.bankdata.api.types;

import org.junit.Assert;
import org.junit.Test;

public class PhoneNumberTest {
    @Test
    public void shouldInterpretPhoneNumber() {
        String p1 = "+4590909090";
        String p2 = "+45 90 90 90 90";
        String p3 = "+45 9090 9090";
        String p4 = "12345678";
        String p5 = "Tlfnr";

        PhoneNumber p1o = new PhoneNumber(p1);
        PhoneNumber p2o = new PhoneNumber(p2);
        PhoneNumber p3o = new PhoneNumber(p3);
        PhoneNumber p4o = new PhoneNumber(p4);
        PhoneNumber p5o = new PhoneNumber(p5);

        String p1_3Result = "PhoneNumber{countryCode='+45', number='90909090'}";
        String p4Result = "PhoneNumber{countryCode='+45', number='12345678'}";
        String p5Result = "PhoneNumber{countryCode='+45', number='Tlfnr'}";

        Assert.assertEquals(p1_3Result, p1o.toString());
        Assert.assertEquals(p1_3Result, p2o.toString());
        Assert.assertEquals(p1_3Result, p3o.toString());
        Assert.assertEquals(p4Result, p4o.toString());
        Assert.assertEquals(p5Result, p5o.toString());
    }

    @Test
    public void shouldConstructPhoneNumber() {
        PhoneNumber p1 = new PhoneNumber("+45", "12345678");

        Assert.assertEquals("+45", p1.getCountryCode());
        Assert.assertEquals("12345678", p1.getNumber());
    }

    @Test
    public void shouldBeEqual() {
        PhoneNumber p1 = new PhoneNumber("+45", "12345678");
        PhoneNumber p2 = new PhoneNumber("+4512345678");

        Assert.assertEquals(p1, p2);
    }
}