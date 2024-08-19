# CoinMarketCapRepo

## CoinMarketCap Frontend Tests
This repository contains automated frontend tests for the CoinMarketCap website. The tests are written in Java using Selenium WebDriver and are managed using TestNG.

### Prerequisites
Before running the tests, we have to ensure that we have the following installed:

1. Java Development Kit (JDK) 8 or higher
   Download JDK: https://www.oracle.com/java/technologies/downloads/
2. Apache Maven (for dependency management)
   Install Maven: https://maven.apache.org/download.cgi
3. Google Chrome (latest version)
   Download Chrome: https://www.google.com/chrome/
4. ChromeDriver (compatible with our installed Chrome version)
   Download ChromeDriver: https://googlechromelabs.github.io/chrome-for-testing/
5. Selenium for Java
   Download Selenium: https://www.selenium.dev/downloads/

### Setup Instructions
1. Clone the Repository

First, we have to clone the repository to our local machine:

> git clone <repository-url>
> cd <repository-directory>

2. Install Dependencies

We navigate to the project directory and install the necessary dependencies using Maven:

> mvn clean install

3. Configure ChromeDriver Path

Make sure the path to ChromeDriver is correctly set in the test classes (Test1.java, Test2.java and Test3.java).

> System.setProperty("webdriver.chrome.driver", "path-to-your-chromedriver");

We will replace "path-to-your-chromedriver" with the actual path where chromedriver.exe is located on our machine.

### Running the tests
We will run the tests using an IDE (e.g., IntelliJ IDEA, Eclipse)
1. Import the project into your preferred IDE.
2. Right-click on the test class you want to run (Test1.java, Test2.java, Test3.java).
3. Select "Run 'Test1'", "'Run Test2'" or "Run 'Test3'" to execute the test.

### Test Details

**FE Test 1: Verify "View All" Results Displayed**

1. Opens the CoinMarketCap website.
2. Clicks on the "View All" button.
3. Verifies that all results are displayed on the page and if they are, all the tests will pass.

**FE Test 2: Add Cryptocurrencies to Watchlist and Verify**

1. Opens the CoinMarketCap website.
2. Selects between 5 and 10 random cryptocurrencies from the list.
3. Adds the selected cryptocurrencies to the Watchlist by clicking the star icon next to each cryptocurrency.
4. Opens the Watchlist page in a new browser tab.
5. Verifies that the selected cryptocurrencies have been added to the Watchlist and if they are, all the tests will pass and it will print out in the terminal that the test has passed.

**FE Test 3: Filter Functionality on Full List of Cryptocurrencies**
1. Opens the CoinMarketCap website.
2. Navigates to the Cryptocurrencies tab and clicks on the Ranking Full List option.
3. Records the data of the current page.
4. Applies filters using the available button called filters above the tab list.
5. After filters's been clicked, it will click on the Category button and open the drop-down menu.
6. After Category has been clicked, it will click on the Platform option and apply the filter.
7. Compares the filtered data against the initially recorded data to ensure they different and prints out the result in the terminal.

## CoinMarketCap Backend Tests
This repository contains automated backend tests for the CoinMarketCap API. The tests are implemented in Java using the RestAssured library for making HTTP requests to the API.

### Prerequisites
Before running the backend tests, ensure that you have the following installed:

1. Java Development Kit (JDK) 8 or higher
   Download JDK: https://www.oracle.com/java/technologies/downloads/
2. Apache Maven (for dependency management)
   Install Maven: https://maven.apache.org/download.cgi
3. CoinMarketCap API Key
   Sign up at CoinMarketCap to get your API key: https://coinmarketcap.com/api/

### Setup Instructions
1. Clone the Repository

First, we have to clone the repository to our local machine:

> git clone <repository-url>
> cd <repository-directory>

2. Install Dependencies

Navigate to the project directory and install the necessary dependencies using Maven:

> mvn clean install

3. Configure API Key

We replace the placeholder API key in the BTest1.java file with our actual CoinMarketCap API key.

> private static final String API_KEY = "your-api-key-here";

Replace "your-api-key-here" with the actual API key you received after signing up.

### Running the tests
We will run the tests using an IDE (e.g., IntelliJ IDEA, Eclipse)
1. Import the project into your preferred IDE.
2. Right-click on the test class you want to run (BTest1.java, BTest2.java or BTest3.java).
3. Select "Run 'BTest1.main()'", "'Run BTest2.main()'" or "Run 'BTest3.main()'" to execute the test.

### Test Details

**BE Test 1: Cryptocurrency ID Retrieval and Conversion to Bolivian Boliviano (BOB)**

This test performs the following steps:

1. Retrieve the IDs of Bitcoin (BTC), USD Tether (USDT), and Ethereum (ETH) using the /cryptocurrency/map API call.
2. Convert the retrieved cryptocurrency IDs to Bolivian Boliviano (BOB) using the /tools/price-conversion API call.
3. The test prints the results to the console, including the ID of each cryptocurrency and its price in Bolivian Boliviano.

**BE Test 2: Ethereum Cryptocurrency Information Validation**

This test performs the following steps for Ethereum (ID: 1027):

1. Retrieve Ethereum technical documentation website using the /cryptocurrency/info API call.
2. Validate the following information:

   -Logo URL: https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png

   -Technical Documentation URI: https://github.com/ethereum/wiki/wiki/White-Paper

   -Symbol: ETH

   -Date Added: 2015-08-07T00:00:00.000Z

   -Platform: null
   
   -Tags: Contains "mineable"

The test checks each of these values and prints the results to the console, indicating whether each validation has passed or failed.

**BE Test 3: Mineable Tag Validation for the First 10 Cryptocurrencies**

This test performs the following steps for the first 10 cryptocurrencies:

1. Retrieve the first 10 cryptocurrencies using the /cryptocurrency/info API call (IDs 1 to 10).
2. Check which cryptocurrencies have the mineable tag associated with them:
   -The test identifies and prints out the IDs, names, and tags of the cryptocurrencies that have the mineable tag.
3. Verify that the correct cryptocurrencies have been printed out:
   -The test outputs all the cryptocurrencies and highlights those that are mineable.