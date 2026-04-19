package com.sweetcart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * AccountPage – /account
 * Shows user profile and order history.
 */
public class AccountPage extends BasePage {

    private final By pageHeading      = By.cssSelector("h1, h2, .page-title");
    private final By userName         = By.cssSelector(".user-name, h4, .account-name, #name, p strong");
    private final By orderHistSection = By.cssSelector(".order-history, #orderHistory, table, .orders");
    private final By orderRows        = By.cssSelector(".order-row, tbody tr, .order-item");
    private final By orderDates       = By.cssSelector("td:first-child, .order-date");
    private final By orderTotals      = By.cssSelector("td:last-child, .order-total");
    private final By logoutLink       = By.cssSelector("a[href*='logout'], button[onclick*='logout'], .logout-btn");
    private final By loginPrompt      = By.cssSelector(".alert, #loginRequired, p.lead");

    public boolean isAccountPageLoaded() {
        try { return getCurrentUrl().contains("account")
                    && waitForElement(pageHeading).isDisplayed(); }
        catch (Exception e) { return getCurrentUrl().contains("account"); }
    }

    public boolean isUserNameDisplayed() {
        try { return !waitForElement(userName).getText().trim().isEmpty(); }
        catch (Exception e) { return false; }
    }

    public String getUserName() {
        try { return waitForElement(userName).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public boolean isOrderHistoryPresent() {
        return isElementPresent(orderHistSection);
    }

    public int getOrderCount() {
        return driver.findElements(orderRows).size();
    }

    public boolean doOrderRowsHaveDates() {
        List<WebElement> dates = driver.findElements(orderDates);
        return !dates.isEmpty() && dates.stream().anyMatch(d -> !d.getText().trim().isEmpty());
    }

    public boolean doOrderRowsHaveTotals() {
        List<WebElement> totals = driver.findElements(orderTotals);
        return !totals.isEmpty() && totals.stream().anyMatch(t -> !t.getText().trim().isEmpty());
    }

    public boolean isLoginPromptShown() {
        return isElementPresent(loginPrompt);
    }

    public void clickLogout() {
        try { waitAndClick(logoutLink); }
        catch (Exception e) {
            jsClick(logoutLink);
        }
    }
}
