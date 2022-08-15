package br.com.automation.pageObjects;

import br.com.automation.DTO.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class YourInformationPage extends PageObjectBase {

    //region Constructor
    public YourInformationPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion


    //region Mapping Elements
    @FindBy(className="subheader")
    private WebElement titlePage;

    @FindBy(id="first-name")
    private WebElement firstName;

    @FindBy(id="last-name")
    private WebElement lastName;

    @FindBy(id="postal-code")
    private WebElement postaCode;

    @FindBy(xpath="//div[@class='checkout_buttons']//input[@class='btn_primary cart_button']")
    private WebElement continueButton;
    //endregion Mapping Elements

    //region Mapping Events
    public String GetTitle()
    {
        return this.titlePage.getText();
    }

    public boolean IsYourInformationPageDisplayed()
    {
        return this.titlePage.isDisplayed();
    }

    public void FillFirstName(String firstName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
    }

    public void FillLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
    }

    public void FillPostalCode(String postalCode) {
        this.postaCode.clear();
        this.postaCode.sendKeys(postalCode);
    }

    public void FillRegistrationForm(DtoUser user) {
        this.firstName.clear();
        this.firstName.sendKeys(user.getFirstName());
        this.lastName.clear();
        this.lastName.sendKeys(user.getLastName());
        this.postaCode.clear();
        this.postaCode.sendKeys(user.getZipCode());
    }

    public OverviewPage ClickContinueButton() {
        this.continueButton.click();
        return new OverviewPage(_driver);

    }
    //endregion Mapping Events
}
