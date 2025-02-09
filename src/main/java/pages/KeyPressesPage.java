package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Key Presses page.
 */
public class KeyPressesPage {

    private final Page page;

    // Selectors
    private static final String INPUT_FIELD = "#target";
    private static final String RESULT_TEXT = "#result";

    public KeyPressesPage(Page page) {
        this.page = page;
    }

    /**
     * Simulates a key press in the input field.
     *
     * @param key The key to be pressed.
     */
    public void pressKey(String key) {
        page.locator(INPUT_FIELD).press(key);
    }

    /**
     * Retrieves the result text that appears after pressing a key.
     *
     * @return The displayed message confirming the key press.
     */
    public String getResultText() {
        return page.locator(RESULT_TEXT).textContent().trim();
    }
}
