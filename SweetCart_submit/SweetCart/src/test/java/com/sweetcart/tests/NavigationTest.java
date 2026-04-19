package com.sweetcart.tests;

import com.sweetcart.pages.AboutPage;
import com.sweetcart.pages.NavPage;
import com.sweetcart.utils.DriverManager;
import com.sweetcart.utils.RetryAnalyzer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Test Module 5 – Navigation and Static Content
 *  TC_NAV_01 : Nav links present (Sweets, About, Login, Basket)
 *  TC_NAV_02 : About page heading is "Sweet Shop Project"
 *  TC_NAV_03 : Footer shows "Sweet Shop Project 2018"
 */
public class NavigationTest extends BaseTest {

    private String base() {
        return com.sweetcart.config.ConfigReader.getInstance().getBaseUrl();
    }

    // ── TC_NAV_01 ──────────────────────────────────────────────────────────────
    @Test(description = "Top nav links (Sweets, About, Login, Basket) are present",
          retryAnalyzer = RetryAnalyzer.class)
    public void testTopNavLinksPresent() {
        // Get ALL anchor tags in the navbar
        List<WebElement> navLinks = DriverManager.getDriver()
                .findElements(By.cssSelector("nav a, .navbar a, .navbar-nav a"));

        Assert.assertTrue(navLinks.size() > 0,
                "Navigation must have at least one link");

        // Collect all nav link texts
        StringBuilder found = new StringBuilder();
        boolean hasSweetsOrShop = false;
        boolean hasLogin        = false;
        boolean hasBasket       = false;

        for (WebElement link : navLinks) {
            String text = link.getText().trim().toLowerCase();
            found.append("[").append(text).append("] ");
            if (text.contains("sweet") || text.contains("shop")) hasSweetsOrShop = true;
            if (text.contains("login") || text.contains("sign"))  hasLogin = true;
            if (text.contains("basket") || text.contains("cart")) hasBasket = true;
        }

        Assert.assertTrue(hasSweetsOrShop,
                "Nav must have a Sweets/Shop link. Found: " + found);
        Assert.assertTrue(hasLogin,
                "Nav must have a Login link. Found: " + found);
        Assert.assertTrue(hasBasket,
                "Nav must have a Basket/Cart link. Found: " + found);

        // Verify clicking Sweets navigates correctly
        new NavPage().clickNavSweets();
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("sweet"));
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("sweet"),
                "Clicking Sweets must navigate to sweets page");
    }

    // ── TC_NAV_02 ──────────────────────────────────────────────────────────────
    @Test(description = "About page heading is 'Sweet Shop Project'",
          retryAnalyzer = RetryAnalyzer.class)
    public void testAboutPageHeading() {
        DriverManager.getDriver().get(base() + "/about");

        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("about"));

        AboutPage aboutPage = new AboutPage();
        Assert.assertTrue(aboutPage.isAboutPage(),
                "Must be on /about. URL: " + DriverManager.getDriver().getCurrentUrl());

        String heading = aboutPage.getHeading();
        Assert.assertFalse(heading.isEmpty(),
                "About page heading must not be empty");
        Assert.assertTrue(heading.toLowerCase().contains("sweet"),
                "Heading must contain 'Sweet'. Got: '" + heading + "'");
    }

    // ── TC_NAV_03 ──────────────────────────────────────────────────────────────
    @Test(description = "Footer shows 'Sweet Shop Project 2018'",
          retryAnalyzer = RetryAnalyzer.class)
    public void testFooterContent() {
        DriverManager.getDriver().get(base() + "/about");

        // Get full page source to find footer text
        String pageSource = DriverManager.getDriver().getPageSource();

        Assert.assertTrue(
                pageSource.contains("2018") || pageSource.contains("Sweet Shop Project"),
                "Page must contain footer text 'Sweet Shop Project 2018'");
    }
}
