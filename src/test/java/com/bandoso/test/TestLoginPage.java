package java.com.bubble.test;

import com.bubble.driver.DriverBase;
import com.bubble.model.pages.DashboardPage;
import com.bubble.model.pages.LoginPage;
import com.bubble.utils.Webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.Element;
import java.time.Duration;
import java.util.List;

public class TestLoginPage extends DriverBase {
    private String loginUrl = "https://agent-develop.bubbles.vn/";
    private String username = "chaunnt@nexlesoft.com";
    private String password = "123456";
    private String expectedUsernameErr = "Vui lòng nhập tên đăng nhập!";

    private String expectedPasswordErr = "Vui lòng nhập mật khẩu!";
    private String expectedErrColor = "rgba(255, 77, 79, 1)";
    private String expectedAlertTitle = "Đăng nhập không thành công!";
    private String expectedAlertContent = "Tài khoản không tồn tại hoặc sai mật khẩu";
    @Test(priority = 1)
    public void registerUser(){
        System.out.println("Register User");
    }
    @Test(priority = 2)
    public void loginWithEmptyUserAndPass(){
        WebDriver driver = getDriver();
        driver.get(loginUrl);
        driver.manage().window().maximize();
        LoginPage loginPage  = new LoginPage(driver);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        List<WebElement>  listExplainErrEles = loginPage.explainErrEles();
        // check if 2 wrong items
        Assert.assertEquals(listExplainErrEles.size(),2);
        String actualUsernameErr = listExplainErrEles.get(0).getText();
        String actualPasswordErr = listExplainErrEles.get(1).getText();
        System.out.println(loginPage.explainErrEles().get(0).getCssValue("color"));
        //check if right color
        for(WebElement e : listExplainErrEles){
            Assert.assertEquals(e.getCssValue("color"), expectedErrColor);
        }
        // check if right error text
        Assert.assertEquals(actualUsernameErr, expectedUsernameErr);
        Assert.assertEquals(actualPasswordErr, expectedPasswordErr);

    }
    @Test(priority = 3)
    public void loginWithEmptyUser() throws InterruptedException {
        WebDriver driver = getDriver();
//        driver.get(loginUrl);
//        driver.manage().window().maximize();
        LoginPage loginPage  = new LoginPage(driver);
        loginPage
                .inputUsername(username)
                .clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        //check if there is only one wrong item
        Assert.assertEquals(loginPage.explainErrEles().size(),1);
        String actualPasswordErr = loginPage.explainErrEles().get(0).getText();
        // check if right error text
        Assert.assertEquals(actualPasswordErr, expectedPasswordErr);
        //check if right color
        Assert.assertEquals(loginPage.explainErrEles().get(0).getCssValue("color"), expectedErrColor);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
        Thread.sleep(1500);
        loginPage.usernameClearEle().click();

    }
    @Test(priority = 4)
    public void loginWithEmptyPassword() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10000);
//        driver.get(loginUrl);
        LoginPage loginPage  = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOf(loginPage.password()));
        loginPage
                .inputPassword(password)
                .clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        //check if there is only one wrong item
        Assert.assertEquals(loginPage.explainErrEles().size(),1);
        //check if right text
        String actualUsername = loginPage.explainErrEles().get(0).getText();
        Assert.assertEquals(actualUsername, expectedUsernameErr);
        //check if right color
        Assert.assertEquals(loginPage.explainErrEles().get(0).getCssValue("color"), expectedErrColor);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
        loginPage.passwordClearEle().click();
        Thread.sleep(1500);

    }

    @Test(priority = 5)
    public void loginWithInvalidUser() throws InterruptedException {
        WebDriver driver = getDriver();
//        driver.get(loginUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        LoginPage loginPage  = new LoginPage(driver);
        loginPage
                .inputUsername(username)
                .inputPassword("password")
                .clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.getAlertXpath()));
        String actualAlertTitle = driver.findElement(loginPage.getAlertTitle()).getText();
        String actualAlertContent = driver.findElement(loginPage.getAlertContent()).getText();
        Assert.assertEquals(actualAlertTitle, expectedAlertTitle);
        Assert.assertEquals(actualAlertContent, expectedAlertContent);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
        loginPage.passwordClearEle().click();
        loginPage.usernameClearEle().click();
        Thread.sleep(1500);
    }
    @Test(priority = 6)
    public void loginWithCorrectUser()  {
        WebDriver driver = getDriver();
//        driver.get(loginUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        LoginPage loginPage  = new LoginPage(driver);
       loginPage
                .inputUsername(username)
                .inputPassword(password)
                .clickLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.getUserNameInfoXpath()));
        String actualUsername = dashboardPage.userNameInfo().getText();
        String actualWelcomeText = dashboardPage.welcomeText().getText();
        String expectedUsername = "Thanh Test";
        String expectedWelcomeText = "Xin chào";
        Assert.assertEquals(actualUsername, expectedUsername);
        Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
        driver.quit();
    }
}
