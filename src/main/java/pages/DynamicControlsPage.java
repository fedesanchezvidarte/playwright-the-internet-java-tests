package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Dynamic Controls page.
 */
public class DynamicControlsPage {

    private final Page page;

    // Selectors
    private static final String CHECKBOX = "#checkbox";
    private static final String REMOVE_ADD_BUTTON = "#checkbox-example button";
    private static final String CHECKBOX_MESSAGE = "#message";

    private static final String INPUT_FIELD = "#input-example input";
    private static final String ENABLE_DISABLE_BUTTON = "#input-example button";
    private static final String INPUT_MESSAGE = "#message";

    public DynamicControlsPage(Page page) {
        this.page = page;
    }

    /**
     * Clicks the Remove/Add button for the checkbox.
     */
    public void clickRemoveAddButton() {
        page.click(REMOVE_ADD_BUTTON);
    }

    /**
     * Checks if the checkbox is visible.
     * @return True if the checkbox is visible, otherwise false.
     */
    public boolean isCheckboxVisible() {
        return page.locator(CHECKBOX).isVisible();
    }

    /**
     * Retrieves the checkbox operation message.
     * @return The message text.
     */
    public String getCheckboxMessage() {
        return page.locator(CHECKBOX_MESSAGE).textContent().trim();
    }

    /**
     * Clicks the Enable/Disable button for the input field.
     */
    public void clickEnableDisableButton() {
        page.click(ENABLE_DISABLE_BUTTON);
    }

    /**
     * Checks if the input field is enabled.
     * @return True if the input field is enabled, otherwise false.
     */
    public boolean isInputFieldEnabled() {
        return page.locator(INPUT_FIELD).isEnabled();
    }

    /**
     * Retrieves the input field operation message.
     * @return The message text.
     */
    public String getInputMessage() {
        return page.locator(INPUT_MESSAGE).textContent().trim();
    }
}
