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

        ChromeOptions options = new ChromeOptions();

        // общий набор для CI
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        // читаем системное свойство -Dheadless=true|false (по умолчанию true в CI)
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        if (headless) {
            // для новых Chrome лучше "new" режим
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        logger.info("Browser started (headless={}) and maximized", headless);
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
