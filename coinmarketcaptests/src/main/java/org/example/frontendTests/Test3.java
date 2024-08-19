package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testFilterFunctionality() throws InterruptedException {
        // Open CoinMarketApp website
        driver.get("https://coinmarketcap.com/");
        Thread.sleep(5000);

        // Click the drop-down menu on "Cryptocurrencies" tab
        WebElement cryptoTab = driver.findElement(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.sc-2e66506f-0.jztNxX > div.sc-d8ea16bb-0.hZhywa > div:nth-child(4) > div > div.sc-2fe50849-0.iqytLW > div:nth-child(3) > div"));
        cryptoTab.click();
        Thread.sleep(2000);

        WebElement currenciesTab = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[1]"));
        currenciesTab.click();
        Thread.sleep(2000);

        WebElement fullListOption = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[2]/div/div[1]/div[2]/a[1]"));
        fullListOption.click();
        Thread.sleep(5000);

        // Record the data from the current page
        List<WebElement> initialData = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        List<String> initialCryptoNames = new ArrayList<>();
        for (WebElement crypto : initialData) {
            initialCryptoNames.add(crypto.getText());
        }

        // Use the filters
        WebElement filterButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[2]/div/button[1]"));
        filterButton.click();
        Thread.sleep(2000);

        WebElement categoryButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/ul/li[1]/div/span/button"));
        categoryButton.click();
        Thread.sleep(2000);

        WebElement platformButton = driver.findElement(By.className("sc-82271cd3-2"));
        platformButton.click();
        Thread.sleep(2000);

        // Record the filtered data from page
        List<WebElement> filteredData = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
        List<String> filteredCryptoNames = new ArrayList<>();
        for (WebElement crypto : filteredData) {
            filteredCryptoNames.add(crypto.getText());
        }

        // Check if the all filtered data are different from the initial data
        Assert.assertNotEquals(filteredCryptoNames, initialCryptoNames, "TEST FAILED | Filtered data are the same as the initial data.");

        // Check the filtered data against the initial data
        if (initialCryptoNames == filteredCryptoNames){
            System.out.println("TEST FAILED | The both initial and filtered data are the same.");
        }
        else {
            System.out.println("TEST PASSED | The filtered data is different from the initial data.");
        }
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


