package ozamkovyi.db.entity;

import org.junit.Assert;
import org.junit.Test;

public class AdminTest {
    @Test
    public void adminGetAndSetTest(){
        Admin admin = new Admin();
        String language = "ua";
        admin.setLanguage(language);
        Assert.assertEquals(language, admin.getLanguage());
    }
}
