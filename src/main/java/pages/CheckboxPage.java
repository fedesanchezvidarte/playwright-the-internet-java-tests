package pages;

import com.microsoft.playwright.Page;

public class CheckboxPage {

    private final Page page;

    // Selectors
    private static final String CHECKBOXES = "form#checkboxes input";

    public CheckboxPage(Page page) {
        this.page = page;
    }

    /**
     * Get the checked state of a checkbox by its index (0-based).
     *
     * @param index Index of the checkbox.
     * @return True if checkbox is checked, false otherwise.
     */
    public boolean isCheckboxChecked(int index) {
        return page.locator(CHECKBOXES).nth(index).isChecked();
    }

    /**
     * Toggle a checkbox by its index (0-based)
     *
     * @param index Index of the checkbox.
     */
    public void toggleCheckbox(int index) {
        page.locator(CHECKBOXES).nth(index).click();
    }

    /**
     * Check a checkbox by its index (0-based)
     *
     * @param index Index of the checkbox.
     */
    public void checkCheckbox(int index) {
        if (!isCheckboxChecked(index)) {
            toggleCheckbox(index);
        }
    }

    /**
     * Uncheck a checkbox by its index (0-based).
     *
     * @param index Index of the checkbox.
     */
    public void unCheckCheckbox(int index) {
        if (isCheckboxChecked(index)) {
            toggleCheckbox(index);
        }
    }
}
