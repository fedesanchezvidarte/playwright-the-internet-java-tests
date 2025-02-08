package pages;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;

/**
 * Page Object Model for the JavaScript Alerts page.
 */
public class JavaScriptAlertsPage {
    private final Page page;

    // Selectors
    private static final String JS_ALERT_BUTTON = "button[onclick='jsAlert()']";
    private static final String JS_CONFIRM_BUTTON = "button[onclick='jsConfirm()']";
    private static final String JS_PROMPT_BUTTON = "button[onclick='jsPrompt()']";
    private static final String RESULT_TEXT = "#result";

    public JavaScriptAlertsPage(Page page) {
        this.page = page;
    }

    /**
     * Clicks the JS Alert button and accepts the alert.
     */
    public void triggerJSAlert() {
        page.onceDialog(Dialog::accept);
        page.click(JS_ALERT_BUTTON);
    }

    /**
     * Clicks the JS Confirm button and accepts or dismisses the alert.
     * @param acceptAlert true to accept, false to dismiss.
     */
    public void triggerJSConfirm(boolean acceptAlert) {
        page.onceDialog(dialog -> {
            if (acceptAlert) {
                dialog.accept();
            } else {
                dialog.dismiss();
            }
        });
        page.click(JS_CONFIRM_BUTTON);
    }

    /**
     * Clicks the JS Prompt button, enters text into the prompt, and accepts or dismisses.
     * @param text The text to enter in the prompt.
     * @param acceptAlert true to accept, false to dismiss.
     */
    public void triggerJSPrompt(String text, boolean acceptAlert) {
        page.onceDialog(dialog -> {
            if (acceptAlert) {
                dialog.accept(text);
            } else {
                dialog.dismiss();
            }
        });
        page.click(JS_PROMPT_BUTTON);
    }

    /**
     * Gets the result text displayed on the page after interacting with the alert.
     * @return The result message.
     */
    public String getResultText() {
        return page.locator(RESULT_TEXT).textContent().trim();
    }
}
