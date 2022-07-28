package com.bandoso.utils;

import com.bandoso.model.pages.TaxOfficersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CountElementsInList {
    public static int countElesInListPagination(WebDriver driver, WebElement buttonEle, By listXpath) {
        int count = 0;
        List<WebElement> officerNameList = driver.findElements(listXpath);
        if (buttonEle.isEnabled() == false) {
            for (WebElement e : officerNameList) {
                count++;
            }
        } else {
            while (buttonEle.isEnabled() == true) {
                officerNameList = driver.findElements(listXpath);
                for (WebElement e : officerNameList) {
                    count++;
                }
                buttonEle.click();
            }
            List<WebElement> officerNameList2 = driver.findElements(listXpath);
            for (WebElement e : officerNameList2) {
                count++;
            }
        }
        return count;
    }
    public static int countElesContainString(WebDriver driver, WebElement buttonEle, By listXpath , String searchKeyword) throws InterruptedException {
        int count = 0;
        List<WebElement> officerNameList = driver.findElements(listXpath);
        if (!buttonEle.isEnabled()) {
            for (WebElement e : officerNameList) {
                if (e.getText().contains(searchKeyword)){
                        count++;
                }
            }
        } else {
            while (buttonEle.isEnabled()) {
                officerNameList = driver.findElements(listXpath);
                for (WebElement e : officerNameList) {
                    if (e.getText().contains(searchKeyword)){
                        count++;
                    }
                }
                buttonEle.click();
            }
            Thread.sleep(1000);
            List<WebElement> officerNameList2 = driver.findElements(listXpath);
            for (WebElement e : officerNameList2) {
                if (e.getText().contains(searchKeyword)){
                    count++;
                }
            }
        };
        return count;
    }
    public static boolean checkIfAllNameContainKeyword(WebDriver driver, WebElement buttonEle, By listXpath , String searchKeyword) throws InterruptedException {
        int count = 0;
        List<WebElement> officerNameList = driver.findElements(listXpath);
        if (!buttonEle.isEnabled()) {
            for (WebElement e : officerNameList) {
                if (!e.getText().toLowerCase(Locale.ROOT).contains(searchKeyword.toLowerCase())){
                    System.out.println(e.getText());
                    return false;
                }
            }
        } else {
            while (buttonEle.isEnabled()) {
                officerNameList = driver.findElements(listXpath);
                for (WebElement e : officerNameList) {
                    if (!e.getText().toLowerCase(Locale.ROOT).contains(searchKeyword.toLowerCase())){
                        System.out.println(e.getText());
                        return false;
                    }
                }
                buttonEle.click();
            }
            Thread.sleep(1000);
            List<WebElement> officerNameList2 = driver.findElements(listXpath);
            for (WebElement e : officerNameList2) {
                if (!e.getText().toLowerCase(Locale.ROOT).contains(searchKeyword.toLowerCase())){
                    System.out.println(e.getText());
                    return false;
                }
            }
        };
        return true;
    }

}
