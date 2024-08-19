# CoinMarketCapRepo

## CoinMarketCap Frontend Tests
This repository contains automated frontend tests for the CoinMarketCap website. The tests are written in Java using Selenium WebDriver and are managed using TestNG.

### Prerequisites
Before running the tests, we have to ensure that we have the following installed:

1. Java Development Kit (JDK) 8 or higher
2. Download JDK
3. Apache Maven (for dependency management)
4. Install Maven
5. Google Chrome (latest version)
6. Download Chrome
7. ChromeDriver (compatible with our installed Chrome version)
8. Download ChromeDriver

### Setup Instructions
1. Clone the Repository

First, we have to clone the repository to our local machine:

> git clone <repository-url>
> cd <repository-directory>

2. Install Dependencies

Navigate to the project directory and install the necessary dependencies using Maven:

> mvn clean install

3. Configure ChromeDriver Path

Make sure the path to ChromeDriver is correctly set in the test classes (Test1.java, Test2.java and Test3.java).

> System.setProperty("webdriver.chrome.driver", "path-to-your-chromedriver");

Replace "path-to-your-chromedriver" with the actual path where chromedriver.exe is located on our machine.

### Running the tests
We will run the tests using an IDE (e.g., IntelliJ IDEA, Eclipse)
1. Import the project into your preferred IDE.
2. Right-click on the test class you want to run (Test1.java).
3. Select "Run 'Test1'", "'Run Test2'" or "Run 'Test3'" to execute the test.

### Test Details

**FE Test 1: Verify "View All" Results Displayed**

1. Opens the CoinMarketCap website.
2. Clicks on the "View All" button.
3. Verifies that all results are displayed on the page and if they are it will show "TEST PASSED" in the terminal.

**FE Test 2: Add Cryptocurrencies to Watchlist and Verify**

1. Opens the CoinMarketCap website.
2. Selects between 5 and 10 random cryptocurrencies from the list.
3. Adds the selected cryptocurrencies to the Watchlist by clicking the star icon next to each cryptocurrency.
4. Opens the Watchlist page in a new browser tab.
5. Verifies that the selected cryptocurrencies have been added to the Watchlist.