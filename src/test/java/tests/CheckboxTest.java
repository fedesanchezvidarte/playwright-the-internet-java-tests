package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import utils.SubPage;

public class CheckboxTest extends BaseTest {

    private CheckboxPage checkboxPage;

    @BeforeMethod
    public void navigateToCheckboxPage() {
        navigateToPage(SubPage.CHECKBOXES);
        checkboxPage = new CheckboxPage(page);
    }

    @Test
    public void verifyInitialCheckboxStates() {
        // Verify the initial state of the checkboxes
        Assert.assertFalse(checkboxPage.isCheckboxChecked(0),
                "Checkbox 1 should be unchecked by default.");
        Assert.assertTrue(checkboxPage.isCheckboxChecked(1),
                "Checkbox 2 should be checked by default.");
    }

    @Test
    public void checkAndUncheckCheckbox() {
        // Check checkbox
        int index = 0;
        checkboxPage.checkCheckbox(index);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + "should be checked");

        // Uncheck checkbox
        checkboxPage.unCheckCheckbox(index);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + "should be unchecked");
    }

    @Test
    public void toggleCheckbox() {
        // Toggle checkbox
        int index = 1;
        checkboxPage.toggleCheckbox(index);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(index), "Checkbox " + (index + 1) + " should be unchecked.");

        // Toggle Checkbox back
        checkboxPage.toggleCheckbox(index);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(index), "Checkbox " + (index + 1) + " should be checked.");
    }
}
