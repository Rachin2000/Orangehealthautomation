package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getReportInstance(){
    if(extent==null)

    {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Orange HRM Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("TestUser", "Rachin");
    }
    return extent;
}

}
