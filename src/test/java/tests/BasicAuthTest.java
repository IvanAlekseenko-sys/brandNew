package tests;

import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import utils.ConfigReader;

public class BasicAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthTest.class);

    @Test(description = "Verify successful basic authentication")
    public void testSuccessfulBasicAuth() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String user = ConfigReader.getProperty("user");
        String pass = ConfigReader.getProperty("pass");

        // The URL format is https://user:pass@domain/path
        String authUrl = baseUrl.replace("https://", "https://" + user + ":" + pass + "@") + "/basic_auth";

        driver.get(authUrl);
        Assert.assertEquals(new BasicAuthPage(driver).getSuccessMessage(), "Congratulations! You must have the proper credentials.");
        logger.info("=== Test passed: testSuccessfulBasicAuth ===");
    }
}
