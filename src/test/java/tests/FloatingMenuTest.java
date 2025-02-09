package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FloatingMenuPage;
import utils.Logger;
import utils.SubPage;

public class FloatingMenuTest extends BaseTest {

    private FloatingMenuPage floatingMenuPage;

    @BeforeMethod
    public void navigateToFloatingMenuPage() {
        Logger.info("Navigating to Floating Menu page...");
        navigateToPage(SubPage.FLOATING_MENU);
        floatingMenuPage = getPageObject(FloatingMenuPage.class);
    }

    @Test
    public void verifyFloatingMenuVisibility() {
        Logger.info("Verifying that the floating menu remains visible after scrolling...");

        // Verify visibility before scrolling
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Floating menu should be visible before scrolling.");

        Logger.pass("Floating menu is visible before scrolling.");

        // Scroll to middle and verify visibility
        floatingMenuPage.scrollToMiddle();
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Floating menu should remain visible after scrolling to the middle.");

        Logger.pass("Floating menu is visible after scrolling to the middle.");

        // Scroll to bottom and verify visibility
        floatingMenuPage.scrollToBottom();
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Floating menu should remain visible after scrolling to the bottom.");

        Logger.pass("Floating menu is visible after scrolling to the bottom.");
    }
}
