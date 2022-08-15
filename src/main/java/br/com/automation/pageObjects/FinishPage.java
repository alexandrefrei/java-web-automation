package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class FinishPage extends PageObjectBase {

    //region Constructor
    public FinishPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(className="subheader")
    private WebElement titlePage;

    @FindBy(xpath="//div[@id='checkout_complete_container']//div[@class='complete-text']")
    private WebElement CompleteMessage;

    @FindBy(xpath="//div[@id='checkout_complete_container']//h2[@class='complete-header']")
    private WebElement OrderCompleteMessage;

    @FindBy(id="logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className="bm-burger-button")
    private WebElement tabLink;
    //endregion Mapping Elements

    //region Mapping Events
    public String GetTitle() {
        return this.titlePage.getText();
    }

    public boolean IsFinishPageDisplayed() {
        return this.titlePage.isDisplayed();
    }

    public String GetOrderCompleteMessage() {
        return this.OrderCompleteMessage.getText();
    }

    public String GetOrderDispatchedMessage() {
        return this.CompleteMessage.getText();
    }

    public void ClickTab() {
        this.tabLink.click();
    }

    public boolean IsLogoutButtonDisplayed() {
        return this.logoutLink.isDisplayed();
    }
    public LoginPage ClickLogout() {
        this.logoutLink.click();
        return new LoginPage(_driver);
    }
}
