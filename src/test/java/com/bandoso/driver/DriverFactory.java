package com.bandoso.driver;

import com.bandoso.utils.Webdriver;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private WebDriver webDriver;

    //Singleton Design Pattern
    WebDriver getWebDriver() {
        if(webDriver == null){
            webDriver = Webdriver.getChromeDriver();
        }
        return webDriver;
    }
    WebDriver getWebDriver(String browserName) {
        if(webDriver == null){
            webDriver = Webdriver.getDriver(browserName);
        }
        return webDriver;
    }
    void quitDriver(){
        if (webDriver!=null){
            webDriver.quit();
            webDriver = null;
        }
    }
}
