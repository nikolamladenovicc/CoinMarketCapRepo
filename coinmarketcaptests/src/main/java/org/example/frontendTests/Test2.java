
package org.example.frontendTests;

import org.example.pageObjectModels.MainPage;
import org.example.pageObjectModels.WatchlistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test2 {

    private WebDriver driver;
    private MainPage mainPage;
    private WatchlistPage watchlistPage;

    @BeforeMethod
    public void setUp() {
        // Initialization of WebDiver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://coinmarketcap.com/");

        mainPage = new MainPage(driver);
        watchlistPage = new WatchlistPage(driver);
    }

    @Test
    public void testAddCryptosToWatchlist() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait
        // Find the list of all cryptocurrencies on page
        List<WebElement> cryptoRows = mainPage.getCryptoRows();

        // Randomly choose between 5 and 10 cryptocurrencies
        Random random = new Random();
        int numberOfCryptos = random.nextInt(6) + 5; // 5 do 10

        List<String> selectedCryptos = new ArrayList<>();

        for (int i = 0; i < numberOfCryptos; i++) {
            WebElement cryptoRow = cryptoRows.get(i);
            String cryptoName = mainPage.getCryptoName(cryptoRow);
            selectedCryptos.add(cryptoName);

            // Click on Star Icon to add to the Watchlist
            mainPage.addToWatchlist(cryptoRow);
        }

        // Open the new tab
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Switch to another tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Switch to another tab (index 1)

        // Open the Watchlist page
        driver.get("https://coinmarketcap.com/watchlist/");

        // Check if the all selected cryptocurrencies are added to Watchlist
        List<WebElement> watchlistItems = watchlistPage.getWatchlistItems();
        List<String> watchlistNames = new ArrayList<>();
        for (WebElement item : watchlistItems) {
            watchlistNames.add(watchlistPage.getWatchlistItemName(item));
        }

        for (String cryptoName : selectedCryptos) {
            Assert.assertTrue(watchlistNames.contains(cryptoName), "Crypto " + cryptoName + " not found in watchlist!");
        }
        System.out.println("TEST PASSED | Cryptocurrencies successfully added to the Watchlist.");
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
