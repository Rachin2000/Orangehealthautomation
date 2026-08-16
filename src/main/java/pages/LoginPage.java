package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LoggerUtil;
import utilities.ReportLogger;
import utilities.WaitUtility;

public class LoginPage {

    private WebDriver driver;
    private WaitUtility wait;

    //adding logger initialization for logging
    private static final Logger logger=
            LoggerUtil.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtility(driver);
        logger.info("Login page initialized");
    }




    private By txtUsername =
            By.xpath("//input[@name='username']");

    private By txtPassword =
            By.xpath("//input[@name='password']");

    private By LoginBtn =
            By.xpath("//button[text()=' Login ']");

    // Invalid username/password
    private By invalidCredentialsMessage =
            By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    // Username Required
    private By usernameRequiredMessage = By.xpath("//span[text()='Required']");
            /*By.xpath(
                    "//input[@name='username']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//span[contains(@class,'oxd-input-field-error-message')]"
            ); */

    // Password Required
    private By passwordRequiredMessage =
            By.xpath(
                    "//input[@name='password']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//span[contains(@class,'oxd-input-field-error-message')]"
            );


    public void enterUsername(String username) {

        logger.info("Entering username: "+username);
        ReportLogger.info("Entering username: "+username);

        System.out.println(
                "Entering the username: [" + username + "]"
        );

        wait.waitForElementVisible(txtUsername)
                .sendKeys(username);

        logger.debug("Username entered successfully");
    }


    public void enterPassword(String password) {

        logger.info("Entering password: "+password);
        ReportLogger.info("Entering password: "+password);


        System.out.println(
                "Entering the password: [" + password + "]"
        );

        wait.waitForElementVisible(txtPassword)
                .sendKeys(password);

        logger.debug("Password entered successfully");
    }


    public void ClickLogin() {

        logger.info("Clicking on Login button.");
        ReportLogger.info("Clicking on Login button.");

        wait.waitForElementClickable(LoginBtn)
                .click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.debug("Login button clicked.");


    }


    public void login(String username, String password) {

        logger.info("Starting login operation");
        ReportLogger.info("Starting login operation");

        enterUsername(username);
        enterPassword(password);
        ClickLogin();

        logger.debug("Login operation completed");
    }


    // ==========================================
    // INVALID CREDENTIALS
    // ==========================================

    public boolean isInvalidCredentialsDisplayed() {
        ReportLogger.info("Invalid credentials check...");
        try {

            return wait
                    .waitForElementVisible(invalidCredentialsMessage)
                    .isDisplayed();

        } catch (Exception e) {

            System.out.println(
                    "Invalid credentials element was NOT found."
            );

            System.out.println(
                    "Page source contains 'Invalid credentials': "
                            + driver.getPageSource()
                            .contains("Invalid credentials")
            );

            return false;
        }
    }


    // ==========================================
    // USERNAME REQUIRED
    // ==========================================

    public boolean isUsernameRequiredDisplayed() {
        ReportLogger.info("Username Required alert message displayed..");
        return driver
                .findElements(usernameRequiredMessage)
                .stream()
                .anyMatch(element ->
                        element.isDisplayed()
                                && element.getText()
                                .equals("Required")
                );
    }


    // ==========================================
    // PASSWORD REQUIRED
    // ==========================================

    public boolean isPasswordRequiredDisplayed() {
        ReportLogger.info("Password Required alert message displayed..");
        return driver
                .findElements(passwordRequiredMessage)
                .stream()
                .anyMatch(element ->
                        element.isDisplayed()
                                && element.getText()
                                .equals("Required")
                );
    }
}