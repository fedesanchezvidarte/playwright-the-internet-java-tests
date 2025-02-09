package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Notification Messages page.
 */
public class NotificationMessagesPage {

    private final Page page;

    // Selectors
    private static final String CLICK_HERE_LINK = "a[href='/notification_message']";
    private static final String NOTIFICATION_MESSAGE = "#flash";

    public NotificationMessagesPage(Page page) {
        this.page = page;
    }

    /**
     * Clicks the "Click here" link to generate a new notification message.
     */
    public void clickGenerateNotification() {
        page.locator(CLICK_HERE_LINK).click();
    }

    /**
     * Gets the notification message text.
     *
     * @return The trimmed notification message.
     */
    public String getNotificationMessage() {
        return page.locator(NOTIFICATION_MESSAGE).textContent().trim();
    }

    /**
     * Checks if the notification message is visible.
     *
     * @return true if the notification message is visible, false otherwise.
     */
    public boolean isNotificationVisible() {
        return page.locator(NOTIFICATION_MESSAGE).isVisible();
    }
}
