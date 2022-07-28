package com.bandoso.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private By usernameXpath= By.xpath("//input[@id='horizontal_login_username']");
    private By usernameFieldXpath = By.xpath("//input[@id='horizontal_login_username']/parent::*");
    private By passwordXpath= By.xpath("//input[@id='horizontal_login_password']");
    private By passwordFieldXpath = By.xpath("//input[@id='horizontal_login_password']/parent::*");
    private By loginBtnXpath= By.xpath("//button[@type='submit']");
    private By explainErrsXpath = By.xpath("//div[@class='ant-form-item-explain ant-form-item-explain-error']");
    private By alertXpath = By.xpath("//div[@class='ant-notification-notice ant-notification-notice-warning ant-notification-notice-closable']");
    private By alertTitle = By.xpath("//div[@class='ant-notification-notice-message']");
    private By alertContent = By.xpath("//div[@class='ant-notification-notice-description']");
    private By alertCloseBtnXpath = By.xpath("//span[@class='ant-notification-close-x']");
    private By forgotPasswordXpath = By.xpath("//div[@class='Login__ForgotPasswordWrapper-sc-1jjbxc7-4 loZlaB']//span");
    private By forgotPasswordPopUpXpath = By.xpath("//div[@class='ant-modal-content']");
    private By popUpCloseBtnXpath = By.xpath("//div[@class='ant-modal-content']//button[@class='ant-modal-close']");
    private By popUpHeaderXpath = By.xpath("//div[@class='ant-modal-body']//span");

    private By inputIconsXpath = By.xpath("//div[@class='Login__InputWrapper-sc-1jjbxc7-2 grRTZl']//span[@role='img']");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public By usernameXpathBy(){
        return this.usernameXpath;
    }

    public WebElement passwordFieldEle() {
        return driver.findElement(passwordFieldXpath);
    }

    public WebElement userFieldEle() {
        return driver.findElement(usernameFieldXpath);
    }

    public WebElement username(){
        return driver.findElement(usernameXpath);
    }

    public WebElement password(){
        return driver.findElement(passwordXpath);
    }

    public WebElement loginBtn(){
        return driver.findElement(loginBtnXpath);
    }
    public WebElement alertCloseBtn(){
        return driver.findElement(alertCloseBtnXpath);
    }
    public List<WebElement> explainErrEles() {
        return driver.findElements(explainErrsXpath);
    }
    public WebElement forgotPasswordEle(){
        return driver.findElement(forgotPasswordXpath);
    }
    public WebElement forgotPasswordPopUpEle(){
        return driver.findElement(forgotPasswordPopUpXpath);
    }
    public WebElement popUpCloseBtnEle(){
        return driver.findElement(popUpCloseBtnXpath);
    }
    public WebElement popUpHeaderEle(){
        return driver.findElement(popUpHeaderXpath);
    }
    public  List<WebElement> inputIconEles(){
        return driver.findElements(inputIconsXpath);
    }
    public By explainErrsXpathBy(){
        return this.explainErrsXpath;
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
    public By getForgotPasswordPopUp(){
        return this.forgotPasswordPopUpXpath;
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
