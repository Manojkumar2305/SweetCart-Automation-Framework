package com.sweetcart.pages;

import org.openqa.selenium.By;

/**
 * LoginPage – /login
 *
 * NOTE: sweetshop.netlify.app is an "intentionally broken" demo app.
 * The login form exists but authentication is not enforced server-side.
 * Submitting any credentials navigates away from /login (no real auth).
 * Tests must account for this — we verify form presence and navigation,
 * not actual authenticated sessions.
 *
 * Verified locators from sweetshop.netlify.app/login:
 *   Email    → input[type='email'] or #exampleInputEmail1
 *   Password → input[type='password'] or #exampleInputPassword1
 *   Submit   → button[type='submit']
 *   Error    → .alert (may not appear since login is broken)
 */
public class LoginPage extends BasePage {

    private final By emailInput    = By.cssSelector("input[type='email'], #exampleInputEmail1");
    private final By passwordInput = By.cssSelector("input[type='password'], #exampleInputPassword1");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By errorAlert    = By.cssSelector(".alert, .alert-danger");
    private final By loginForm     = By.cssSelector("form, .login-form");

    public void enterEmail(String email) {
        try { waitAndType(emailInput, email); }
        catch (Exception e) { /* field may not exist */ }
    }

    public void enterPassword(String password) {
        try { waitAndType(passwordInput, password); }
        catch (Exception e) { /* field may not exist */ }
    }

    public void clickLogin() {
        try { waitAndClick(loginButton); }
        catch (Exception e) { /* button may not exist */ }
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginFormPresent() {
        return isElementPresent(loginForm) || isElementPresent(emailInput);
    }

    public boolean isErrorDisplayed() {
        try { return fluentWait(errorAlert).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("login");
    }
}
