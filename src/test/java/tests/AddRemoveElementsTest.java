package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.SubPage;

public class AddRemoveElementsTest extends BaseTest {

    @BeforeMethod
    public void navigateToAddRemovePage() {
        navigateToPage(SubPage.ADD_REMOVE_ELEMENTS);
    }

    @Test
    public void verifyInitialPageLoad() {
        // Verify "Add Element" button is visible
        boolean isAddElementVisible = page.locator("button[onclick='addElement()']").isVisible();
        Assert.assertTrue(isAddElementVisible, "'Add Element' button should be visible on page load.");
    }

    @Test
    public void addSingleElement() {
        // Click on "Add Element"
        page.locator("button[onclick='addElement()']").click();

        // Verify one "Delete" button appears
        int deleteButtonCount = page.locator("button.added-manually").count();
        Assert.assertEquals(deleteButtonCount, 1, "There should be exactly 1 'Delete' button.");
    }
}
