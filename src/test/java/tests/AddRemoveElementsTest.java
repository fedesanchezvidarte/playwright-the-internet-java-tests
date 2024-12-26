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
        testData = TestDataManager.getSection("addRemoveElements");
    }

    @Test
    public void verifyInitialPageLoad() {
        // Verify "Add Element" button is visible
        boolean isAddElementVisible = addRemoveElementsPage.isAddElementButtonVisible();
        Assert.assertTrue(isAddElementVisible,
                "'Add Element' button should be visible on page load.");
    }

    @Test
    public void addRemoveSingleElement() {
        // Click on "Add Element"
        addRemoveElementsPage.clickAddElementButton();

        // Verify one "Delete" button appears
        int deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();
        int initialCount = (int) testData.get("initialCount");
        Assert.assertEquals(
                deleteButtonCount,
                initialCount + 1,
                "There should be exactly 1 'Delete' button.");

        // Remove the "Delete" button
        addRemoveElementsPage.clickFirstDeleteButton();

        // Verify no "Delete" button remain
        deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();
        Assert.assertEquals(
                deleteButtonCount,
                initialCount,
                "There should not be 'Delete' buttons after removal."
        );
    }

    @Test
    public void addRemoveMultipleElements() {
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
                "There should be exactly " + numOfElements + " delete buttons"
        );

        // Remove all 'Delete' buttons
        addRemoveElementsPage.removeAllDeleteButtons();

        // Verify no "Delete" button remain
        deleteButtonCount = addRemoveElementsPage.getDeleteButtonCount();
        int initialCount = (int) testData.get("initialCount");
        Assert.assertEquals(
                deleteButtonCount,
                initialCount,
                "There should not be 'Delete' buttons after removal."
        );
    }
}
