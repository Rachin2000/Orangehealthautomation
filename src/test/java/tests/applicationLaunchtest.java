package tests;

import base.baseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class applicationLaunchtest extends baseTest {

    @Test
    public void verifyApplicationLaunch(){
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
    }


}
