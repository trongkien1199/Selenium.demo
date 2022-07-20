package java.com.bubble.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private By usernameXpath= By.xpath("//input[@id='horizontal_login_username']");
    private By usernameClearXpath = By.xpath("//input[@id='horizontal_login_username']//following::span[1]");
    private By passwordXpath= By.xpath("//input[@id='horizontal_login_password']");
    private By passwordClearXpath = By.xpath("//input[@id='horizontal_login_password']//following::span[1]");
    private By loginBtnXpath= By.xpath("//div[@class='login__ButtonWrapper-sc-14ug8ov-4 fUFqEu']//button[@type='submit']");
    private By explainErrsXpath = By.xpath("//div[@class='ant-form-item-explain']//span");
    private By alertXpath = By.xpath("//span//div[@class='ant-notification-notice ant-notification-notice-closable']");
    private By alertTitle = By.xpath("//div[@class='ant-notification-notice-message']");
    private By alertContent = By.xpath("//div[@class='ant-notification-notice-description']");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public By usernameXpathBy(){
        return this.usernameXpath;
    }
    public WebElement username(){
        return driver.findElement(usernameXpath);
    }
    public WebElement usernameClearEle(){
        return driver.findElement(usernameClearXpath);
    }
    public WebElement password(){
        return driver.findElement(passwordXpath);
    }
    public WebElement passwordClearEle(){
        return driver.findElement(passwordClearXpath);
    }
    public WebElement loginBtn(){
        return driver.findElement(loginBtnXpath);
    }
    public By explainErrsXpathBy(){
        return this.explainErrsXpath;
    }
    public List<WebElement> explainErrEles() {
        return driver.findElements(explainErrsXpath);
    }

    public By getAlertXpath() {
        return this.alertXpath;
    }

    public By getAlertTitle() {
        return this.alertTitle;
    }
    public By getAlertContent(){
        return this.alertContent;
    }

    public LoginPage inputUsername(String username){
        username().sendKeys(username);
        return this;
    }
    public LoginPage inputPassword(String password){
        password().sendKeys(password);
        return this;
    }
    public LoginPage clickLoginButton(){
        loginBtn().click();
        return this;
    }
}
