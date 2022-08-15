package br.com.automation.Test;

import br.com.automation.SetupBase;
import br.com.automation.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import br.com.automation.util.*;

public class VerifyImageTest extends SetupBase {

    @Test
    public void VerifyImageIsLoaded() throws Exception
    {
        //region Expected Values Parameter

        String username = "problem_user";
        String password = "secret_sauce";

        //endregion Expected Values Parameter

        LoginPage loginPage = new LoginPage(SeleniumDriver);

        //Assert the fields Username and Password is Displayed
        Assert.assertTrue(loginPage.isUsernameDisplayed(),"Field Username");
        Assert.assertTrue(loginPage.isPasswordDisplayed(),"Field Password");

        loginPage.FillUsername(username);
        loginPage.FillPassword(password);
        HomePage homePage = loginPage.ClickLoginWithSuccess();

        //Assert that Home Page is loaded and the Title is correct
        Assert.assertTrue(homePage.IsHomeDisplayed(),"Home Page Displayed");
        Assert.assertEquals(homePage.GetTitle(),"Products","Title in Home Page");

        /*
         * Correctly have 6 images that should be loaded correctly.
         * If one image is broken the test should fail
         *
         *
         * In this case that we have 6 image broken the test will fail
         *
         *
         */
        int imageLoadedCorrectly = homePage.VerifyImageIsLoaded();

        TakeScreenshot.TakeSnapShot(SeleniumDriver, "c:\\tmp\\screenshot.png");
        Assert.assertEquals(imageLoadedCorrectly,6,"Product Image Loaded");


    }

}
