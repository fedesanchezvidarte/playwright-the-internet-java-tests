package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.KeyPressesPage;
import utils.Logger;
import utils.SubPage;
import utils.TestDataManager;

import java.util.Map;

public class KeyPressesTest extends BaseTest {

    private KeyPressesPage keyPressesPage;
    private Map<String, String> keyPressData;

    @BeforeMethod
    public void navigateToKeyPressesPage() {
        Logger.info("Navigating to Key Presses page...");
        navigateToPage(SubPage.KEY_PRESSES);
        keyPressesPage = getPageObject(KeyPressesPage.class);

        // Load key press test data from JSON
        Logger.info("Loading key press test data...");
        keyPressData = (Map<String, String>) TestDataManager.getSection("keyPresses").get("keys");
    }

    @Test
    public void verifyKeyPresses() {
        Logger.info("Verifying multiple key presses...");
        SoftAssert softAssert = new SoftAssert();

        for (Map.Entry<String, String> entry : keyPressData.entrySet()) {
            String key = entry.getKey();
            String expectedMessage = entry.getValue();

            Logger.info("Pressing key: " + key);
            keyPressesPage.pressKey(key);
            String actualMessage = keyPressesPage.getResultText();

            // Assert for each key without stopping execution
            softAssert.assertEquals(actualMessage, expectedMessage,
                    "Expected message for key press '" + key + "' does not match.");

            if (actualMessage.equals(expectedMessage)) {
                Logger.pass("Successfully verified key press for: " + key);
            } else {
                Logger.fail("Mismatch for key press '" + key + "'. Expected: '" + expectedMessage + "', but got: '" + actualMessage + "'");
            }
        }

        // Ensures all assertions are checked at the end
        softAssert.assertAll();
    }

    /**
     * @deprecated This DataProvider is deprecated in favor of using TestData.json.
     */
    @Deprecated
    @DataProvider(name = "keyPressData")
    public Object[][] keyPressDataProvider() {
        return new Object[][]{
                {"Enter", "You entered: ENTER"},
                {"Backspace", "You entered: BACK_SPACE"},
                {"Escape", "You entered: ESCAPE"},
                {"Space", "You entered: SPACE"},
                {"Shift", "You entered: SHIFT"},
                {"Tab", "You entered: TAB"}
        };
    }

    /**
     * @deprecated This test uses a deprecated DataProvider. Use verifyKeyPresses() instead.
     */
    @Deprecated
    @Test(dataProvider = "keyPressData")
    public void verifyKeyPressUsingDataProvider(String key, String expectedMessage) {
        Logger.info("Pressing key (from DataProvider): " + key);
        keyPressesPage.pressKey(key);
        String actualMessage = keyPressesPage.getResultText();

        Assert.assertEquals(actualMessage, expectedMessage,
                "Expected message for key press does not match.");
        Logger.pass("Successfully verified key press for: " + key);
    }
}
