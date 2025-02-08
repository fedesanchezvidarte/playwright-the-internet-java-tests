package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Page Object Model for the Dynamic Loading page.
 */
public class DynamicLoadingPage {

    private final Page page;

    // Selectors
    private static final String PAGE_TITLE = "h3";
    private static final String EXAMPLE_1_LINK = "a[href='/dynamic_loading/1']";
    private static final String EXAMPLE_2_LINK = "a[href='/dynamic_loading/2']";
    private static final String START_BUTTON = "#start button";
    private static final String LOADING_INDICATOR = "#loading";
    private static final String HELLO_WORLD_TEXT = "#finish h4";

    public DynamicLoadingPage(Page page) {
        this.page = page;
    }

    /**
     * Retrieves the title of the page (h3 header).
     *
     * @return The text content of the h3 element.
     */
    public String getPageTitle() {
        return page.locator(PAGE_TITLE).textContent().trim();
    }

    /**
     * Navigates to "Example 1: Element on page that is hidden".
     */
    public void navigateToExample1() {
        page.locator(EXAMPLE_1_LINK).click();
    }

    /**
     * Navigates to "Example 2: Element rendered after the fact".
     */
    public void navigateToExample2() {
        page.locator(EXAMPLE_2_LINK).click();
    }

    /**
     * Clicks the "Start" button.
     */
    public void clickStartButton() {
        page.locator(START_BUTTON).click();
    }

    /**
     * Waits for the loading indicator to disappear.
     */
    public void waitForLoadingToComplete() {
        page.waitForSelector(LOADING_INDICATOR, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }

    /**
     * Checks if the "Hello World!" text is visible.
     *
     * @return true if visible, false otherwise.
     */
    public boolean isHelloWorldVisible() {
        return page.locator(HELLO_WORLD_TEXT).isVisible();
    }

    /**
     * Gets the text of the dynamically loaded element.
     *
     * @return The text content of the element.
     */
    public String getHelloWorldText() {
        return page.locator(HELLO_WORLD_TEXT).textContent().trim();
    }
}
