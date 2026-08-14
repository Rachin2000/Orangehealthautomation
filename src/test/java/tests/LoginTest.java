package tests;

import base.baseTest;
import dataProvider.DataProviderClass;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends baseTest {

    @Test(
            dataProvider = "loginData",
            dataProviderClass = DataProviderClass.class
    )
    public void verifyValidLogin(
            String username,
            String password,
            String expectedResult,
            String expectedMessage) {

        System.out.println("=====================================");
        System.out.println(
                "Executing Login Test with: "
                        + username + "/" + password
        );

        System.out.println(
                "Expected Result: [" + expectedResult + "]"
        );

        System.out.println(
                "Expected Message: [" + expectedMessage + "]"
        );

        LoginPage loginPage = new LoginPage(driver);

        // Login using Excel data
        loginPage.login(username, password);

        System.out.println(
                "URL after login: " + driver.getCurrentUrl()
        );

        // ==========================================
        // SUCCESSFUL LOGIN
        // ==========================================

        if (expectedResult.equalsIgnoreCase("Success")) {

            DashboardPage dashboardPage =
                    new DashboardPage(driver);

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard was not displayed after successful login"
            );
        }

        // ==========================================
        // FAILED LOGIN
        // ==========================================

        else {

            // --------------------------------------
            // Invalid username/password
            // --------------------------------------

            if (expectedMessage.equalsIgnoreCase("Invalid credentials")) {

                Assert.assertTrue(
                        loginPage.isInvalidCredentialsDisplayed(),
                        "Login failed, but 'Invalid credentials' message was not displayed"
                );
            }

            // --------------------------------------
            // Username is blank
            // --------------------------------------

            else if (expectedMessage.equalsIgnoreCase("Required")) {

                Assert.assertTrue(
                        loginPage.isUsernameRequiredDisplayed(),
                        "Username is blank, but 'Required' message was not displayed for username"
                );
            }

            // --------------------------------------
            // Password is blank
            // --------------------------------------

            else if (expectedMessage.equalsIgnoreCase("Required")) {

                Assert.assertTrue(
                        loginPage.isPasswordRequiredDisplayed(),
                        "Password is blank, but 'Required' message was not displayed for password"
                );
            }

            // --------------------------------------
            // Both username and password blank
            // --------------------------------------

            else if (expectedMessage.equalsIgnoreCase("Required")) {

                Assert.assertTrue(
                        loginPage.isUsernameRequiredDisplayed(),
                        "Username 'Required' message was not displayed"
                );

                Assert.assertTrue(
                        loginPage.isPasswordRequiredDisplayed(),
                        "Password 'Required' message was not displayed"
                );
            }

            // --------------------------------------
            // Unknown expected message
            // --------------------------------------

            else {

                Assert.fail(
                        "Unknown expected message in Excel: "
                                + expectedMessage
                );
            }
        }

        System.out.println("=====================================");
    }
}