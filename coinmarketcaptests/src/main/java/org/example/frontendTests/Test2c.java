package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test2c {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Postavi putanju do ChromeDriver-a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testAddToWatchlist() throws InterruptedException {
        // Otvori CoinMarketCap stranicu
        driver.get("https://coinmarketcap.com/");
        Thread.sleep(5000); // Čekaj da se stranica učita

        // Izaberi između 5 i 10 nasumičnih kriptovaluta
        List<WebElement> cryptoElements = driver.findElements(By.tagName("tr"));

        // Ako je veličina liste manja od 5, izaberi sve kriptovalute
        Random random = new Random();
        int numCryptosToSelect = Math.min(5 + random.nextInt(6), cryptoElements.size());
        List<WebElement> selectedCryptos = new ArrayList<>();

        for (int i = 0; i < numCryptosToSelect; i++) {
            selectedCryptos.add(cryptoElements.get(i));
        }

        // Dodaj ih u Watchlist
        for (WebElement crypto : selectedCryptos) {
            crypto.click();
            Thread.sleep(5000); // Čekaj da se menu otvori
            WebElement addToWatchlist = driver.findElement(By.className("icon-Star"));
            addToWatchlist.click();
            Thread.sleep(5000); // Čekaj da se kriptovaluta doda
        }

        // Otvori Watchlist u novom tabu
        ((JavascriptExecutor) driver).executeScript("window.open('https://coinmarketcap.com/watchlist/', '_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Proveri da li su svi izabrani kriptovaluti prisutni u Watchlist
        Thread.sleep(5000);
        List<WebElement> watchlistItems = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        Assert.assertTrue(watchlistItems.size() >= selectedCryptos.size(), "TEST FAILED | Neke kriptovalute nisu dodate u Watchlist.");

        // Proveri da li su sve izabrane kriptovalute prisutne
        Thread.sleep(5000);
        for (WebElement crypto : selectedCryptos) {
            Assert.assertTrue(watchlistItems.stream().anyMatch(item -> item.getText().equals(crypto.getText())), "TEST FAILED | Kriptovaluta " + crypto.getText() + " nije pronađena u Watchlist.");
        }
    }



    @AfterMethod
    public void teardown() {
        // Zatvori sve tabove i browser
        if (driver != null) {
            driver.quit();
        }
    }
}

