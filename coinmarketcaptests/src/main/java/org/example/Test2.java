package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {
    public static void main(String[] args) {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://coinmarketcap.com/");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
