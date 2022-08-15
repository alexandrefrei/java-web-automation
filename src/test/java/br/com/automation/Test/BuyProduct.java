package br.com.automation.Test;

import br.com.automation.DTO.*;
import br.com.automation.pageObjects.*;
import br.com.automation.SetupBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class BuyProduct extends SetupBase {

    @Test
    public void PurchaseAllTShirts()
    {
        //region Expected Values Parameter

        String username = "standard_user";
        String password = "secret_sauce";
        String firstName = "John";
        String lastName = "Doe";
        String zipCode = "12124";
        String expectedFirstProductName = "Test.allTheThings() T-Shirt (Red)";
        String expectedSecondProductName = "Sauce Labs Bolt T-Shirt";
        String expectedOrderMessage = "THANK YOU FOR YOUR ORDER";
        String expectedOrderDispatchedMessage = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        //protected final Logger logger = LogManager.getLogger();

        Double expectedTotalItem = 31.98;
        Double expectedTax = 2.56;
        List<Double> listTotalPrice = new ArrayList<>();

        //endregion Expected Values Parameter

        //logger.info(String.format("Navigating to URL: %s", url));
        LoginPage loginPage = new LoginPage(SeleniumDriver);

        //Assert the fields Username and Password is Displayed
        Assert.assertTrue(loginPage.isUsernameDisplayed(),"Field Username");
        Assert.assertTrue(loginPage.isPasswordDisplayed(),"Field Password");

        loginPage.FillUsername(username);
        loginPage.FillPassword(password);

        HomePage homePage = loginPage.ClickLoginWithSuccess();

        //Assert that Home Page is displayed and the Title is correct
        Assert.assertTrue(homePage.IsHomeDisplayed(),"Home Page Displayed");
        Assert.assertEquals(homePage.GetTitle(),"Products","Title in Home Page");


        //Selecting the first product by product name
        ProductDetailsPage productDetailsPage = homePage.SelectProduct(expectedFirstProductName);
        String firstProductSelected = productDetailsPage.GetProductLabel();
        Assert.assertEquals(firstProductSelected,expectedFirstProductName, "First Product Selected");

        productDetailsPage.ClickAddToCartButton();

        int itemCart = productDetailsPage.GetItemCart();
        Assert.assertEquals(itemCart,1, "Number of Item in the Cart");
        Double price = productDetailsPage.GetPriceProduct();
        listTotalPrice.add(price);
        homePage = productDetailsPage.ClickBackButton();


        //Selecting the second product by position
        productDetailsPage = homePage.SelectBlackTshirt();
        String secondProductSelected = productDetailsPage.GetProductLabel();
        Assert.assertEquals(secondProductSelected,expectedSecondProductName, "Second Product Selected");

        productDetailsPage.ClickAddToCartButton();

        int itemCart2 = productDetailsPage.GetItemCart();
        Assert.assertEquals(itemCart2,2, "Number of Item in the Cart");
        Double price2 = productDetailsPage.GetPriceProduct();
        listTotalPrice.add(price2);
        homePage = productDetailsPage.ClickBackButton();


        //In the Home Page verifies the total item in the Cart
        int itemCartHome = homePage.GetItemCart();
        Assert.assertEquals(itemCartHome,2, "Number of Item in the Cart");
        CartPage cartPage = homePage.ClickCartButton();


        //In the Cart Page assert some values in the UI
        Assert.assertTrue(cartPage.IsCartPageDisplayed(),"Cart Page Displayed");
        Assert.assertEquals(cartPage.GetTitle(),"Your Cart", "Cart Page Title");
        YourInformationPage yourInformationPage = cartPage.ClickCheckoutButton();

        //In the Checkout: Your Information page fill all the information
        Assert.assertTrue(yourInformationPage.IsYourInformationPageDisplayed(), "Checkout: Your Information Page Displayed");
        Assert.assertEquals(yourInformationPage.GetTitle(),"Checkout: Your Information", "Checkout: Your Information Page");
        DtoUser user = new DtoUser(firstName, lastName, zipCode);
        yourInformationPage.FillRegistrationForm(user);
        OverviewPage overviewPage = yourInformationPage.ClickContinueButton();

        //In the Checkout: Overview page validate prices and if the products were added correctly
        Assert.assertTrue(overviewPage.IsOverviewPageDisplayed(),"Checkout: Overview Page Displayed");
        Assert.assertEquals(overviewPage.GetTitle(),"Checkout: Overview","Checkout: Overview Page");

        Assert.assertEquals(overviewPage.GetProductRedNameLabel(),expectedFirstProductName,"First Product Selected");
        Assert.assertEquals(overviewPage.GetProductBlackNameLabel(),expectedSecondProductName,"Secondo Product Selected");

        //Performing some validations against price.
        // Validating total price item against the sum of products price
        // Validating values against the information provide by UI
        Double sumProductPrice = listTotalPrice.stream().mapToDouble(Double::doubleValue).sum();
        Assert.assertEquals(sumProductPrice,expectedTotalItem,"Total Item Price Sum");
        Assert.assertEquals(overviewPage.GetItemTotalPrice(),expectedTotalItem,"Total Item Price UI");
        Assert.assertEquals(overviewPage.GetTaxPrice(),expectedTax,"Total Tax");
        Assert.assertEquals(overviewPage.GetTotalPrice(),expectedTotalItem + expectedTax, "Total Price plus Tax");

        FinishPage finishPage = overviewPage.ClickFinishButton();

        //In the Finish Page validate if the all flow was complete with success
        Assert.assertTrue(finishPage.IsFinishPageDisplayed(),"Finish Page Displayed");
        Assert.assertEquals(finishPage.GetTitle(),"Finish", "Finish Page Title");

        Assert.assertEquals(finishPage.GetOrderCompleteMessage(),expectedOrderMessage,"Message Order");
        Assert.assertEquals(finishPage.GetOrderDispatchedMessage(),expectedOrderDispatchedMessage,"Message Order Dispatched");

        //Click in the Logout to return to Login Page
        finishPage.ClickTab();
        //Assert.assertTrue(finishPage.IsLogoutButtonDisplayed(), "Login Page Displayed");
        loginPage = finishPage.ClickLogout();

        //Assert that back to Login Page
        Assert.assertTrue(loginPage.isUsernameDisplayed(),"Field Username");
        Assert.assertTrue(loginPage.isPasswordDisplayed(),"Field Password");

    }
}


