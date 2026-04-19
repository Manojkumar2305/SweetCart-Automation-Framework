package com.sweetcart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SweetsPage – /sweets
 * Verified from sweetshop.netlify.app:
 *   Product cards use Bootstrap card layout
 *   Each card has: image, title (h5.card-title), price, Add to Basket link
 */
public class SweetsPage extends BasePage {

    // Verified locators
    private final By productCards    = By.cssSelector(".card");
    private final By productTitles   = By.cssSelector(".card-title");
    private final By productPrices   = By.cssSelector(".card-text");
    private final By addToBasketBtns = By.cssSelector("a.btn, .card a[href*='basket']");
    private final By categoryLinks   = By.cssSelector(".nav-link");
    private final By pageHeading     = By.cssSelector("h1");

    public boolean hasProducts() {
        try {
            fluentWait(By.cssSelector(".card"));
            return !driver.findElements(productCards).isEmpty();
        } catch (Exception e) { return false; }
    }

    public int getProductCount() {
        return driver.findElements(productCards).size();
    }

    public List<String> getAllProductNames() {
        return driver.findElements(productTitles)
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickFirstProduct() {
        List<WebElement> cards = driver.findElements(productCards);
        if (!cards.isEmpty()) {
            // Click the card title link
            List<WebElement> links = cards.get(0).findElements(By.tagName("a"));
            if (!links.isEmpty()) links.get(0).click();
            else cards.get(0).click();
        }
    }

    public void addFirstProductToBasket() {
        List<WebElement> btns = driver.findElements(addToBasketBtns);
        if (!btns.isEmpty()) {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                    .elementToBeClickable(btns.get(0))).click();
        }
    }

    public void addProductToBasket(int index) {
        List<WebElement> btns = driver.findElements(addToBasketBtns);
        if (btns.size() > index) btns.get(index).click();
    }

    public void clickCategoryFilter(String category) {
        for (WebElement link : driver.findElements(categoryLinks)) {
            if (link.getText().trim().equalsIgnoreCase(category)) {
                link.click();
                return;
            }
        }
        // Fallback: click second nav-link (index 1 usually a category)
        List<WebElement> all = driver.findElements(categoryLinks);
        if (all.size() > 1) all.get(1).click();
    }

    public String getFirstProductName() {
        List<WebElement> titles = driver.findElements(productTitles);
        return titles.isEmpty() ? "" : titles.get(0).getText().trim();
    }
}
