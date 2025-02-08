package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DynamicLoadingPage;
import utils.Logger;
import utils.SubPage;

public class DynamicLoadingTest extends BaseTest {

    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeMethod
    public void navigateToDynamicLoadingPage() {
        Logger.info("Navigating to Dynamic Loading page...");
        navigateToPage(SubPage.DYNAMIC_LOADING);
        dynamicLoadingPage = getPageObject(DynamicLoadingPage.class);
    }

    @Test
    public void verifyPageTitle() {
        Logger.info("Verifying that the Dynamic Loading page has the correct title...");
        String actualTitle = dynamicLoadingPage.getPageTitle();
        Assert.assertEquals(actualTitle, "Dynamically Loaded Page Elements",
                "The page title should be 'Dynamically Loaded Page Elements'.");
    }

    @Test
    public void verifyElementAppearsAfterLoading() {
        Logger.info("Navigating to Example 1: Hidden Element...");
        dynamicLoadingPage.navigateToExample1();

        Logger.info("Testing that the hidden element appears after clicking 'Start'...");

        // Start loading process
        dynamicLoadingPage.clickStartButton();
        dynamicLoadingPage.waitForLoadingToComplete();

        // Validate element visibility
        boolean isVisible = dynamicLoadingPage.isHelloWorldVisible();
        Assert.assertTrue(isVisible, "The 'Hello World!' text should be visible after loading.");
    }

    @Test
    public void verifyLoadedText() {
        Logger.info("Navigating to Example 2: Element rendered after the fact...");
        dynamicLoadingPage.navigateToExample2();

        Logger.info("Verifying the loaded text after dynamic loading...");

        // Start the loading process
        dynamicLoadingPage.clickStartButton();
        dynamicLoadingPage.waitForLoadingToComplete();

        // Validate the text content
        String actualText = dynamicLoadingPage.getHelloWorldText();
        Assert.assertEquals(actualText, "Hello World!",
                "The loaded text should be 'Hello World!'.");
    }
}
