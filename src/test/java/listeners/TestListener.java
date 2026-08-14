package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportManager;
import utilities.LoggerUtil;
import utilities.ReportLogger;
import utilities.ScreenshotUtility;

import org.apache.logging.log4j.Logger;


public class TestListener implements ITestListener {
    private ExtentTest test;

    //logger initialization
    private static final Logger logger= LoggerUtil.getLogger(TestListener.class);



    @Override
    public void onStart(ITestContext context){
        logger.info("===============================");
        logger.info("Test suite started: {}",context.getName());
        logger.info("================================");
        ExtentReportManager.getReportInstance();
    }
    @Override
    public void onTestStart(ITestResult result){
        //for logging
        String testName=result.getMethod().getMethodName();
        test=ExtentReportManager.getReportInstance().createTest(testName);
        ReportLogger.setTest(test);

        logger.info("TEST STARTED: {}",testName);
        logger.info("TEST STARTED: {}",testName);

        test= ExtentReportManager.getReportInstance().createTest(result.getMethod().getMethodName());
        test.log(Status.INFO,"Test Execution Started");
    }
    @Override
    public  void onTestSuccess(ITestResult result){
        String testName = result.getMethod().getMethodName();
        test=ExtentReportManager.getReportInstance().createTest(testName);
        ReportLogger.setTest(test);
        logger.info("TEST PASSED: {}",testName);
        ReportLogger.pass("Test Passed");



        System.out.println(
                "TEST PASSED: " + testName
        );

        ScreenshotUtility.captureScreenshot(
                testName + "_PASS"
        );
        test.pass("Test Passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result){
        String testName = result.getMethod().getMethodName();
        test=ExtentReportManager.getReportInstance().createTest(testName);
        ReportLogger.setTest(test);

        logger.error("TEST FAILED: {}",testName);
        ReportLogger.fail("Test failed");

        System.out.println(
                "TEST FAILED: " + testName
        );

        ScreenshotUtility.captureScreenshot(
                testName + "_FAIL"
        );

        test.fail("Test failed");
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        String testName=result.getMethod().getMethodName();
        test=ExtentReportManager.getReportInstance().createTest(testName);
        ReportLogger.setTest(test);

        test.log(Status.SKIP,"Test Skipped");
        logger.warn("TEST SKIPPED: {}",testName);
        logger.warn("Test skipped");
        ReportLogger.info("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context){
        logger.info("================================");
        logger.info("Test Suite Finished: {}",context.getName());
        logger.info("================================");
        ExtentReportManager.getReportInstance().flush();
    }

}
