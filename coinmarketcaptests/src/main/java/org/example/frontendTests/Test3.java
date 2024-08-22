package org.example.frontendTests;

import org.example.pageObjectModels.MainPage;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test3 {
    private WebDriver driver;
    private MainPage mainPage;
    @BeforeClass
    public void setUp() {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Implicit wait
        mainPage = new MainPage(driver);

    }

    @Test
    public void testFilterFunctionality() {
        // Open CoinMarketApp website
        driver.get("https://coinmarketcap.com/");

        // Click the drop-down menu on "Cryptocurrencies" tab
        mainPage.openCryptoTab();
        mainPage.selectCurrenciesTab();
        mainPage.selectFullListOption();


        // Record the data from the current page
        List<String> initialData = mainPage.recordInitialData();

        // Use the filters
        mainPage.clickFilterButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> mainPage.displayCategoryButton());
        mainPage.clickCategoryButton();
        wait.until(d -> mainPage.displayPlatformButton());
        mainPage.clickPlatformButton();

        // Record the filtered data from page
        wait.until(d -> mainPage.displayResults());
        List<String> filteredData = mainPage.recordFilteredData();

        // Check if the all filtered data are different from the initial data
        Assert.assertNotEquals(filteredData, initialData, "TEST FAILED | Filtered data are the same as the initial data.");
        // Check the filtered data against the initial data
        if (initialData.equals(filteredData)){
            System.out.println("TEST FAILED | The both initial and filtered data are the same.");
        }
        else {
            System.out.println("TEST PASSED | The filtered data is different from the initial data.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


