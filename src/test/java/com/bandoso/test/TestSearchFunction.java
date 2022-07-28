package com.bandoso.test;

import com.bandoso.driver.DriverBase;
import com.bandoso.model.pages.LoginPage;
import com.bandoso.model.pages.TaxOfficersPage;
import com.bandoso.utils.CountElementsInList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearchFunction extends DriverBase {
    private String keyword = "test";
    private int NEXT_BUTTON_IDEX = 1;
    private int BACK_BUTTON_IDEX = 0;
    private String loginUrl = "https://bds-hkd-web-dev.captainbandoso.nexlesoft.com/";
    private String username = "admin.tbi";
    private String password = "Nexle@2020";
    private String url = "https://bds-hkd-web-dev.captainbandoso.nexlesoft.com/";
    private WebDriver driver;

    @Test
    public void searchAllByChars() throws InterruptedException {
        driver = getDriver();
        driver.get(url);
        driver.manage().window().maximize();
        int numOfNameIncludeKeyword = 0;
        LoginPage loginPage = new LoginPage(driver);
        TaxOfficersPage taxOfficersPage = new TaxOfficersPage(driver);
        loginPage
                .inputUsername(username)
                .inputPassword(password)
                .clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(taxOfficersPage.getTaxOfficerTabXpath()));
        WebElement taxOfficersTab = driver.findElement(taxOfficersPage.getTaxOfficerTabXpath());
        //go to tax officer tab
        taxOfficersTab.click();
        WebElement nextPageButton = taxOfficersPage.nextPreviousButons().get(NEXT_BUTTON_IDEX);
        WebElement backPageButton = taxOfficersPage.nextPreviousButons().get(BACK_BUTTON_IDEX);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(taxOfficersPage.getOfficersNamesXpath()));
        List<WebElement> officerNameList;
        officerNameList = driver.findElements(taxOfficersPage.getOfficersNamesXpath());
        By officersNamesXpath = taxOfficersPage.getOfficersNamesXpath();
        // count name contain keyword in whole list
        numOfNameIncludeKeyword = CountElementsInList.countElesContainString(driver, nextPageButton, officersNamesXpath, keyword);
        System.out.println(numOfNameIncludeKeyword);
        //search with key word
        taxOfficersPage.searchFieldEle().sendKeys(keyword);
        taxOfficersPage.searchButtonEle().click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(taxOfficersPage.getOfficersNamesXpath()));
        nextPageButton = taxOfficersPage.nextPreviousButons().get(NEXT_BUTTON_IDEX);
        backPageButton = taxOfficersPage.nextPreviousButons().get(BACK_BUTTON_IDEX);

        int numOfNameAfterSearch = CountElementsInList.countElesInListPagination(driver, nextPageButton, officersNamesXpath);
        boolean checkIfAllNameContainKeyword = CountElementsInList.checkIfAllNameContainKeyword(driver,backPageButton, officersNamesXpath, keyword);
        Assert.assertEquals(checkIfAllNameContainKeyword, true);
        Assert.assertEquals(numOfNameAfterSearch,numOfNameIncludeKeyword);



    }
}

