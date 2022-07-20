package java.com.bubble.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Webdriver {
    public static WebDriver getChromeDriver(){
        String path = System.getProperty("user.dir");
        String ChromeDriverPath = path + "/chromedriver";
        System.setProperty("webriver.chromer.driver", "/home/nexle/bubble-selenium-mvn/chromedriver");
        System.out.println("get web");
        return new ChromeDriver();
    }

}
