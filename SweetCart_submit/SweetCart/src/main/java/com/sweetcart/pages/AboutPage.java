package com.sweetcart.pages;

import org.openqa.selenium.By;

/**
 * AboutPage – /about
 * Verified from live site:
 *   Heading: "Sweet Shop Project"
 *   Content: "An intentionally broken web application..."
 *   Footer:  "Sweet Shop Project 2018"
 */
public class AboutPage extends BasePage {

    private final By pageHeading = By.cssSelector("h1");
    private final By pageContent = By.cssSelector("p");
    private final By footerText  = By.xpath("//*[contains(text(),'Sweet Shop Project 2018')]");

    public String getHeading() {
        try { return waitForElement(pageHeading).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public boolean isHeadingDisplayed() {
        try { return waitForElement(pageHeading).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isContentPresent() {
        return isElementPresent(pageContent);
    }

    public boolean isAboutPage() {
        return getCurrentUrl().contains("about");
    }

    public String getFooterText() {
        try { return waitForElement(footerText).getText().trim(); }
        catch (Exception e) { return ""; }
    }
}
