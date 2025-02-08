package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import utils.Logger;
import utils.SubPage;
import utils.TestDataManager;

import java.util.List;

public class CheckboxTest extends BaseTest {

    private CheckboxPage checkboxPage;
    private List<Boolean> initialStates;

    @BeforeMethod
    public void navigateToCheckboxPage() {
        Logger.info("Navigating to Checkboxes page...");
        navigateToPage(SubPage.CHECKBOXES);
        checkboxPage = getPageObject(CheckboxPage.class);

        // Load initial states for checkboxes
        Logger.info("Loading initial checkbox states from test data...");
        initialStates = (List<Boolean>) TestDataManager.getSection("checkboxes").get("initialStates");
    }

    @Test
    public void verifyInitialCheckboxStates() {
        Logger.info("Verifying initial checkbox states...");

        Assert.assertEquals(
                checkboxPage.isCheckboxChecked(0),
                initialStates.get(0),
                "Checkbox 1 state mismatch."
        );
        Logger.pass("Checkbox 1 state is correct.");

        Assert.assertEquals(
                checkboxPage.isCheckboxChecked(1),
                initialStates.get(1),
                "Checkbox 2 state mismatch."
        );
        Logger.pass("Checkbox 2 state is correct.");
    }

    @Test
    public void checkAndUncheckCheckbox() {
        Logger.info("Checking and unchecking checkbox...");

        int index = 0;

        checkboxPage.checkCheckbox(index);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + " should be checked");
        Logger.pass("Checkbox " + (index + 1) + " was checked.");

        checkboxPage.unCheckCheckbox(index);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + " should be unchecked");
        Logger.pass("Checkbox " + (index + 1) + " was unchecked.");
    }

    @Test
    public void toggleCheckbox() {
        Logger.info("Toggling checkbox...");

        int index = 1;

        checkboxPage.toggleCheckbox(index);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + " should be unchecked.");
        Logger.pass("Checkbox " + (index + 1) + " was toggled to unchecked.");

        checkboxPage.toggleCheckbox(index);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(index),
                "Checkbox " + (index + 1) + " should be checked.");
        Logger.pass("Checkbox " + (index + 1) + " was toggled back to checked.");
    }
}
