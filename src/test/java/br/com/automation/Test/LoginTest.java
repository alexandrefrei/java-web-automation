package br.com.automation.Test;

import br.com.automation.SetupBase;
import br.com.automation.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends SetupBase {

    @Test
    public void LoginWithUserLockout()
    {
        String username = "locked_out_user";
        String password = "secret_sauce";
        String expectedMessageError = "Epic sadface: Sorry, this user has been locked out.";

        LoginPage loginPage = new LoginPage(SeleniumDriver);

        //Assert the fields Username and Password is Displayed
        Assert.assertTrue(loginPage.isUsernameDisplayed(),"Field Username");
        Assert.assertTrue(loginPage.isPasswordDisplayed(),"Field Password");

        loginPage.FillUsername(username);
        loginPage.FillPassword(password);
        loginPage.ClickLoginWithError();
        String CurrentErrorMessage = loginPage.GetErrorMessage();

        Assert.assertEquals(CurrentErrorMessage,expectedMessageError,"Error Message");
    }
}



