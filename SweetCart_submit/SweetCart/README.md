# SweetCart – Sweet Shop Automation Framework

## AUT
**URL:** https://sweetshop.netlify.app
**No registration needed** – use demo credentials below

## Login Credentials
| Field | Value |
|---|---|
| Email | test@example.com |
| Password | letmein |

## Tech Stack
| Tool | Version |
|---|---|
| Java | 11+ |
| Selenium | 4.18.1 |
| TestNG | 7.9.0 |
| WebDriverManager | 5.7.0 |
| ExtentReports | 5.1.1 |
| Apache POI | 5.2.5 (Excel) |
| Jackson | 2.16.1 (JSON) |

## Project Structure
```
SweetCart/
├── pom.xml
├── testng.xml
├── screenshots/                    ← auto-created on failure
├── test-output/
│   └── SweetCartReport.html        ← auto-generated after run
└── src/
    ├── main/java/com/sweetcart/
    │   ├── config/ConfigReader.java
    │   ├── pages/
    │   │   ├── BasePage.java        ← WebDriverWait + FluentWait utilities
    │   │   ├── LoginPage.java
    │   │   ├── NavPage.java
    │   │   ├── SweetsPage.java
    │   │   ├── ProductDetailPage.java
    │   │   ├── BasketPage.java
    │   │   ├── AccountPage.java
    │   │   └── AboutPage.java
    │   ├── utils/
    │   │   ├── DriverManager.java   ← ThreadLocal + Chrome popup fix
    │   │   ├── ScreenshotUtil.java
    │   │   ├── ExtentReportManager.java
    │   │   └── RetryAnalyzer.java
    │   └── listeners/TestListener.java
    └── test/
        ├── java/com/sweetcart/
        │   ├── dataproviders/TestDataProvider.java
        │   └── tests/
        │       ├── BaseTest.java
        │       ├── AuthTest.java           ← Module 1
        │       ├── ProductBrowseTest.java  ← Module 2
        │       ├── BasketTest.java         ← Module 3
        │       ├── AccountTest.java        ← Module 4
        │       └── NavigationTest.java     ← Module 5
        └── resources/
            ├── config.properties
            ├── loginData.json
            └── testdata.xlsx (LoginData + ProductData sheets)
```

## Eclipse Setup
1. File → Import → Maven → Existing Maven Projects → browse to SweetCart
2. Right-click pom.xml → Maven → Update Project (Alt+F5)
3. Run: `mvn test`

## All Hackathon Requirements Met
| Requirement | Implementation |
|---|---|
| POM – 7 Page classes | LoginPage, NavPage, SweetsPage, ProductDetailPage, BasketPage, AccountPage, AboutPage |
| BasePage shared utility | waitForElement, fluentWait, jsClick, scrollAndClick |
| No Thread.sleep() | WebDriverWait + FluentWait only |
| No hardcoded values | config.properties + testdata.xlsx + loginData.json |
| No WebDriver in tests | DriverManager + BaseTest handle all lifecycle |
| @DataProvider from Excel | LoginData sheet via Apache POI |
| @DataProvider from JSON | loginData.json via Jackson |
| Screenshot on failure | TestListener auto-captures with timestamp |
| ExtentReports HTML | test-output/SweetCartReport.html |
| Parallel execution | thread-count="2" parallel="methods" |
| Retry on failure | RetryAnalyzer (1 retry per test) |
| Headless mode | headless=true in config.properties |
| mvn test via testng.xml | Surefire plugin configured |
| Chrome popup fix | DriverManager prefs disable password manager |
