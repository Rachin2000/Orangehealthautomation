
package factory;

import utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // Separate WebDriver instance for each thread
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver driver() {

        // Create driver only if current thread doesn't already have one
        if (driver.get() == null) {

            String browser = ConfigReader.getProperty("browser");

            switch (browser.toLowerCase()) {

                case "chrome":
                    driver.set(new ChromeDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;

                default:
                    throw new RuntimeException(
                            "Browser not supported: " + browser
                    );
            }

            driver.get().manage().window().maximize();
        }

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            // Remove WebDriver from current thread
            driver.remove();
        }
    }
}
