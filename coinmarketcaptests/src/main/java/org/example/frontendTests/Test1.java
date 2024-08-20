package org.example.frontendTests;

import org.example.pageObjectModels.MainPage;
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
    private MainPage mainPage;

    @BeforeMethod
    public void setup() {
        // Postavi putanju do ChromeDriver-a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
    }

    @Test
    public void testViewAllResultsDisplayed() {
        // Open CoinMarketCap website
        driver.get("https://coinmarketcap.com/");

        // Click on "View All"
        mainPage.clickViewAll();

        // Check if all results are displayed
        Assert.assertTrue(mainPage.displayedResults(), "TEST FAILED | No data to display.");
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
