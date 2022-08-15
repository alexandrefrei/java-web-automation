package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ProductDetailsPage extends PageObjectBase {

    //region Constructor
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(className="inventory_details_name")
    private WebElement productLabel;

    @FindBy(xpath="//div[@id='inventory_item_container']//button[@class='btn_primary btn_inventory']")
    private WebElement addToCartButton;

    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;

    @FindBy(xpath="//div[@id='shopping_cart_container']//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement itemCartNumber;

    @FindBy(className = "inventory_details_back_button")
    private WebElement backButton;
    //endregion Mapping Elements

    //region Mapping Events
    public String GetProductLabel() {
        return this.productLabel.getText();
    }

    public void ClickAddToCartButton() {
        this.addToCartButton.click();
    }

    public HomePage ClickBackButton() {
        this.backButton.click();
        return new HomePage(_driver);
    }

    public Double GetPriceProduct() {
        String price = this.productPrice.getText();

        //price is returning $29,99, with substring I removed the character $ and convert to Double
        return Double.parseDouble(price.substring(1,price.length()));
    }

    public int GetItemCart() {

        return Integer.parseInt(itemCartNumber.getText());
    }
    //endregion Mapping Events
}
