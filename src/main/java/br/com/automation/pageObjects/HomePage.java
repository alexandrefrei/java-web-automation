package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends PageObjectBase {

    //region Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
    //endregion

    //region Mapping Elements
    @FindBy(className="product_label")
    private WebElement titlePage;

    @FindBy(id="item_1_title_link")
    private WebElement productBlackTShirt;

    @FindBys({@FindBy(xpath = "//div[@id='inventory_container']//div[@class='inventory_item_name']")})
    private List<WebElement> listProducts;

    @FindBy(xpath="//div[@id='shopping_cart_container']//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement itemCartNumber;

    @FindBy(id="shopping_cart_container")
    private WebElement cartButton;

    @FindBys({@FindBy(xpath = "//div[@id='inventory_container']//img[@class='inventory_item_img']")})
    private List<WebElement> listImageProducts;
    //endregion Mapping Elements


    //region Mapping Events

    // Here there are two ways to select product:
    // The first one it's used the id and clicking directly in the product
    // The second one it's used stream to search the product name in the product list

    public ProductDetailsPage SelectBlackTshirt() {
        this.productBlackTShirt.click();
        return new ProductDetailsPage(_driver);
    }

    public ProductDetailsPage SelectProduct(String productName) {
        WebElement product = listProducts.stream().filter(p -> p.getText().contains(productName)).findFirst().orElse(null);
        product.click();
        return new ProductDetailsPage(_driver);
    }

    public int VerifyImageIsLoaded() {
        int validImage = 6;
        for (WebElement elem :listImageProducts)
        {
            //If the image have Height and Width equals zero then the image is broken
            if(elem.getAttribute("naturalHeight").equals("0") &&elem.getAttribute("naturalWidth").equals("0") )
            {
                validImage--;
            }
        }
        return validImage;
    }

    public int GetItemCart() {
        return Integer.parseInt(itemCartNumber.getText());
    }

    public CartPage ClickCartButton() {
        this.cartButton.click();
        return new CartPage(_driver);
    }

    public String GetTitle() {
        return this.titlePage.getText();
    }

    public boolean IsHomeDisplayed() {
        return this.titlePage.isDisplayed();
    }
    //endregion Mapping Events
}