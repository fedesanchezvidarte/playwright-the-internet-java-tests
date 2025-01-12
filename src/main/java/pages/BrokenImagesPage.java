package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class BrokenImagesPage {

    private final Page page;

    // Selectors
    private static final String IMAGE_SELECTOR = "//div/img";

    public BrokenImagesPage(Page page) {
        this.page = page;
    }

    /**
     * Get the total count of images on the page.
     *
     * @return Number of images.
     */
    public int getTotalImageCount() {
        return page.locator(IMAGE_SELECTOR).count();
    }

    public List<Integer> getBrokenImages() {
        List<Integer> brokenImages = new ArrayList<>();
        Locator images = page.locator(IMAGE_SELECTOR);

        List<Boolean> brokenStatuses = (List<Boolean>) page.locator("img").evaluateAll(
                "images => images.map(img => img.naturalWidth === 0)"
        );

        for (int i = 0; i < brokenStatuses.size(); i++) {
            if (brokenStatuses.get(i)) {
                brokenImages.add(i);
            }
        }

        return brokenImages;
    }
}
