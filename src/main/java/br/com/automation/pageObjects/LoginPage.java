package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LoginPage extends PageObjectBase {

    //region Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(id="user-name")
    private WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(className = "btn_action")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='login-box']//h3")
    private WebElement errorMessage;
    //endregion Mapping Elements

    //region Mapping Events
    public boolean isUsernameDisplayed() {
        return this.username.isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return this.password.isDisplayed();
    }

    public void FillUsername(String name) {
        this.username.clear();
        this.username.sendKeys(name);
    }

    public void FillPassword(String pass) {
        this.password.clear();
        this.password.sendKeys(pass);
    }

    public String GetErrorMessage() {
        return this.errorMessage.getText();
    }

    public void ClickLoginWithError() {
        this.loginButton.click();

    }

    public HomePage ClickLoginWithSuccess() {
        this.loginButton.click();
        return new HomePage(_driver);
    }
    //endregion Mapping Events
}
