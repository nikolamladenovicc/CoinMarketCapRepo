package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Korisnik\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testFilterFunctionality() throws InterruptedException {
        // Otvori CoinMarketCap stranicu
        driver.get("https://coinmarketcap.com/");
        Thread.sleep(5000); // Čekaj da se stranica učita

        // Prikaži padajući meni na "Cryptocurrencies" tabu
        WebElement cryptoTab = driver.findElement(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.sc-2e66506f-0.jztNxX > div.sc-d8ea16bb-0.hZhywa > div:nth-child(4) > div > div.sc-2fe50849-0.iqytLW > div:nth-child(3) > div"));
        cryptoTab.click();
        Thread.sleep(2000); // Čekaj da se meni prikaže

        WebElement currenciesTab = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[1]"));
        currenciesTab.click();
        Thread.sleep(2000);


        WebElement fullListOption = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[2]/div/div[1]/div[2]/a[1]"));
        fullListOption.click();
        Thread.sleep(5000); // Čekaj da se stranica učita

        // Zabeleži podatke sa trenutne stranice
        List<WebElement> initialData = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        List<String> initialCryptoNames = new ArrayList<>();
        for (WebElement crypto : initialData) {
            initialCryptoNames.add(crypto.getText());
            System.out.println(initialCryptoNames);
        }
        // Primeni filtere
        WebElement filterButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div/button[1]"));
        filterButton.click();
        Thread.sleep(2000);

        WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/ul/li[1]/div/span/button"));
        categoryButton.click();
        Thread.sleep(2000);

        WebElement platformButton = driver.findElement(By.className("sc-82271cd3-2"));
        platformButton.click();
        Thread.sleep(2000);

        // Zabeleži filtrirane podatke sa stranice
        List<WebElement> filteredData = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        List<String> filteredCryptoNames = new ArrayList<>();
        for (WebElement crypto : filteredData) {
            filteredCryptoNames.add(crypto.getText());
        }

        // Proveri da li su filtrirani podaci različiti od početnih
        Assert.assertNotEquals(filteredCryptoNames, initialCryptoNames, "TEST FAILED | Filtrirani podaci su isti kao početni.");

        // Dodatna provera: Prikazi filtrirane podatke
        System.out.println("Početni podaci: " + initialCryptoNames);
        System.out.println("Filtrirani podaci: " + filteredCryptoNames);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

