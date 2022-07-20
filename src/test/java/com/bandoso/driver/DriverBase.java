package java.com.bubble.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.*;

public class DriverBase {
    private static List<DriverFactory> webdriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void initWebdriverObject(){
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory webdriverThread = new DriverFactory();
            webdriverThreadPool.add(webdriverThread);
            return webdriverThread;
        });
    }
    public static WebDriver getDriver(){
        return driverThread.get().getWebDriver();
    }
    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        for(DriverFactory driver : webdriverThreadPool){
            driver.quitDriver();
        }
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result){
//        getDriver().manage().deleteAllCookies();
        //Get screenShot
        if(result.getStatus() == ITestResult.FAILURE){
            //1. Get the test method name
            String testMethod = result.getName();
            //2.Declare file location
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int month= calendar.get(Calendar.MONTH);
            int date = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec =calendar.get(Calendar.SECOND);
            String takenTime = y + "-" + (month+1) + "-" + date + "-" + hr + "-" + min + "-" + sec;

            String fileLocation = System.getProperty("user.dir").concat("/screenshot/" + testMethod +takenTime+ ".png");
            System.out.println(fileLocation);
//            3.Save screen shot in System
            File screenShot = ((TakesScreenshot) driverThread.get().getWebDriver()).getScreenshotAs(OutputType.FILE);
            try{
                FileUtils.copyFile(screenShot, new File(fileLocation));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
