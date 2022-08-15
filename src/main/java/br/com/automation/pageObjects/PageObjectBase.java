package br.com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectBase {

    protected WebDriver _driver;

    public PageObjectBase(WebDriver driver) {
        _driver = driver;
        PageFactory.initElements(_driver, this);
    }
}
