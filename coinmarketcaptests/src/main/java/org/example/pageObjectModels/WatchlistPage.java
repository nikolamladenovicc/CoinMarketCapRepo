package org.example.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WatchlistPage {

    private WebDriver driver;

    private By watchlistItemsLocator = By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/div/div/div[1]/div[3]/table/tbody/tr[1]/td[3]/div/a/div/div/div/p");

    public WatchlistPage(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> getWatchlistItems(){
        return driver.findElements(watchlistItemsLocator);
    }

    public String getWatchlistItemName(WebElement watchlistItemName){
        return watchlistItemName.getText();
    }
}
