package pages;

import com.microsoft.playwright.Page;

public class AddRemoveElementsPage {

    private final Page page;

    // Selectors
    private static final String ADD_ELEMENT_BUTTON = "button[onclick='addElement()']";
    private static final String DELETE_BUTTON = "button.added-manually";

    public AddRemoveElementsPage(Page page) {
        this.page = page;
    }

    // Click "Add Element" button
    public void clickAddElementButton() {
        page.locator(ADD_ELEMENT_BUTTON).click();
    }

    // Get count of "Delete" buttons
    public int getDeleteButtonCount() {
        return page.locator(DELETE_BUTTON).count();
    }

    // Click first "Delete" button
    public void clickFirstDeleteButton() {
        page.locator(DELETE_BUTTON).first().click();
    }

    // Remove all "Delete" buttons
    public void removeAllDeleteButtons() {
        while (getDeleteButtonCount() > 0) {
            clickFirstDeleteButton();
        }
    }

    // Verify if "Add Element" button is visible
    public boolean isAddElementButtonVisible() {
        return page.locator(ADD_ELEMENT_BUTTON).isVisible();
    }
}
