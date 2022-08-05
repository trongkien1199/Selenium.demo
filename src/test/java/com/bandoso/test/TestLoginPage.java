package com.bandoso.test;

import com.bandoso.driver.DriverBase;
import com.bandoso.model.pages.DashboardPage;
import com.bandoso.model.pages.LoginPage;
import com.bandoso.utils.ExcelReaderUtil;
import com.bandoso.utils.Webdriver;
import net.bytebuddy.description.annotation.AnnotationSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.xml.bind.Element;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class TestLoginPage extends DriverBase {
    private String loginUrl = "https://bds-hkd-web-dev.captainbandoso.nexlesoft.com/";
    private String username = "admin.tbi";
    private String password = "Nexle@2020";
    private String expectedUsernameErr = "Please input your username!";

    private String expectedPasswordErr = "Please input your password!";
    private String expectedErrColor = "rgb(a)?\\(255, 77, 79(, 1)?\\)";
    private String expectedAlertTitle = "Tên hoặc mật khẩu không đúng. Vui lòng thử lại";
    private String expectedPageTitleAfterLogin ="BẢN ĐỒ SỐ - HKD";
    private String expectedUsernameAfterLogin = "Super Admin";
    private String expectedWelcomeTextAfteLogin = "Xin chào";
    private By maskXpath = By.xpath("//div[@class='ant-modal-mask']") ;
    private String expectedPopUpContent = "Vui lòng liên hệ người quản trị";
    private int ICON_USER_IDEX = 0;
    private int ICON_PASSWORD_IDEX = 1;
    private WebDriver driver;

    @Test(priority = 1)
//    @Parameters({"browser"})
    public void forgetPassword() throws InterruptedException{
        driver = getDriver();
        driver.get(loginUrl);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.forgotPasswordEle().click();
        //check if the popup display
        Assert.assertEquals(driver.findElements(loginPage.getForgotPasswordPopUp()).size(),1);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(maskXpath));
        String actualPopUpContent = loginPage.forgotPasswordPopUpEle().getText();
        //check if text in alert
        Assert.assertEquals(actualPopUpContent,expectedPopUpContent);
        //Check the mask is present when popup display
        Assert.assertEquals(driver.findElements(maskXpath).size(),1);
        //Close popup
        loginPage.popUpCloseBtnEle().click();
        Thread.sleep(500);
        //Check the mask disappear when dismissing the popup
        Assert.assertEquals(driver.findElements(maskXpath).size(),0);
    }
    @Test(priority = 2)
    public void loginWithEmptyUserAndPass(){
        driver = getDriver();
        LoginPage loginPage  = new LoginPage(driver);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        List<WebElement>  listExplainErrEles = loginPage.explainErrEles();
        List<WebElement> inputIcons = loginPage.inputIconEles();
        // check if 2 wrong items
        Assert.assertEquals(listExplainErrEles.size(),2);
        String actualUsernameErr = listExplainErrEles.get(0).getText();
        String actualPasswordErr = listExplainErrEles.get(1).getText();
        String actualUsernameFieldBorderColor = loginPage.userFieldEle().getCssValue("border-color");
        String actualPasswordFieldBorderColor = loginPage.passwordFieldEle().getCssValue("border-color");
        //check if right color
        for(WebElement e : listExplainErrEles){
            Assert.assertEquals(e.getCssValue("color").matches(expectedErrColor), true);
        }
        for(WebElement e : inputIcons){
            Assert.assertEquals(e.getCssValue("color").matches(expectedErrColor), true);
        }
//        for (WebElement e : )
        Assert.assertEquals(actualPasswordFieldBorderColor.matches(expectedErrColor), true);
        Assert.assertEquals(actualUsernameFieldBorderColor.matches(expectedErrColor),true);
        // check if right error text
        Assert.assertEquals(actualUsernameErr, expectedUsernameErr);
        Assert.assertEquals(actualPasswordErr, expectedPasswordErr);
    }
    @Test(priority = 3)
    public void loginWithEmptyUser() throws InterruptedException {
        driver = getDriver();
        LoginPage loginPage  = new LoginPage(driver);
        loginPage
                .inputPassword(password)
                .clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        //check if there is only one wrong item
        String actualUserErr = loginPage.explainErrEles().get(0).getText();
        String actualUsernameFieldBorderColor = loginPage.userFieldEle().getCssValue("border-color");
        // check if right error text
        Assert.assertEquals(actualUserErr, expectedUsernameErr);
        //check if right color
        Assert.assertEquals(loginPage.inputIconEles().get(ICON_USER_IDEX).getCssValue("color").matches(expectedErrColor),true);
        Assert.assertEquals(loginPage.explainErrEles().get(0).getCssValue("color").matches(expectedErrColor), true);
        Assert.assertEquals(actualUsernameFieldBorderColor.matches(expectedErrColor),true);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
    }
    @Test(priority = 4)
    public void loginWithEmptyPassword() throws InterruptedException {
        driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage  = new LoginPage(driver);
        //clear value in fields
        loginPage.password().sendKeys(Keys.CONTROL + "a");
        loginPage.password().sendKeys(Keys.DELETE);
        loginPage.username().sendKeys(Keys.CONTROL + "a");
        loginPage.username().sendKeys(Keys.DELETE);

        wait.until(ExpectedConditions.visibilityOf(loginPage.password()));
        loginPage
                .inputUsername(username)
                .clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.explainErrsXpathBy()));
        //check if there is only one wrong item
        Assert.assertEquals(loginPage.explainErrEles().size(),1);
        //check if right text
        String actualPasswordErr = loginPage.explainErrEles().get(0).getText();
        Assert.assertEquals(actualPasswordErr, expectedPasswordErr);
        //check if right color
        String actualPasswordFieldBorderColor = loginPage.passwordFieldEle().getCssValue("border-color");
        Assert.assertEquals(loginPage.inputIconEles().get(ICON_PASSWORD_IDEX).getCssValue("color").matches(expectedErrColor),true);
        Assert.assertEquals(loginPage.explainErrEles().get(0).getCssValue("color").matches(expectedErrColor), true);
        Assert.assertEquals(actualPasswordFieldBorderColor.matches(expectedErrColor), true);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
    }

    @Test(priority = 5)
    public void loginWithInvalidUser() throws InterruptedException {
        driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage  = new LoginPage(driver);
        // clear values in fields
        loginPage.password().sendKeys(Keys.CONTROL + "a");
        loginPage.password().sendKeys(Keys.DELETE);
        loginPage.username().sendKeys(Keys.CONTROL + "a");
        loginPage.username().sendKeys(Keys.DELETE);

        //login
        loginPage
                .inputUsername(username)
                .inputPassword("password")
                .clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginPage.getAlertXpath()));
        //check alert title
        String actualAlertTitle = driver.findElement(loginPage.getAlertTitle()).getText();
        Assert.assertEquals(actualAlertTitle, expectedAlertTitle);
        loginPage.alertCloseBtn().click();
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginBtn()));
    }
    @Test(priority = 6, dataProvider = "loginData")
    public void loginWithCorrectUser(String username, String password,String expectedUsernameAfterLogin) throws InterruptedException {
        driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage  = new LoginPage(driver);
        //clear text in fields
        loginPage.password().sendKeys(Keys.CONTROL + "a");
        loginPage.password().sendKeys(Keys.DELETE);
        loginPage.username().sendKeys(Keys.CONTROL + "a");
        loginPage.username().sendKeys(Keys.DELETE);
        System.out.println(driver.getTitle());
        loginPage
                .inputUsername(username)
                .inputPassword(password)
                .clickLoginButton();
        String actualPageTitleURLAfterLogin = driver.getTitle();
        System.out.println(actualPageTitleURLAfterLogin);
        DashboardPage dashboardPage = new DashboardPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.getUserNameInfoXpath()));
        String actualUsername = dashboardPage.userNameInfo().getText();
        String actualWelcomeText = dashboardPage.welcomeText().getText();
        //check page title
        Assert.assertEquals(actualPageTitleURLAfterLogin, expectedPageTitleAfterLogin);
        // check user name
        Assert.assertEquals(actualUsername, expectedUsernameAfterLogin);
        //Check welcome text
        Assert.assertEquals(actualWelcomeText, expectedWelcomeTextAfteLogin);
        //Check if check if side menu is present and the first item is selected
        Assert.assertEquals(dashboardPage.menuItemsEles().size()>0 ,true);
        Assert.assertEquals(dashboardPage.menuItemsEles().get(0).getAttribute("class").contains("selected"), true);
        Thread.sleep(500);
        dashboardPage.dropDownLogOutEle().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.getLogOutButtonXpath()));
        Thread.sleep(500);
        dashboardPage.logOutButton().click();
    }
    @DataProvider
    public Object[][] loginData(){
        String excelFileName = "Login.xlsx";
        File excelFileLocation = new File(System.getProperty("user.dir") + "/data/" +excelFileName);
        String sheetName = "accounts";
        int startRowIndex = 1;
        int startColIndex = 0;
        ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(excelFileLocation, sheetName, startRowIndex, startColIndex);
        int totalRows = excelReaderUtil.getTotalRows();
        int totalCols = excelReaderUtil.getTotalCols();
        Object[][] loginData = new Object[totalRows - startRowIndex][totalCols - startColIndex];
        for (int startRow = startRowIndex; startRow < totalRows; startRow++){
            for (int startCol = startColIndex; startCol < totalCols; startCol++){
                loginData[startRow - startRowIndex][startCol]= excelReaderUtil.getCellValue(startRow, startCol);
            }
        }
        return loginData;
    }
}
