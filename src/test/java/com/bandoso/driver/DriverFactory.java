package java.com.bubble.driver;

import com.bubble.utils.Webdriver;
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
    void quitDriver(){
        if (webDriver!=null){
            webDriver.quit();
            webDriver = null;
        }
    }
}
