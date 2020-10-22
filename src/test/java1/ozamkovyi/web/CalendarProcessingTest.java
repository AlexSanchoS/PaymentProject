package ozamkovyi.web;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.bean.CreditCardBean;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarProcessingTest {

    @Test
    public void date2StringTest(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(1997, 1, 8);
        Assert.assertEquals("1997-02-08", CalendarProcessing.date2String(calendar));
        calendar.set(1997, 10, 10);
        Assert.assertEquals("1997-11-10", CalendarProcessing.date2String(calendar));
    }

    @Test
    public void date2StringForCardTest(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2021, 1, 8);
        Assert.assertEquals("2021-02", CalendarProcessing.date2StringForCard(calendar));
        calendar.set(2021, 10, 10);
        Assert.assertEquals("2021-11", CalendarProcessing.date2StringForCard(calendar));
    }

    @Test
    public void string2FullDateTest(){
        String date = "2020-10-16 15:44:40";
        Calendar calendar = CalendarProcessing.string2FullDate(date);
        Assert.assertNotNull(calendar);
    }

    @Test
    public void getValidityForNewCardTest(){
        String calendar = CalendarProcessing.getValidityForNewCard();
        Assert.assertNotNull(calendar);
    }

    @Test
    public void getFullCurrentDateTest(){
        String calendar = CalendarProcessing.getFullCurrentDate();
        Assert.assertNotNull(calendar);
    }

    @Test
    public void isCardValidTest(){
        CreditCardBean creditCardBean = new CreditCardBean();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2019,02,20);
        creditCardBean.setValidity(calendar);
        calendar.set(2020,9,1);
        Assert.assertFalse(CalendarProcessing.isCardValid(creditCardBean));
        calendar.set(2020,11,1);
        Assert.assertTrue(CalendarProcessing.isCardValid(creditCardBean));
    }
}
