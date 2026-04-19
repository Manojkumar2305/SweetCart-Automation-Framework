package com.sweetcart.pages;

import org.openqa.selenium.By;

/**
 * ProductDetailPage – individual sweet product page.
 * sweetshop.netlify.app product pages use Bootstrap cards.
 */
public class ProductDetailPage extends BasePage {

    private final By productName    = By.cssSelector("h1, h2, h3, .product-title");
    private final By productPrice   = By.cssSelector(".price, strong, h4, .card-text");
    private final By productDesc    = By.cssSelector("p, .description, .card-body p");
    private final By addToBasketBtn = By.cssSelector(
            "a[href*='basket'], a.btn, .btn-success, button.btn");

    public String getProductName() {
        try { return waitForElement(productName).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public String getProductPrice() {
        try { return waitForElement(productPrice).getText().trim(); }
        catch (Exception e) { return ""; }
    }

    public boolean isAddToBasketPresent() {
        return isElementPresent(addToBasketBtn);
    }

    public void clickAddToBasket() { waitAndClick(addToBasketBtn); }

    public boolean isProductNameShown() { return !getProductName().isEmpty(); }

    public boolean isPriceShown() {
        String p = getProductPrice();
        return !p.isEmpty() && (p.contains("£") || p.contains("$") || p.matches(".*\\d.*"));
    }

    public boolean isDescriptionShown() {
        try { return waitForElement(productDesc).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}
