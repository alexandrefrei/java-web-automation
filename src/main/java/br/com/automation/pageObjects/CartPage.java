package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class CartPage extends PageObjectBase {

    //region Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(className="subheader")
    private WebElement titlePage;

    @FindBy(xpath="//div[@id='cart_contents_container']//a[@class='btn_action checkout_button']")
    private WebElement checkoutButton;
    //endregion Mapping Elements

    //region Mapping Events
    public String GetTitle() {
        return this.titlePage.getText();
    }

    public boolean IsCartPageDisplayed() {
        return this.titlePage.isDisplayed();
    }

    public YourInformationPage ClickCheckoutButton() {
        this.checkoutButton.click();
        return new YourInformationPage(_driver);
    }
    //endregion Mapping Events
}
