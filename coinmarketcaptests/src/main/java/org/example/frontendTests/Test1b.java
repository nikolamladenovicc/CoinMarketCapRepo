package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Test1b {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Postavi putanju do ChromeDriver-a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testViewAllResultsDisplayed() {
        // Otvori CoinMarketCap stranicu
        driver.get("https://coinmarketcap.com/");

        // Klikni na "View All"
        WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]/a[1]/button"));
        viewAll.click();

        // Proveri da li su svi rezultati prikazani
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        Assert.assertFalse(results.isEmpty(), "TEST FAILED | Nema prikazanih rezultata.");
    }

    @AfterMethod
    public void teardown() {
        // Zatvori browser
        if (driver != null) {
            driver.quit();
        }
    }
}
