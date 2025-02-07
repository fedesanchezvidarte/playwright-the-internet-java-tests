package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DynamicControlsPage;
import utils.SubPage;
import utils.TestDataManager;

import java.util.Map;

/**
 * Test cases for the Dynamic Controls page.
 */
public class DynamicControlsTest extends BaseTest {

    private DynamicControlsPage dynamicControlsPage;
    private Map<String, Object> testData;

    @BeforeMethod
    public void navigateToDynamicControlsPage() {
        navigateToPage(SubPage.DYNAMIC_CONTROLS);
        dynamicControlsPage = getPageObject(DynamicControlsPage.class);

        // Load test data
        testData = TestDataManager.getSection("dynamicControls");
    }

    @Test
    public void removeCheckbox() {
        String expectedMessage = (String) testData.get("checkboxRemovedMessage");

        // Click Remove button and wait for checkbox to disappear
        dynamicControlsPage.clickRemoveAddButton();
        getPage().waitForCondition(() -> !dynamicControlsPage.isCheckboxVisible());

        Assert.assertFalse(dynamicControlsPage.isCheckboxVisible(),
                "Checkbox should be removed.");
        Assert.assertTrue(dynamicControlsPage.getCheckboxMessage().contains(expectedMessage),
                "Message should confirm checkbox removal.");
    }

    @Test
    public void addCheckbox() {
        String expectedMessage = (String) testData.get("checkboxAddedMessage");

        // Click Remove to remove checkbox
        dynamicControlsPage.clickRemoveAddButton();
        getPage().waitForCondition(() -> !dynamicControlsPage.isCheckboxVisible());

        // Click Add button and wait for checkbox to appear
        dynamicControlsPage.clickRemoveAddButton();
        getPage().waitForCondition(dynamicControlsPage::isCheckboxVisible);

        Assert.assertTrue(dynamicControlsPage.isCheckboxVisible(),
                "Checkbox should be added back.");
        Assert.assertTrue(dynamicControlsPage.getCheckboxMessage().contains(expectedMessage),
                "Message should confirm checkbox addition.");
    }

    @Test
    public void enableInputField() {
        String expectedMessage = (String) testData.get("inputEnabledMessage");

        // Click Enable button and wait for input field to become enabled
        dynamicControlsPage.clickEnableDisableButton();
        getPage().waitForCondition(dynamicControlsPage::isInputFieldEnabled);

        Assert.assertTrue(dynamicControlsPage.isInputFieldEnabled(),
                "Input field should be enabled.");
        Assert.assertTrue(dynamicControlsPage.getInputMessage().contains(expectedMessage),
                "Message should confirm input enabling.");
    }

    @Test
    public void disableInputField() {
        String expectedMessage = (String) testData.get("inputDisabledMessage");

        // Click Enable button to enable input
        dynamicControlsPage.clickEnableDisableButton();
        getPage().waitForCondition(dynamicControlsPage::isInputFieldEnabled);

        // Click Disable button and wait for input field to be disabled
        dynamicControlsPage.clickEnableDisableButton();
        getPage().waitForCondition(() -> !dynamicControlsPage.isInputFieldEnabled());

        Assert.assertFalse(dynamicControlsPage.isInputFieldEnabled(),
                "Input field should be disabled.");
        Assert.assertTrue(dynamicControlsPage.getInputMessage().contains(expectedMessage),
                "Message should confirm input disabling.");
    }
}
