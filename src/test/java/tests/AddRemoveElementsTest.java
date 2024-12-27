package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;
import utils.Logger;
import utils.SubPage;
import utils.TestDataManager;

import java.util.Map;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;
    private Map<String, Object> testData;

    @BeforeMethod
    public void navigateToAddRemovePage() {
        Logger.info("Navigating to Add/Remove Elements page...");
        navigateToPage(SubPage.ADD_REMOVE_ELEMENTS);
        addRemoveElementsPage = getPageObject(AddRemoveElementsPage.class);

        // Load test data for Add/Remove Elements
        Logger.info("Loading test data for Add/Remove Elements...");
        testData = TestDataManager.getSection("addRemoveElements");
    }

    @Test
    public void verifyInitialPageLoad() {
        Logger.info("Verifying initial page load...");
        try {
            boolean isAddElementVisible = addRemoveElementsPage.isAddElementButtonVisible();

            Assert.assertTrue(isAddElementVisible,
                    "'Add Element' button should be visible on page load.");
            Logger.pass("'Add Element' button is visible on page load.");
        } catch (AssertionError e) {
            Logger.fail("Failed to verify initial page load: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void addRemoveSingleElement() {
        Logger.info("Adding and removing a single element...");
        try {
            // Click on "Add Element"
            addRemoveElementsPage.clickAddElementButton();

            // Verify one "Delete" button appears
            int deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();
            int initialCount = (int) testData.get("initialCount");

            Assert.assertEquals(
                    deleteButtonCount,
                    initialCount + 1,
                    "There should be exactly 1 'Delete' button.");
            Logger.pass("Verified 1 'Delete' button appears after adding.");

            // Remove the "Delete" button
            addRemoveElementsPage.clickFirstDeleteButton();

            // Verify no "Delete" buttons remain
            deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();

            Assert.assertEquals(
                    deleteButtonCount,
                    initialCount,
                    "There should not be 'Delete' buttons after removal.");
            Logger.pass("Verified 'Delete' button is removed successfully.");
        } catch (AssertionError e) {
            Logger.fail("Failed to add/remove single element: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void addRemoveMultipleElements() {
        Logger.info("Adding and removing multiple elements...");
        try {
            // Click on "Add Element" multiple times
            int numOfElements = (int) testData.get("numOfElementsToAdd");
            for (int i = 0; i < numOfElements; i++) {
                addRemoveElementsPage.clickAddElementButton();
            }

            // Verify number of "Delete" buttons
            int deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();

            Assert.assertEquals(
                    deleteButtonCount,
                    numOfElements,
                    "There should be exactly " + numOfElements + " delete buttons.");
            Logger.pass("Verified " + numOfElements + " 'Delete' buttons are present.");

            // Remove all 'Delete' buttons
            addRemoveElementsPage.removeAllDeleteButtons();

            // Verify no "Delete" buttons remain
            deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();
            int initialCount = (int) testData.get("initialCount");

            Assert.assertEquals(
                    deleteButtonCount,
                    initialCount,
                    "There should not be 'Delete' buttons after removal.");
            Logger.pass("Verified all 'Delete' buttons are removed successfully.");
        } catch (AssertionError e) {
            Logger.fail("Failed to add/remove multiple elements: " + e.getMessage());
            throw e;
        }
    }
}
