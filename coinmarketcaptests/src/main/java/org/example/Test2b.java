package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test2b {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");


        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the CoinMarketCap website
        driver.get("https://coinmarketcap.com/");

        // Wait for the page to load completely
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Get a list of cryptocurrency elements
        List<WebElement> cryptocurrencies = driver.findElements(By.tagName("tr"));

        // Iterate through the first 5-10 elements (as an example, we're choosing 5)
        for (int i = 0; i < 5; i++) {
            WebElement cryptocurrency = cryptocurrencies.get(i);

            // Find the ellipsis (three dots) button and click it
            WebElement ellipsisButton = cryptocurrency.findElement(By.className("icon-Star"));
            ellipsisButton.click();

        }

        // Open a new tab and navigate to the Watchlist
        ((JavascriptExecutor) driver).executeScript("window.open('https://coinmarketcap.com/watchlist/', '_blank');");


        // Switch to the new tab
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Retrieve the list of cryptocurrencies in the watchlist
        List<WebElement> watchlistItems = driver.findElements(By.tagName("table"));

        // Check if the count matches the number of cryptocurrencies you added
        if (watchlistItems.size() == 5) {
            System.out.println("All selected cryptocurrencies are added to the watchlist.");
        } else {
            System.out.println("Some cryptocurrencies were not added to the watchlist.");
        }

        // Close the browser
        driver.quit();
    }
}

