package org.example.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    // Locators
    private By viewAllButton = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]/a[1]/button");
    private By resultsTable = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table");
    private By cryptoRowsLocator = By.cssSelector("tbody tr");
    private By watchlistStarIconLocator = By.className("icon-Star");
    private By cryptoTab = By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.sc-2e66506f-0.jztNxX > div.sc-d8ea16bb-0.hZhywa > div:nth-child(4) > div > div.sc-2fe50849-0.iqytLW > div:nth-child(3) > div");
    private By currenciesTab = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[1]");
    private By fullListOption = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[2]/div/div[1]/div[2]/a[1]");
    private By cryptoTable = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table");
    private By filterButton = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div/button[1]");
    private By categoryButton = By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/ul/li[1]/div/span/button");
    private By platformButton = By.className("sc-82271cd3-2");

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

    // Method to get the name of a cryptocurrency
    public String getCryptoName(WebElement cryptoRow) {
        WebElement nameElement = cryptoRow.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table/tbody/tr[1]/td[3]/div/a/div/div/div/p"));
        return nameElement.getText();
    }

    public void openCryptoTab(){
        driver.findElement(cryptoTab).click();
    }

    public void selectCurrenciesTab(){
        driver.findElement(currenciesTab).click();
    }

    public void selectFullListOption(){
        driver.findElement(fullListOption).click();
    }
    public List<String> recordInitialData() {
        List<WebElement> initialData = driver.findElements(cryptoTable);
        List<String> initialCryptoNames = new ArrayList<>();
        for (WebElement crypto : initialData) {
            initialCryptoNames.add(crypto.getText());
        }
        return initialCryptoNames;
    }
    public void applyFilters() throws InterruptedException {
        driver.findElement(filterButton).click();
        Thread.sleep(2000);
        driver.findElement(categoryButton).click();
        Thread.sleep(2000);
        driver.findElement(platformButton).click();
        Thread.sleep(2000);
    }
    public void filterButton(){
        driver.findElement(filterButton).click();
    }
    public void categoryButton(){
        driver.findElement(categoryButton).click();
    }
    public void platformButton(){
        driver.findElement(platformButton).click();
    }
    public List<String> recordFilteredData() {
        List<WebElement> filteredData = driver.findElements(cryptoTable);
        List<String> filteredCryptoNames = new ArrayList<>();
        for (WebElement crypto : filteredData) {
            filteredCryptoNames.add(crypto.getText());
        }
        return filteredCryptoNames;
    }




}
