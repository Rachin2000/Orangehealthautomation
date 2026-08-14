package utilities;
import factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.JavascriptExecutor;
public class ScreenshotUtility {

        public static String captureScreenshot(String testName) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String filePath =
                    "test-output/screenshots/"
                            + testName + "_"
                            + timestamp + ".png";

            try {

                TakesScreenshot screenshot =
                        (TakesScreenshot) DriverFactory.driver();

                File sourceFile =
                        screenshot.getScreenshotAs(OutputType.FILE);

                File destinationFile =
                        new File(filePath);

                FileUtils.copyFile(sourceFile, destinationFile);

                System.out.println(
                        "Screenshot saved: " + filePath
                );

                return filePath;

            } catch (IOException e) {

                e.printStackTrace();
                return null;
            }
        }

        //since few images are not loaded while screenshot capture so, i'll create a waitforLoad method
        private static void waitForPageLoad() {

            JavascriptExecutor js =
                    (JavascriptExecutor) DriverFactory.driver();

            int timeout = 10;

            for (int i = 0; i < timeout; i++) {

                try {

                    String readyState =
                            js.executeScript(
                                    "return document.readyState"
                            ).toString();

                    if (readyState.equals("complete")) {
                        System.out.println(
                                "Page loading completed."
                        );
                        return;
                    }

                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

