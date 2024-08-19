# CoinMarketCapRepo

## CoinMarketCap Frontend Tests
This repository contains automated frontend tests for the CoinMarketCap website. The tests are written in Java using Selenium WebDriver and are managed using TestNG.

### Prerequisites
Before running the tests, we have to ensure that we have the following installed:

Java Development Kit (JDK) 8 or higher
Download JDK
Apache Maven (for dependency management)
Install Maven
Google Chrome (latest version)
Download Chrome
ChromeDriver (compatible with our installed Chrome version)
Download ChromeDriver

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

### Running the Tests
We will do a test using an IDE (e.g., IntelliJ IDEA, Eclipse)
1. Import the project into your preferred IDE.
2. Right-click on the test class you want to run (Test1.java).
3. Select "Run 'Test1'" to execute the test.

### Test Details

Test1: Verify "View All" Results Displayed

1. Opens the CoinMarketCap website.
2. Clicks on the "View All" button.
3. Verifies that all results are displayed on the page and if they are it will show "TEST PASSED" in the terminal.