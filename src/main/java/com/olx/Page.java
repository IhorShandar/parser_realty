package com.olx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private boolean findLast = false;
    private WebDriver driver;

    public boolean isFindLast() {
        return findLast;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getNewAds(String lastId) {
        List<WebElement> ads = driver.findElements(By.xpath("//table[@id='offers_table']//tr[@class='wrap']//table"));
        List<String> newAds = new ArrayList<>();
        if (!(lastId == null)) {
            for (WebElement ad : ads) {
                String data_id = ad.getAttribute("data-id");
                if (data_id.equals(lastId)) {
                    this.findLast = true;
                    break;
                } else {
                    newAds.add(data_id);
                }
            }
        } else {
            for (WebElement element : ads) {
                String data_id = element.getAttribute("data-id");
                newAds.add(data_id);
            }
        }
        return newAds;
    }

    public void outputLinks(List<String> newAds) {
        for (String newId : newAds) {
            WebElement linkAd = driver.findElement(By.xpath("//table[@id='offers_table']//tr[@class='wrap']//table[@data-id='" + newId + "']//a[@data-cy='listing-ad-title']"));
            String href = linkAd.getAttribute("href");
            System.out.println(href);
        }
    }

    public void nextPage() throws InterruptedException {
        WebElement linkOnPage = driver.findElement(By.xpath("//span[@class='fbold next abs large']//a[@href]"));
        String linkPage = linkOnPage.getAttribute("href");
        driver.get(linkPage);
        Thread.sleep(2000);
    }
}
