package com.bandoso.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.bind.Element;
import java.util.List;

public class TaxOfficersPage {
    private By officersNamesXpath = By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']/descendant::span[1]");
    private By branchesXpath = By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']/descendant::td[2]");

    private By nextPreviousButonsXpath = By.xpath("//button[@class='ant-pagination-item-link']");
    private WebDriver driver;
    private By taxOfficerTabXpath = By.xpath("//li//span[contains(text(),'Cán bộ thuế')]");
    private By searchFieldXpath = By.xpath("//input[@class='ant-input ant-input-lg']");
    private By searchButtonXpath = By.xpath("//span[contains(text(),'Tìm kiếm')]/parent::*");
    private By addButtonXpath = By.xpath("//span[contains(text(),'Thêm')]/parent::*");

    private By officeNamesXpath = By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']/descendant::td[2]");

    public TaxOfficersPage(WebDriver driver){
        this.driver = driver;
    }
    public By getTaxOfficerTabXpath(){
        return this.taxOfficerTabXpath;
    }

    public By getNextPreviousButonsXpath() {
        return this.nextPreviousButonsXpath;
    }
    public By getOfficersNamesXpath() {
        return this.officersNamesXpath;
    }
    public List<WebElement> officersNameEles(){
        return driver.findElements(officersNamesXpath);
    }
    public List<WebElement> BranchesEles(){
        return driver.findElements(branchesXpath);
    }
    public List<WebElement> nextPreviousButons(){
        return driver.findElements(nextPreviousButonsXpath);
    }
    public WebElement searchFieldEle(){
        return  driver.findElement(searchFieldXpath);
    }
    public WebElement searchButtonEle(){
        return driver.findElement(searchButtonXpath);
    }
    public WebElement addButtonEle(){
        return driver.findElement(addButtonXpath);
    }
}
