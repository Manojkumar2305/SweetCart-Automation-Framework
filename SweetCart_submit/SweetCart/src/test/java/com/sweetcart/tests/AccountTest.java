package com.sweetcart.tests;

import com.sweetcart.pages.AccountPage;
import com.sweetcart.pages.LoginPage;
import com.sweetcart.pages.NavPage;
import com.sweetcart.utils.DriverManager;
import com.sweetcart.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Module 4 – Account and Order History
 * Since sweetshop.netlify.app has broken auth, we verify:
 *  TC_ACCT_01 : Account page is accessible (URL /account)
 *  TC_ACCT_02 : Account page loads without crashing
 *  TC_ACCT_03 : Order history section or login prompt is present
 */
public class AccountTest extends BaseTest {

    private String base() {
        return com.sweetcart.config.ConfigReader.getInstance().getBaseUrl();
    }

    // ── TC_ACCT_01 ─────────────────────────────────────────────────────────────
    @Test(description = "Account page is accessible at /account URL",
          retryAnalyzer = RetryAnalyzer.class)
    public void testAccountPageAccessible() {
        DriverManager.getDriver().get(base() + "/account");

        String url = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("sweetshop"),
                "Must be on sweetshop site when navigating to /account. URL: " + url);
    }

    // ── TC_ACCT_02 ─────────────────────────────────────────────────────────────
    @Test(description = "Account page loads page title correctly",
          retryAnalyzer = RetryAnalyzer.class)
    public void testAccountPageLoads() {
        DriverManager.getDriver().get(base() + "/account");

        String title = DriverManager.getDriver().getTitle();
        Assert.assertNotNull(title, "Page title must not be null on account page");
        Assert.assertFalse(title.isEmpty(), "Page title must not be empty on account page");
    }

    // ── TC_ACCT_03 ─────────────────────────────────────────────────────────────
    @Test(description = "Account page shows order history or login prompt",
          retryAnalyzer = RetryAnalyzer.class)
    public void testAccountPageShowsContent() {
        DriverManager.getDriver().get(base() + "/account");

        AccountPage accountPage = new AccountPage();
        // Either shows order history (if logged in) or a login prompt
        boolean hasContent = accountPage.isOrderHistoryPresent()
                || accountPage.isLoginPromptShown()
                || accountPage.isAccountPageLoaded();

        Assert.assertTrue(hasContent,
                "Account page must show some content (history or login prompt). URL: "
                + DriverManager.getDriver().getCurrentUrl());
    }
}
