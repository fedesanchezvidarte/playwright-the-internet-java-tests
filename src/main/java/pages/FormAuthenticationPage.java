package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Form Authentication page.
 */
public class FormAuthenticationPage {

    private final Page page;

    // Selectors
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "button[type='submit']";
    private static final String MESSAGE = "#flash";
    private static final String LOGOUT_BUTTON = "a[href='/logout']";
    private static final String SECURE_AREA_TEXT = "h2";

    public FormAuthenticationPage(Page page) {
        this.page = page;
    }

    /**
     * Logs in with the given username and password.
     * @param username The username.
     * @param password The password.
     */
    public void login(String username, String password) {
        page.fill(USERNAME_INPUT, username);
        page.fill(PASSWORD_INPUT, password);
        page.click(LOGIN_BUTTON);
    }

    /**
     * Retrieves the login result message.
     * @return The message text.
     */
    public String getLoginMessage() {
        return page.locator(MESSAGE).textContent().trim();
    }

    /**
     * Checks if the logout button is visible (indicating successful login).
     * @return True if logout button is visible, otherwise false.
     */
    public boolean isLogoutButtonVisible() {
        return page.locator(LOGOUT_BUTTON).isVisible();
    }

    /**
     * Performs a logout action.
     */
    public void logout() {
        page.click(LOGOUT_BUTTON);
    }

    /**
     * Checks if the secure area text is displayed after login.
     * @return True if displayed, otherwise false.
     */
    public boolean isSecureAreaVisible() {
        return page.locator(SECURE_AREA_TEXT).isVisible();
    }
}
