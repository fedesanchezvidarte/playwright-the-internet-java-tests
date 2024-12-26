package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import utils.SubPage;
import utils.TestDataManager;

import java.util.List;

public class CheckboxTest extends BaseTest {

    private CheckboxPage checkboxPage;
    private List<Boolean> initialStates;

    @BeforeMethod
    public void navigateToCheckboxPage() {
        navigateToPage(SubPage.CHECKBOXES);
        checkboxPage = getPageObject(CheckboxPage.class);

        // Load initial states for checkboxes
        initialStates = (List<Boolean>) TestDataManager.getSection("checkboxes").get("initialStates");
    }

    @Test
    public void verifyInitialCheckboxStates() {
        // Verify the initial state of the checkboxes
        Assert.assertEquals(
                checkboxPage.isCheckboxChecked(0),
                initialStates.get(0),
                "Checkbox 1 state mismatch."
        );
        Assert.assertEquals(
                checkboxPage.isCheckboxChecked(1),
                initialStates.get(1),
                "Checkbox 2 state mismatch."
        );
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
