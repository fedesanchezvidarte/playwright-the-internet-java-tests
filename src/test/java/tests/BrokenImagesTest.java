package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImagesPage;
import utils.Logger;
import utils.SubPage;

import java.util.List;

public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeMethod
    public void navigateToBrokenImagesPage() {
        Logger.info("Navigating to Broken Images page...");
        navigateToPage(SubPage.BROKEN_IMAGES);
        brokenImagesPage = getPageObject(BrokenImagesPage.class);
    }

    @Test
    public void verifyNoBrokenImages() {
        Logger.info("Checking for broken images on the page...");
        int totalImages = brokenImagesPage.getTotalImageCount();
        List<Integer> brokenImages = brokenImagesPage.getBrokenImages();

        Logger.info("Total images found: " + totalImages);
        Logger.info("Broken images found: " + brokenImages.size());

        Assert.assertTrue(
                brokenImages.isEmpty(),
                "There are broken images on the page at indices: " + brokenImages
        );

        Logger.pass("All images are loaded successfully");
    }
}
