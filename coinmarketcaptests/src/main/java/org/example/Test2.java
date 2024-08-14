package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test2 {

    private WebDriver driver;
    private int numCryptosToSelect = 5; // Number of cryptocurrencies to select

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Set up ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Open browser in maximized mode
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait
    }

    @Test
    public void testAddToWatchlist() {
        // Open https://coinmarketcap.com/
        driver.get("https://coinmarketcap.com/");

        // Select between 5 and 10 random cryptocurrencies
        List<WebElement> cryptoRows = driver.findElements(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.cmc-body-wrapper > div > div:nth-child(1) > div.sc-7b3ac367-2.cFnHu > table"));
        numCryptosToSelect = Math.min(cryptoRows.size(), numCryptosToSelect); // Ensure we don't exceed available cryptos

        for (int i = 0; i < numCryptosToSelect; i++) {
            WebElement cryptoRow = cryptoRows.get(i);

            WebElement ellipsis = cryptoRow.findElement(By.tagName("img"));
            ellipsis.click();

            WebElement addToWatchlistButton = driver.findElement(By.className("sc-65e7f566-0 sc-768207e8-0 eQBACe faBHBV full-action-btn"));
            addToWatchlistButton.click();

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

        ((JavascriptExecutor) driver).executeScript("window.open('https://coinmarketcap.com/watchlist/', '_blank');");

    }

    @Test(dependsOnMethods = "testAddToWatchlist")
    public void testVerifyWatchlist() {
        // Switch to the watchlist tab
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Step 5: Check if the cryptos were added to the watchlist
        List<WebElement> watchlistItems = driver.findElements(By.cssSelector(".cmc-table-row"));

        // Assert the number of items in the watchlist
        Assert.assertEquals(watchlistItems.size(), numCryptosToSelect, "Not all selected cryptos were added to the watchlist.");

        // Additional verification by checking the names
        for (WebElement item : watchlistItems) {
            String cryptoName = item.findElement(By.cssSelector(".coin-item-symbol")).getText();
            System.out.println("Added to watchlist: " + cryptoName);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
