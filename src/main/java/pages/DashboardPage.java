package pages;

import factory.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.LoggerUtil;
import utilities.ReportLogger;
import utilities.WaitUtility;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WaitUtility wait;

    //for logs
    //adding logger initialization for logging
    private static final Logger logger=
            LoggerUtil.getLogger(DashboardPage.class);


    private By DashboardTitle= By.xpath("//h6[text()=\"Dashboard\"]");

    //Dashboard widgets

    private By timeAtWorkWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Time at Work']");
    private By myActionsWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='My Actions']");
    private By quickLaunchWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Quick Launch']");

    /*private By buzzWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Buzz Latest Posts']");

    private By leaveTodayWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Employees on Leave Today']");

    private By employeeDistributonWidget =
            By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Employee Distribution by Sub Unit']");

    private By employeeDistributionLocationWidget=By.xpath("//p[contains(@class,'oxd-text oxd-text--p') and normalize-space()='Employee Distribution by Location']");
    */
    //side menu locators

    private By adminMenu = By.xpath("//span[normalize-space()='Admin']");
    private By pimMenu = By.xpath("//span[normalize-space()='PIM']");
    private By leaveMenu = By.xpath("//span[normalize-space()='Leave']");
    private By timeMenu = By.xpath("//span[normalize-space()='Time']");
    private By recruitmentMenu = By.xpath("//span[normalize-space()='Recruitment']");
    private By performanceMenu = By.xpath("//span[normalize-space()='Performance']");

    //user profile
    private By userDropdown =
            By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");

    private By logout =
            By.xpath("//a[normalize-space()='Logout']");



    public DashboardPage(WebDriver driver){

        this.driver=driver;
        this.wait=new WaitUtility(driver);
        logger.info("Dashboard page initialized");
        ReportLogger.info("Dashboard page initialized");

    }

    //verify dashboard title
    public boolean isDashboardDisplayed(){
        logger.info("Checking dashboard displayed");
        ReportLogger.info("Dashboard displayed check");

        try{
            return wait.waitForElementVisible(DashboardTitle).isDisplayed();

        }catch (Exception e){
            return  false;
        }


    }

    //verify dashboard url
    public boolean isDashboardUrlDisplayed() {
        logger.info("Dashboard url displayed check.");
        ReportLogger.info("Dashboard url displayed check.");
        return driver.getCurrentUrl().contains("/dashboard/index");

    }

    //verify dashboard widgets
    public boolean areDashboardWidgetsDisplayed() {
        logger.info("Dashboard widget displayed check: timeAtWork, myActions, quickLaunch ");
        ReportLogger.info("Dashboard widget displayed check: timeAtWork, myActions, quickLaunch");
        try {
            boolean timeAtWork = wait.waitForElementVisible(timeAtWorkWidget).isDisplayed();
            boolean myActions = wait.waitForElementVisible(myActionsWidget).isDisplayed();
            boolean quickLaunch = wait.waitForElementVisible(quickLaunchWidget).isDisplayed();
            return timeAtWork && myActions && quickLaunch;
        }
        catch (Exception e) {
            System.out.println( "Dashboard widget validation failed: " + e.getMessage() );
            ReportLogger.fail("Dashboard widget validation failed");
            return false; }
    }

    //verfiy sidebar
    public boolean isSidebarDisplayed() {
        logger.info("Dashboard sidebar menu display check: adminMenu, pimMenu, leaveMenu, timeMenu");
        ReportLogger.info("Dashboard sidebar menu display check: adminMenu, pimMenu, leaveMenu, timeMenu");
        try {
            return wait.waitForElementVisible(adminMenu).isDisplayed() &&
                    wait.waitForElementVisible(pimMenu).isDisplayed() &&
                    wait.waitForElementVisible(leaveMenu).isDisplayed() &&
                    wait.waitForElementVisible(timeMenu).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //verify PIM click
    public void clickPIM() {
        logger.info("PIM button clicking.");
        ReportLogger.info("PIM button clicking.");
        wait.waitForElementClickable(pimMenu).click();
        logger.debug("PIM button working");
        ReportLogger.info("PIM button working.");
    }

    //verify Admin click
    public void clickAdmin(){
        logger.info("Admin button clicking.");
        ReportLogger.info("Admin button clicking.");
        wait.waitForElementClickable(adminMenu).click();
        logger.debug("Admin button working");
        ReportLogger.info("Admin button working");
    }

    //verify Leave click
    public void clickLeave() {
        logger.info("Leave button clicking.");
        ReportLogger.info("Leave button clicking.");
        wait.waitForElementClickable(leaveMenu).click();
        logger.debug("Leave button working.");
        ReportLogger.info("Leave button working.");
    }

    //verify Time click
    public void clickTime() {
        logger.info("Time button clicking.");
        ReportLogger.info("Time button clicking.");
        wait.waitForElementClickable(timeMenu).click();
        logger.debug("Time button working");
        ReportLogger.info("Time button working.");
    }

    //verify Recruitment click
    public void clickRecruitment() {
        logger.info("Recruitment button clicking.");
        ReportLogger.info("Recruitment button clicking.");
        wait.waitForElementClickable(recruitmentMenu).click();
        logger.debug("Recruitment button working");
        ReportLogger.info("Recruitment button working.");
    }

    //verify performance click
    public void clickPerformance() {
        logger.info("Performance button clicking...");
        wait.waitForElementClickable(performanceMenu).click();
        logger.debug("Performance button working");
        ReportLogger.info("Performance button working.");
    }

    //verify user profile actions

    public void userdropDown(){
        logger.info("userDropdown button clicking...");
        wait.waitForElementClickable(userDropdown).click();
        logger.debug("userDropdown button working");
        ReportLogger.info("userDropdown button working.");
    }

    public void userLogout(){
        logger.info("Logout operation checking...");
        userdropDown();
        wait.waitForElementClickable(logout).click();
        logger.debug("Logout Successful");
        ReportLogger.info("Logout Successful");
    }











}
