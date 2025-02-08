package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DropdownPage;
import utils.Logger;
import utils.SubPage;
import utils.TestDataManager;

import java.util.List;
import java.util.Map;

public class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;
    private Map<String, Object> testData;
    private List<String> options;

    @BeforeMethod
    public void navigateToDropdownPage() {
        Logger.info("Navigating to Dropdown page...");
        navigateToPage(SubPage.DROPDOWN);
        dropdownPage = getPageObject(DropdownPage.class);

        // Load test data for Dropdown
        Logger.info("Loading test data for Dropdown...");
        testData = TestDataManager.getSection("dropdown");
        options = (List<String>) testData.get("options");
    }

    @Test
    public void verifyDropdownIsVisible() {
        Logger.info("Verifying dropdown visibility...");

        boolean isDropdownVisible = dropdownPage.isDropdownVisible();
        Assert.assertTrue(isDropdownVisible, "Dropdown menu should be visible on page load.");

        Logger.pass("Dropdown menu is visible on page load.");
    }

    @Test
    public void verifyDefaultValue() {
        Logger.info("Verifying default value of dropdown...");

        String defaultOptionText = dropdownPage.getSelectedOptionText();
        String defaultValue = (String) testData.get("defaultValue");

        Assert.assertEquals(
                defaultOptionText,
                defaultValue,
                "Default option should be: " + defaultValue);

        Logger.pass("Verified default value of dropdown is: " + defaultValue);
    }

    @Test
    public void selectOption1() {
        Logger.info("Selecting Option 1 in dropdown...");

        dropdownPage.selectOptionByValue("1");

        // Verify Option 1 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, options.get(0), "Option 1 should be selected.");

        Logger.pass("Verified Option 1 is selected.");
    }

    @Test
    public void selectOption2() {
        Logger.info("Selecting Option 2 in dropdown...");

        dropdownPage.selectOptionByValue("2");

        // Verify Option 2 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, options.get(1), "Option 2 should be selected.");

        Logger.pass("Verified Option 2 is selected.");
    }
}
