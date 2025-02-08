package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.JavaScriptAlertsPage;
import utils.Logger;
import utils.SubPage;

public class JavaScriptAlertsTest extends BaseTest {

    private JavaScriptAlertsPage javaScriptAlertsPage;

    @BeforeMethod
    public void navigateToJavaScriptAlertsPage() {
        Logger.info("Navigating to JavaScript Alerts page...");
        navigateToPage(SubPage.JAVASCRIPT_ALERTS);
        javaScriptAlertsPage = getPageObject(JavaScriptAlertsPage.class);
    }

    @Test
    public void handleJSAlert() {
        Logger.info("Triggering JavaScript Alert...");
        javaScriptAlertsPage.triggerJSAlert();

        String result = javaScriptAlertsPage.getResultText();
        Assert.assertEquals(result, "You successfully clicked an alert",
                "The confirmation message should match after accepting the alert.");
        Logger.pass("JavaScript Alert handled successfully.");
    }

    @Test
    public void handleJSConfirmAccept() {
        Logger.info("Triggering JavaScript Confirm and accepting...");
        javaScriptAlertsPage.triggerJSConfirm(true);

        String result = javaScriptAlertsPage.getResultText();
        Assert.assertEquals(result, "You clicked: Ok",
                "The confirmation message should match after accepting the confirm alert.");
        Logger.pass("JavaScript Confirm alert accepted successfully.");
    }

    @Test
    public void handleJSConfirmDismiss() {
        Logger.info("Triggering JavaScript Confirm and dismissing...");
        javaScriptAlertsPage.triggerJSConfirm(false);

        String result = javaScriptAlertsPage.getResultText();
        Assert.assertEquals(result, "You clicked: Cancel",
                "The confirmation message should match after dismissing the confirm alert.");
        Logger.pass("JavaScript Confirm alert dismissed successfully.");
    }

    @Test
    public void handleJSPromptAccept() {
        Logger.info("Triggering JavaScript Prompt and entering text...");
        String inputText = "Playwright Test";
        javaScriptAlertsPage.triggerJSPrompt(inputText, true);

        String result = javaScriptAlertsPage.getResultText();
        Assert.assertEquals(result, "You entered: " + inputText,
                "The confirmation message should match the input text after accepting.");
        Logger.pass("JavaScript Prompt handled successfully with input.");
    }

    @Test
    public void handleJSPromptDismiss() {
        Logger.info("Triggering JavaScript Prompt and dismissing...");
        javaScriptAlertsPage.triggerJSPrompt("", false);

        String result = javaScriptAlertsPage.getResultText();
        Assert.assertEquals(result, "You entered: null",
                "The confirmation message should be 'You entered: null' after dismissing.");
        Logger.pass("JavaScript Prompt dismissed successfully.");
    }
}
