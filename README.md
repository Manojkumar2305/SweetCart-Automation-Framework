# SweetCart-Automation-Framework
SweetCart Automation Framework – A Selenium Java Test Automation Framework using Page Object Model (POM) and TestNG to automate a sweet shop web application, covering authentication, product browsing, shopping cart, and user account workflows with reporting and reusable design.


# 🍬 SweetCart – Selenium Java Automation Framework

## 📌 Overview

SweetCart is a **Selenium WebDriver + Java Automation Framework** built to test a sweet shop web application.
It follows the **Page Object Model (POM)** design pattern to ensure modular, reusable, and maintainable test automation.

The framework automates **end-to-end user journeys** including login, product browsing, basket operations, and account validation.

---

## 🎯 Features

* ✅ Page Object Model (POM) architecture
* ✅ TestNG framework with DataProviders
* ✅ WebDriverManager integration
* ✅ Explicit wait strategy (WebDriverWait)
* ✅ Config-driven execution
* ✅ Screenshot capture on failure
* ✅ ExtentReports HTML reporting
* ✅ Retry mechanism for flaky tests
* ✅ Clean and scalable design

---

## 🌐 Application Under Test

🔗 https://sweetshop.netlify.app

Modules covered:

* Authentication (Login/Logout)
* Product Browsing
* Basket Management
* Account & Orders
* Navigation & Static Content

---

## 🧱 Project Structure

```id="p3d8k2"
SweetCartFramework/
│
├── src/test/java
│   ├── tests/           → Test classes
│   ├── pages/           → Page Object classes
│   ├── utils/           → Utilities (ConfigReader, Listeners, Waits)
│   ├── dataproviders/   → Test data
│
├── src/main/resources
│   └── config.properties
│
├── screenshots/         → Failure screenshots
├── test-output/         → Reports
├── pom.xml              → Maven dependencies
├── testng.xml           → Test suite
```

---

## ⚙️ Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* ExtentReports

---

## 🔧 Configuration

```id="h2x9lz"
browser=chrome
base.url=https://sweetshop.netlify.app
timeout=15
```

---

## 🧪 Test Modules

### 1️⃣ User Authentication

* Valid login verification
* Invalid login validation
* Logout functionality
* Unauthorized access handling

### 2️⃣ Product Browsing

* Verify product listing
* Filter by category
* Validate product details
* Verify Add to Basket option

### 3️⃣ Shopping Basket

* Add/remove products
* Validate basket count update
* Verify product details in basket
* Handle multiple items

### 4️⃣ Account & Orders

* Verify user profile details
* Validate order history section
* Check order data (date, total)

### 5️⃣ Navigation & Static Content

* Validate navigation links
* Verify About page content
* Validate footer links

---

## ▶️ How to Run

### Run using Maven

```id="l9q2we"
mvn clean test
```

### Run using TestNG

* Right click → `testng.xml` → Run

---

## 📊 Reporting

* ExtentReports generates HTML report
* Includes:

  * Test results (Pass/Fail)
  * Logs
  * Screenshots on failure

---

## 📸 Screenshot on Failure

* Automatically captured using TestNG Listener
* Stored in `/screenshots/` with timestamp

---

## 🔁 Retry Mechanism

* Implemented using `IRetryAnalyzer`

---
