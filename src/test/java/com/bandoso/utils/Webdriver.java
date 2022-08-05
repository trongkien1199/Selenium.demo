package com.bandoso.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Webdriver {
    public static WebDriver getChromeDriver(){
//        ========================= Run physical driver ==================================
//        //For Chrome driver
//        String path = System.getProperty("user.dir");
//        String chromeDriverPath = path + "/selenium-grid/chromedriver";
//        System.setProperty("webdriver.chromer.driver", chromeDriverPath);
//        System.out.println("get web");
//        return new ChromeDriver();
//        For FF driver
//        String path = System.getProperty("user.dir");
//        String firefoxDriverPath = path + "/selenium-grid/geckodriver";
//        System.out.println(firefoxDriverPath);
//        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
//        System.out.println("get web");
//        return new FirefoxDriver();
//         For Edge
//        String path = System.getProperty("user.dir");
//        String edgeDriverPath = path + "/selenium-grid/msedgedriver";
//        System.out.println(edgeDriverPath);
//        System.setProperty("webdriver.edge.driver", edgeDriverPath);
//        return new EdgeDriver();

//        ========================================== Use selenium grid ==================================
        RemoteWebDriver remoteWebDriver;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("firefox");

        try{
            remoteWebDriver = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
        return  remoteWebDriver;
    }
    public static WebDriver getDriver(String browserName){
        RemoteWebDriver remoteWebDriver;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        if(browserName.equalsIgnoreCase("chrome")) {
            desiredCapabilities.setBrowserName("chrome");
        }else if(browserName.equalsIgnoreCase("firefox")){
            desiredCapabilities.setBrowserName("firefox");
        }else{
            desiredCapabilities.setBrowserName("MicrosoftEdge");
        }
        try{
            remoteWebDriver = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
        return  remoteWebDriver;
    }
}
