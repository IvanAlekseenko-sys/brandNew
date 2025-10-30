package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import static org.testng.Assert.assertEquals;

public class OpenHomePageTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(OpenHomePageTest.class);

    @Test
    public void openHomePage() {
        logger.info("=== Test started: openHomePage ===");
        openBaseUrl();
        HomePage homePage = new HomePage(driver);
        String heading = homePage.getHeadingText();
        logger.info("Page heading: {}", heading);
        assertEquals(heading, "Welcome to the-internet");
        logger.info("=== Test passed: openHomePage ===");
    }
}
