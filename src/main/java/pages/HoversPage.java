package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Page Object Model for the Hovers page.
 */
public class HoversPage {

    private final Page page;

    // Selectors
    private static final String USER_AVATARS = ".figure";
    private static final String USER_CAPTIONS = ".figcaption";
    private static final String PROFILE_LINKS = ".figcaption a";

    public HoversPage(Page page) {
        this.page = page;
    }

    /**
     * Gets the total number of user avatars displayed.
     *
     * @return The count of avatars.
     */
    public int getUserAvatarCount() {
        return page.locator(USER_AVATARS).count();
    }

    /**
     * Hovers over a user avatar based on index.
     *
     * @param index The index of the avatar (0-based).
     */
    public void hoverOverUser(int index) {
        List<Locator> avatars = page.locator(USER_AVATARS).all();
        if (index < avatars.size()) {
            avatars.get(index).hover();
        }
    }

    /**
     * Checks if the caption is visible for a given user after hovering.
     *
     * @param index The index of the user (0-based).
     * @return True if the caption is visible, false otherwise.
     */
    public boolean isCaptionVisible(int index) {
        List<Locator> captions = page.locator(USER_CAPTIONS).all();
        return index < captions.size() && captions.get(index).isVisible();
    }

    /**
     * Gets the profile name of a user based on index.
     *
     * @param index The index of the user (0-based).
     * @return The profile name.
     */
    public String getProfileName(int index) {
        List<Locator> captions = page.locator(USER_CAPTIONS).all();
        return index < captions.size() ? captions.get(index).textContent().trim() : "";
    }

    /**
     * Clicks on a user's profile link.
     *
     * @param index The index of the user (0-based).
     */
    public void clickProfileLink(int index) {
        List<Locator> links = page.locator(PROFILE_LINKS).all();
        if (index < links.size()) {
            links.get(index).click();
        }
    }
}
