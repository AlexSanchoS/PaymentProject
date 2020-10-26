package ozamkovyi.db.entity;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

    @Test
    public void getAndSetTest(){
        int id = 1;
        String name = "UAH";
        float course =31.7f;
        Currency currency = new Currency();
        currency.setId(id);
        currency.setName(name);
        currency.setCourse(course);
        Assert.assertEquals(id, currency.getId());
        Assert.assertEquals(name, currency.getName());
        Assert.assertEquals(course, currency.getCourse(), 0.001);
    }
}
