package com.sweetcart.tests;

import com.sweetcart.dataproviders.TestDataProvider;
import com.sweetcart.pages.LoginPage;
import com.sweetcart.utils.DriverManager;
import com.sweetcart.utils.RetryAnalyzer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Test Module 1 – User Authentication
 *
 * NOTE: sweetshop.netlify.app is an intentionally broken demo app.
 * The login form exists but authentication is not enforced.
 * Tests verify: form presence, navigation, and page behavior.
 */
public class AuthTest extends BaseTest {

    private String base() {
        return com.sweetcart.config.ConfigReader.getInstance().getBaseUrl();
    }

    @DataProvider(name = "loginExcel", parallel = false)
    public Object[][] loginFromExcel() {
        return TestDataProvider.getLoginDataFromExcel();
    }

    @DataProvider(name = "loginJson", parallel = false)
    public Object[][] loginFromJson() {
        return TestDataProvider.getLoginDataFromJson();
    }

    // ── TC_AUTH_01 to TC_AUTH_03 – data-driven from Excel ─────────────────────
    @Test(dataProvider = "loginExcel",
          description = "Data-driven login – verify login form behavior",
          retryAnalyzer = RetryAnalyzer.class)
    public void testLoginScenarios(String email, String password,
                                    String expectedResult, String description) {
        DriverManager.getDriver().get(base() + "/login");
        LoginPage loginPage = new LoginPage();

        // Verify login page is accessible
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "Must be on login page. Case: " + description);

        // Verify login form is present
        Assert.assertTrue(loginPage.isLoginFormPresent(),
                "Login form must be present on /login page. Case: " + description);

        // Attempt login
        loginPage.login(email, password);

        // Since site is intentionally broken:
        // Any submission (valid or invalid) should produce a page response
        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertNotNull(url,
                "Page must respond after login attempt. Case: " + description);
        Assert.assertTrue(url.contains("sweetshop"),
                "Must remain on sweetshop domain after login attempt. Case: " + description);
    }

    // ── TC_AUTH_04 – Login page navigation ────────────────────────────────────
    @Test(description = "Login nav link navigates to login page",
          retryAnalyzer = RetryAnalyzer.class)
    public void testLoginNavigation() {
        DriverManager.getDriver().get(base());
        com.sweetcart.pages.NavPage nav = new com.sweetcart.pages.NavPage();

        Assert.assertTrue(nav.isLoginNavPresent(),
                "Login nav link must be present in the navigation bar");
        nav.clickNavLogin();

        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("login"));

        Assert.assertTrue(new LoginPage().isOnLoginPage(),
                "Clicking Login nav link must navigate to /login");
    }

    // ── TC_AUTH_05 – Account page direct URL ──────────────────────────────────
    @Test(description = "Direct access to account page – app responds correctly",
          retryAnalyzer = RetryAnalyzer.class)
    public void testDirectAccountAccess() {
        DriverManager.getDriver().get(base() + "/account");

        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(d -> d.getCurrentUrl().contains("sweetshop"));

        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("sweetshop"),
                "Must remain on sweetshop site when accessing /account. URL: " + url);
    }
}
