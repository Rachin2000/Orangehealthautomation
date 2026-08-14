package base;

import utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import factory.DriverFactory;

public class baseTest {

    protected WebDriver driver;

    @BeforeMethod
    public  void setup() throws  InterruptedException{
        driver=DriverFactory.driver();
        driver.manage().deleteAllCookies();
        driver.get(ConfigReader.getProperty("url"));
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());

    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();

    }


}
