package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DropdownPage;
import utils.SubPage;

public class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;

    @BeforeMethod
    public void navigateToDropdownPage() {
        navigateToPage(SubPage.DROPDOWN);
        dropdownPage = new DropdownPage(page);
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
        String defaultOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(defaultOption,
                "Please select an option",
                "Default option should be 'Please select an option'.");
    }

    @Test
    public void selectOption1() {
        // Select Option 1
        dropdownPage.selectOptionByValue("1");

        // Verify Option 1 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption,
                "Option 1",
                "Option 1 should be selected");
    }

    @Test
    public void selectOption2() {
        // Select Option 2
        dropdownPage.selectOptionByValue("2");

        // Verify Option 2 is selected
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption,
                "Option 2",
                "Option 2 should be selected");
    }
}
