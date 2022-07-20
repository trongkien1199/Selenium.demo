package java.com.bubble.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    private WebDriver driver;
    private By userNameInfoXpath = By.xpath("//div[@class='user__name ant-dropdown-trigger']//span[@class='user__name--name']");
    private By welcomeTextXpath = By.xpath("//div[@class= 'user__name ant-dropdown-trigger']//span/span");

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
}
