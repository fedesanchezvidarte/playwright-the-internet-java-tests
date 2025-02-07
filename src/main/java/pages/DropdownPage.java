package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object Model for the Dropdown page.
 */
public class DropdownPage {

    private final Page page;

    // Selectors
    private static final String DROPDOWN = "select#dropdown";
    private static final String SELECTED_OPTION = "select#dropdown option:checked";

    public DropdownPage(Page page) {
        this.page = page;
    }

    // Methods
    public void selectOptionByValue(String value) {
        page.locator(DROPDOWN).selectOption(value);
    }

    public String getSelectedOptionText() {
        return page.locator(SELECTED_OPTION).textContent();
    }

    public boolean isDropdownVisible() {
        return page.locator(DROPDOWN).isVisible();
    }
}
