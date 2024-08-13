package org.example;
import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;

        import java.util.List;
import java.util.stream.Collectors;

public class Test3 {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open https://coinmarketcap.com/
            driver.get("https://coinmarketcap.com/");

            // Step 2: Display the drop-down menu on the Cryptocurrencies tab
            displayCryptoDropdown(driver);

            // Step 3: Click any of the three Full List options on this menu
            clickFullListOption(driver);

            // Step 4: Record the data on the current page
            List<String> initialData = recordData(driver);

            // Step 5: Apply any combination of filters
            applyFilters(driver);

            // Step 6: Check against the data recorded in Step 4
            List<String> filteredData = recordData(driver);
            compareData(initialData, filteredData);

        } finally {
            // Close the browser after completing the tasks
            driver.quit();
        }
    }

    // Method to display the drop-down menu on the Cryptocurrencies tab
    private static void displayCryptoDropdown(WebDriver driver) {
        WebElement cryptoTab = driver.findElement(By.xpath("//a[@href='/ranking/']/ancestor::li"));
        cryptoTab.click();
    }

    // Method to click any of the Full List options
    private static void clickFullListOption(WebDriver driver) {
        WebElement fullListOption = driver.findElement(By.xpath("//a[text()='Top 100']"));
        fullListOption.click();
    }

    private static List<String> recordData(WebDriver driver) {
        List<WebElement> cryptoNames = driver.findElements(By.xpath("//table//tr/td[2]//p[@class='sc-1eb5slv-0 iworPT']"));
        List<WebElement> cryptoPrices = driver.findElements(By.xpath("//table//tr/td[4]//a[@class='cmc-link']"));

        // Print and return the data
        System.out.println("Recording Data:");
        for (int i = 0; i < cryptoNames.size(); i++) {
            String data = "Name: " + cryptoNames.get(i).getText() + ", Price: " + cryptoPrices.get(i).getText();
            System.out.println(data);
        }

        return cryptoNames.stream().map(WebElement::getText).collect(Collectors.toList());  // Collect data for comparison later
    }


    // Method to apply filters
    private static void applyFilters(WebDriver driver) {
        WebElement marketCapFilter = driver.findElement(By.xpath("//button[contains(text(), 'Market Cap')]"));
        marketCapFilter.click();

        WebElement filterOption = driver.findElement(By.xpath("//button[contains(text(), '$1B - $10B')]"));
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

