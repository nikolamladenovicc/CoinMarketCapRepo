package org.example.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    // Locators
    private By viewAllButton = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]/a[1]/button");
    private By resultsTable = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table");

    private By cryptoRowsLocator = By.cssSelector("tbody tr");

    private By watchlistStarIconLocator = By.className("icon-Star");
    // Constructors
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Methods for interacting with elements
    public void clickViewAll(){
        driver.findElement(viewAllButton).click();
    }

    public boolean displayedResults(){
        List<WebElement> results = driver.findElements(resultsTable);
        return !results.isEmpty();
    }

    public List<WebElement> getCryptoRows() {
        return driver.findElements(cryptoRowsLocator);
    }

    public void addToWatchlist(WebElement cryptoRow) {
        WebElement starIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cryptoRow.findElement(watchlistStarIconLocator)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", starIcon);
    }


}
