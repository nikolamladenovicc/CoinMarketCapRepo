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

public class Test1 {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Postavi putanju do ChromeDriver-a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testViewAllResultsDisplayed() {
        // Open CoinMarketCap website
        driver.get("https://coinmarketcap.com/");

        // Click on "View All"
        WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]/a[1]/button"));
        viewAll.click();

        // Check if all results are displayed
        List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        Assert.assertFalse(results.isEmpty(), "TEST FAILED | No data to display.");
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
