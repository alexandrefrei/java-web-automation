package br.com.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetupBase {

    protected static WebDriver SeleniumDriver;
    private static String host = " https://www.saucedemo.com/";

    @BeforeTest
    public void InitializeTestWebDriver() {
        WebDriverManager.chromedriver().setup();
        if(SeleniumDriver == null)
        {
            SeleniumDriver = new ChromeDriver();
        }

        SeleniumDriver.get(host);
        SeleniumDriver.manage().window().maximize();
    }

    @AfterTest
    public void Close() {
        if(SeleniumDriver != null)
        {
            //Close only the tab that is used by webdriver
            SeleniumDriver.close();
            //Close the browser
            SeleniumDriver.quit();
        }
    }
}
