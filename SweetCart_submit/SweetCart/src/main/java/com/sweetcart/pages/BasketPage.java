package com.sweetcart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BasketPage – /basket
 * sweetshop.netlify.app shows basket items in a table.
 */
public class BasketPage extends BasePage {

    private final By basketRows    = By.cssSelector("table tbody tr, .basket-item");
    private final By itemNames     = By.cssSelector("table tbody tr td:first-child, .item-name");
    private final By itemPrices    = By.cssSelector("table tbody tr td:nth-child(2), .item-price");
    private final By removeLinks   = By.cssSelector("a[href*='remove'], .remove-item, td a");
    private final By emptyMsg      = By.cssSelector("p, .empty-basket");
    private final By basketTable   = By.cssSelector("table, #basketItems");

    public boolean hasItems() {
        try {
            fluentWait(basketTable);
            return !driver.findElements(basketRows).isEmpty();
        } catch (Exception e) { return false; }
    }

    public int getItemCount() {
        return driver.findElements(basketRows).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());
    }

    public String getFirstItemName() {
        try { return fluentWait(itemNames).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public String getFirstItemPrice() {
        try { return waitForElement(itemPrices).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public void removeFirstItem() {
        List<WebElement> links = driver.findElements(removeLinks);
        if (!links.isEmpty()) links.get(0).click();
    }

    public boolean isBasketEmpty() {
        try {
            return driver.findElements(basketRows).isEmpty();
        } catch (Exception e) { return true; }
    }
}
