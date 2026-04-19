package com.sweetcart.pages;

import com.sweetcart.config.ConfigReader;
import com.sweetcart.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage – all shared wait utilities.
 * Zero Thread.sleep() – only WebDriverWait and FluentWait.
 * All Page classes extend this.
 */
public abstract class BasePage {

    protected WebDriver     driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait   = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getInstance().getTimeout()));
    }

    /** Wait until visible, return element */
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Wait until clickable, click */
    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /** Wait until visible, clear and type */
    protected void waitAndType(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    /** FluentWait – polls every 500ms, for slow-loading elements */
    protected WebElement fluentWait(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getInstance().getTimeout()))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** JS click – bypasses any overlay */
    protected void jsClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    /** Scroll element into view then click */
    protected void scrollAndClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    /** Check if element exists without throwing */
    protected boolean isElementPresent(By locator) {
        try { return !driver.findElements(locator).isEmpty(); }
        catch (Exception e) { return false; }
    }

    public String getPageTitle()  { return driver.getTitle(); }
    public String getCurrentUrl() { return driver.getCurrentUrl(); }
}
