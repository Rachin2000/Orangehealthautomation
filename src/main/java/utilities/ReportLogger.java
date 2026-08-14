package utilities;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportLogger {



        private static ThreadLocal<ExtentTest> extentTest =
                new ThreadLocal<>();

        public static void setTest(ExtentTest test) {
            extentTest.set(test);
        }

        public static ExtentTest getTest() {
            return extentTest.get();
        }

        public static void info(String message) {

            if (extentTest.get() != null) {
                extentTest.get().log(Status.INFO, message);
            }
        }

        public static void pass(String message) {

            if (extentTest.get() != null) {
                extentTest.get().pass(message);
            }
        }

        public static void fail(String message) {

            if (extentTest.get() != null) {
                extentTest.get().fail(message);
            }
        }

        public static void clear() {
            extentTest.remove();
        }
    }

