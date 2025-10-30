package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasicAuthPage extends BasePage {

    @FindBy(css = ".example p")
    private WebElement successMessage;

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get success message text from the page")
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }

}
