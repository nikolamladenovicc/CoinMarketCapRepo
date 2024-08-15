package org.example.frontendTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://coinmarketcap.com/");

            WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div[1]/a[1]/button"));
            viewAll.click();

            List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/div/div[1]/div[4]/table"));
            if (!results.isEmpty()) {
                System.out.println("TEST PASSED| All results are displayed.");
            } else {
                System.out.println("TEST FAILED| No results are displayed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
