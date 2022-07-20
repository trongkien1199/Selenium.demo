package com.bandoso.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

public class DashboardPage {
    private WebDriver driver;
    private By userNameInfoXpath = By.xpath("//div[@class='ant-dropdown-trigger user__name']//span[@class='user__name--name']");
    private By welcomeTextXpath = By.xpath("//div[@class= 'ant-dropdown-trigger user__name']//span/span");
    private By menuItemsXpath = By.xpath("//ul//li[@role = 'menuitem']");

    public  DashboardPage (WebDriver driver) {
        this.driver = driver;
    }
    public By getUserNameInfoXpath(){
        return this.userNameInfoXpath;
    }
    public WebElement userNameInfo(){
        return driver.findElement(userNameInfoXpath);

    }
    public WebElement welcomeText(){
        return driver.findElement(welcomeTextXpath);
    }
    public List<WebElement> menuItemsEles(){
        return driver.findElements(menuItemsXpath);
    }
}
