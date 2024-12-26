package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DropdownPage;
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
        navigateToPage(SubPage.DROPDOWN);
        dropdownPage = new DropdownPage(page);

        // Load test data for Dropdown
        testData = TestDataManager.getSection("dropdown");
        options = (List<String>) testData.get("options");
    }

    @Test
    public void verifyDropdownIsVisible() {
        // Verify the dropdown menu is visible
        boolean isDropdownVisible = dropdownPage.isDropdownVisible();
        Assert.assertTrue(isDropdownVisible,
                "Dropdown menu should be visible on page load.");
    }

    @Test
    public void verifyDefaultValue() {
        // Verify default value is "Please select an option"
        String defaultOptionText = dropdownPage.getSelectedOptionText();
        String defaultValue = (String) testData.get("defaultValue");
        Assert.assertEquals(
                defaultOptionText,
                defaultValue,
                "Default option should be: " + defaultValue);
    }

    @Test
    public void selectOption1() {
        // Select Option 1
        dropdownPage.selectOptionByValue("1");

        // Verify Option 1 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption,
                options.get(0),
                "Option 1 should be selected");
    }

    @Test
    public void selectOption2() {
        // Select Option 2
        dropdownPage.selectOptionByValue("2");

        // Verify Option 2 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption,
                options.get(1),
                "Option 2 should be selected");
    }
}
