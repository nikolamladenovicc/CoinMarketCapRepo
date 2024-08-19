
package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @BeforeMethod
    public void setUp() {
        // Inicijalizacija WebDriver-a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Korisnik\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://coinmarketcap.com/");
    }

    @Test
    public void testAddCryptosToWatchlist() throws InterruptedException {
        // Pronađi listu svih kriptovaluta na stranici
        List<WebElement> cryptoRows = driver.findElements(By.cssSelector("tbody tr"));

        // Nasumično odaberi između 5 i 10 kriptovaluta
        Random random = new Random();
        int numberOfCryptos = random.nextInt(6) + 5; // 5 do 10

        List<String> selectedCryptos = new ArrayList<>();

        for (int i = 0; i < numberOfCryptos; i++) {
            WebElement cryptoRow = cryptoRows.get(i);
            WebElement nameElement = cryptoRow.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table/tbody/tr[1]/td[3]/div/a/div/div/div/p"));
            String cryptoName = nameElement.getText();
            selectedCryptos.add(cryptoName);

            // Klik na elipsu i dodaj na Watchlist
            WebElement starIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.className("icon-Star")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", starIcon);

            // Čekaj da se zvezda promeni (ili neki drugi indikator)
            Thread.sleep(1000); // Ubaci čekanje pre prelaska na sledeći kripto
        }

        // Otvori novi tab
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Prebaci se na novi tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Prebaci se na drugi tab (indeks 1)

        // Otvori Watchlist
        driver.get("https://coinmarketcap.com/watchlist/");
        Thread.sleep(1000);

        // Proveri da li su sve odabrane kriptovalute dodate na Watchlist
        List<WebElement> watchlistItems = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/div/div/div[1]/div[3]/table/tbody/tr[1]/td[3]/div/a/div/div/div/p"));
        List<String> watchlistNames = new ArrayList<>();
        for (WebElement item : watchlistItems) {
            watchlistNames.add(item.getText());
        }

        for (String cryptoName : selectedCryptos) {
            Assert.assertTrue(watchlistNames.contains(cryptoName), "Crypto " + cryptoName + " not found in watchlist!");
        }
        Thread.sleep(1000);
    }


    @AfterMethod
    public void tearDown() {
        // Zatvori pretraživač
        if (driver != null) {
            driver.quit();
        }
    }
}
