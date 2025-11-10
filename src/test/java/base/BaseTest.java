package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        ChromeOptions opts = new ChromeOptions();
        if (headless) {
            opts.addArguments("--headless=new", "--window-size=1920,1080");
        } else {
            opts.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(opts);
        logger.info("Browser started{}", headless ? " (headless)" : " and maximized");
    }


protected void openBaseUrl() {
    String baseUrl = ConfigReader.getProperty("baseUrl");
    logger.info("Navigating to: {}", baseUrl);
    driver.get(baseUrl);
}

@AfterMethod
public void tearDown() {
    if (driver != null) {
        driver.quit();
        logger.info("Browser closed");
    }
}

}
