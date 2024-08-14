package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\QH0158\\IdeaProjects\\chromedriver\\chromedriver.exe");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open https://coinmarketcap.com/
            driver.get("https://coinmarketcap.com/");
            // Step 2: Display the drop-down menu on the Cryptocurrencies tab
            displayCryptoDropdown(driver);

            clickCryptoTabOption(driver);
            // Step 3: Click any of the three Full List options on this menu
            clickFullListOption(driver);
            // Step 4: Record the data on the current page
            List<String> initialData = recordData(driver);

            // Step 5: Apply any combination of filters
            applyFilters(driver);

            // Step 6: Check against the data recorded in Step 4
            //List<String> filteredData = recordData(driver);
            //compareData(initialData, filteredData);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after completing the tasks
            driver.quit();
        }
    }

    // Method to display the drop-down menu on the Cryptocurrencies tab
    private static void displayCryptoDropdown(WebDriver driver) {
        WebElement dropMenu = driver.findElement(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.sc-2e66506f-0.jztNxX > div.sc-d8ea16bb-0.hZhywa > div:nth-child(4) > div > div.sc-2fe50849-0.iqytLW > div:nth-child(3) > div"));
        dropMenu.click();


    }

    private static void clickCryptoTabOption(WebDriver driver) {
        WebElement cryptoTab = driver.findElement(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.sc-2e66506f-0.jztNxX > div.sc-d8ea16bb-0.hZhywa > div:nth-child(4) > div > div.sc-a27793cf-2.iUMgId > div.sc-a27793cf-3.SoVeO > section > div:nth-child(1) > div.sc-a27793cf-6.bRKAlq"));
        cryptoTab.click();
    }



    // Method to click any of the Full List options
    private static void clickFullListOption(WebDriver driver) {
        WebElement fullListOption = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[1]/div[2]/div[4]/div/div[3]/div[2]/section/div[1]/div[2]/div/div[1]/div[2]/a[1]"));
        fullListOption.click();
    }


    private static List<String> recordData(WebDriver driver) {
        // Pronađi sve redove u tabeli
        List<WebElement> rows = driver.findElement(By.tagName("table")).findElements(By.tagName("tr"));
        List<String> rowDataList = new ArrayList<>();



        // Iteriraj kroz svaki red
        for (WebElement row : rows) {
            // Pronađi sve kolone u trenutnom redu
            List<WebElement> columns = row.findElements(By.tagName("td"));
            StringBuilder rowData = new StringBuilder();

            // Iteriraj kroz svaku kolonu i uzmi tekst
            for (WebElement column : columns) {
                String columnText = column.getText();
                rowData.append(columnText).append(" | "); // Dodaj podatke iz kolone i separator
            }

            // Dodaj podatke iz trenutnog reda u listu redova
            rowDataList.add(rowData.toString().trim());

            // Ispisuj trenutni red u konzolu
            System.out.println(rowData.toString().trim());
        }

        // Vraća listu redova za dalju upotrebu
        return rowDataList;
    }


    // Method to apply filters
    private static void applyFilters(WebDriver driver) {

        WebElement marketCapFilter = driver.findElement(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.cmc-body-wrapper > div > div:nth-child(1) > div.sc-d577b7d4-4.dZhWhQ > div.sc-4c05d6ef-0.sc-c652d51c-1.sc-6fa8c3d4-0.dlQYLv.jOvctx.eDTcwB.table-control-outer-wrapper.scroll-indicator.scroll-initial.hideArrow > div.scroll-child > div.sc-4c05d6ef-0.dlQYLv.table-control-area > div > button.sc-7d96a92a-0.bvcvhD.sc-c652d51c-0.iDLcf.table-control-filter"));
        marketCapFilter.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#__next > div.sc-2e66506f-1.buMEwe.global-layout-v2 > div.main-content > div.cmc-body-wrapper > div > div:nth-child(1) > ul > li:nth-child(1) > div > span > button")));
        categoryOption.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filterOption = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tippy-15\"]/div/div[1]/div/div/div[1]/ul/li[1]")));
        filterOption.click();
    }

    // Method to compare data before and after applying filters
    private static void compareData(List<String> initialData, List<String> filteredData) {
        System.out.println("Comparing Data:");
        for (String data : filteredData) {
            if (initialData.contains(data)) {
                System.out.println("Match found: " + data);
            } else {
                System.out.println("New data after filter: " + data);
            }
        }
    }

}

