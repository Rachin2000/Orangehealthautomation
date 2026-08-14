package tests;

import base.baseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;


public class Dashboardtest {

    @Listeners(TestListener.class)
    public class DashboardTest extends baseTest {

        private DashboardPage dashboardPage;

        @BeforeMethod
        public void loginToApplication() {

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login("Admin", "admin123");

            dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard was not displayed after login"
            );
        }

        // DASH_001
        @Test
        public void verifyDashboardDisplayed() {

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard is not displayed"
            );
        }

        // DASH_002
        @Test
        public void verifyDashboardURL() {

            Assert.assertTrue(
                    dashboardPage.isDashboardUrlDisplayed(),
                    "Incorrect Dashboard URL"
            );
        }

        // DASH_003
        @Test
        public void verifyDashboardWidgets() {

            Assert.assertTrue(
                    dashboardPage.areDashboardWidgetsDisplayed(),
                    "Expected dashboard widgets were not displayed"
            );
        }

        // DASH_005
        @Test
        public void verifySidebarMenu() {

            Assert.assertTrue(
                    dashboardPage.isSidebarDisplayed(),
                    "Expected sidebar menu items were not displayed"
            );
        }

        // DASH_006
        @Test
        public void verifyPIMNavigation() {

            dashboardPage.clickPIM();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/pim/"),
                    "PIM page was not opened"
            );
        }

        // DASH_007
        @Test
        public void verifyAdminNavigation() {

            dashboardPage.clickAdmin();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/admin/"),
                    "Admin page was not opened"
            );
        }

        // DASH_008
        @Test
        public void verifyLeaveNavigation() {

            dashboardPage.clickLeave();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/leave/"),
                    "Leave page was not opened"
            );
        }

        // DASH_009
        @Test
        public void verifyTimeNavigation() {

            dashboardPage.clickTime();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/time/"),
                    "Time page was not opened"
            );
        }

        // DASH_010
        @Test
        public void verifyRecruitmentNavigation() {

            dashboardPage.clickRecruitment();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/recruitment/viewCandidates"),
                    "Recruitment page was not opened"
            );
        }

        // DASH_011
        @Test
        public void verifyPerformanceNavigation() {

            dashboardPage.clickPerformance();

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/performance/"),
                    "Performance page was not opened"
            );
        }
        //DASH_012
        @Test
        public void verifyUserdropdownClick(){
            dashboardPage.userdropDown();


        }

        //DASH_013
        @Test
        public void  verifyUserLogout(){
            dashboardPage.userLogout();
            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/auth/login"),
                    "User did not logout");
        }
    }


}
