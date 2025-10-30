package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement heading;

    /**
     * Constructor for BasePage.
     *
     * @param driver The WebDriver instance from the test.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText() {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }
}
