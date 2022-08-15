package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class OverviewPage extends PageObjectBase {

    //region Constructor
    public OverviewPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(className="subheader")
    private WebElement titlePage;

    @FindBy(xpath="//div[@id='checkout_summary_container']//a[@class='btn_action cart_button']")
    private WebElement finishButton;

    @FindBy(xpath="//div[@id='checkout_summary_container']//div[@class='summary_subtotal_label']")
    private WebElement itemTotalPrice;

    @FindBy(xpath="//div[@id='checkout_summary_container']//div[@class='summary_tax_label']")
    private WebElement taxPrice;

    @FindBy(xpath="//div[@id='checkout_summary_container']//div[@class='summary_total_label']")
    private WebElement totalPrice;

    @FindBy(id="item_1_title_link")
    private WebElement productBlackTShirt;

    @FindBy(id="item_3_title_link")
    private WebElement productRedTShirt;
    //endregion Mapping Elements


    //region Mapping Events
    public String GetTitle() {
        return this.titlePage.getText();
    }

    public boolean IsOverviewPageDisplayed() {
        return this.titlePage.isDisplayed();
    }

    public FinishPage ClickFinishButton() {
        this.finishButton.click();
        return new FinishPage(_driver);
    }

    public String GetProductBlackNameLabel() {
        return this.productBlackTShirt.getText();
    }

    public String GetProductRedNameLabel() {
        return this.productRedTShirt.getText();
    }

    public Double GetItemTotalPrice() {
        String item = this.itemTotalPrice.getText().replaceAll("[^0-9.]","");
        return Double.parseDouble(item);
    }

    public Double GetTaxPrice() {
        String tax = this.taxPrice.getText().replaceAll("[^0-9.]","");
        return Double.parseDouble(tax);
    }

    public Double GetTotalPrice() {
        String price = this.totalPrice.getText().replaceAll("[^0-9.]","");
        return Double.parseDouble(price);
    }
    //endregion Mapping Events
}
