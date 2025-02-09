package pages;

import com.microsoft.playwright.Page;

public class FloatingMenuPage {

    private final Page page;
    private static final String FLOATING_MENU = "#menu"; // Selector del menú flotante

    public FloatingMenuPage(Page page) {
        this.page = page;
    }

    /**
     * Scrolls down to the middle of the page.
     */
    public void scrollToMiddle() {
        page.evaluate("window.scrollBy(0, document.body.scrollHeight / 2)");
    }

    /**
     * Scrolls down to the bottom of the page.
     */
    public void scrollToBottom() {
        page.evaluate("window.scrollBy(0, document.body.scrollHeight)");
    }

    /**
     * Checks if the floating menu is visible.
     * @return true if the menu is visible, false otherwise.
     */
    public boolean isFloatingMenuVisible() {
        return page.locator(FLOATING_MENU).isVisible();
    }
}
