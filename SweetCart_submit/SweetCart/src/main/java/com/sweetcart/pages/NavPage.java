package com.sweetcart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * NavPage – top navigation bar and footer.
 * Verified from live site screenshot:
 *   Nav links: Sweet Shop (brand), Sweets, About, Login, Basket (with badge)
 */
public class NavPage extends BasePage {

    // Verified nav link locators from sweetshop.netlify.app
    private final By navBrand   = By.cssSelector(".navbar-brand, a.navbar-brand");
    private final By navSweets  = By.linkText("Sweets");
    private final By navAbout   = By.linkText("About");
    private final By navLogin   = By.linkText("Login");
    private final By navBasket  = By.linkText("Basket");

    // Basket count badge – shows "0" initially
    private final By basketBadge = By.cssSelector(".basket-count, .badge, span.badge-success, " +
                                                   "a[href*='basket'] .badge, " +
                                                   "a[href*='basket'] span");

    // Footer
    private final By footer      = By.cssSelector("footer, .footer, .container-fluid.bg-dark");
    private final By footerText  = By.xpath("//*[contains(text(),'Sweet Shop Project')]");

    public boolean isSweetsNavPresent() { return isElementPresent(navSweets); }
    public boolean isAboutNavPresent()  { return isElementPresent(navAbout); }
    public boolean isLoginNavPresent()  { return isElementPresent(navLogin); }
    public boolean isBasketNavPresent() { return isElementPresent(navBasket); }

    public void clickNavSweets()  { waitAndClick(navSweets); }
    public void clickNavAbout()   { waitAndClick(navAbout); }
    public void clickNavLogin()   { waitAndClick(navLogin); }
    public void clickNavBasket()  { waitAndClick(navBasket); }

    public int getNavLinkCount() {
        return driver.findElements(By.cssSelector("nav a, .navbar-nav a")).size();
    }

    public int getBasketCount() {
        try {
            String text = fluentWait(basketBadge).getText().trim();
            return text.isEmpty() ? 0 : Integer.parseInt(text);
        } catch (Exception e) { return 0; }
    }

    public boolean isFooterPresent() {
        return isElementPresent(footer) || isElementPresent(footerText);
    }

    public List<WebElement> getFooterLinks() {
        return driver.findElements(By.cssSelector("footer a"));
    }

    public String getFooterText() {
        try { return waitForElement(footerText).getText().trim(); }
        catch (Exception e) { return ""; }
    }
}
