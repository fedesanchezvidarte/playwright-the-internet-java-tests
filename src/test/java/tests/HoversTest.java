package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HoversPage;
import utils.Logger;
import utils.SubPage;

public class HoversTest extends BaseTest {

    private HoversPage hoversPage;

    @BeforeMethod
    public void navigateToHoversPage() {
        Logger.info("Navigating to Hovers page...");
        navigateToPage(SubPage.HOVERS);
        hoversPage = getPageObject(HoversPage.class);
    }

    @Test
    public void verifyUserAvatarsAreDisplayed() {
        Logger.info("Verifying user avatars are displayed...");
        int avatarCount = hoversPage.getUserAvatarCount();
        Assert.assertTrue(
                avatarCount > 0,
                "There should be at least one user avatar.");

        Logger.pass("User avatars are displayed correctly.");
    }

    @Test
    public void verifyCaptionsAppearOnHover() {
        Logger.info("Verifying captions appear when hovering over users...");
        int avatarCount = hoversPage.getUserAvatarCount();
        Assert.assertTrue(
                avatarCount > 0,
                "No user avatars found.");

        for (int i = 0; i < avatarCount; i++) {
            Logger.info("Hovering over user " + (i + 1) + "...");
            hoversPage.hoverOverUser(i);
            Assert.assertTrue(hoversPage.isCaptionVisible(i),
                    "Caption should be visible after hovering over user " + (i + 1));

            Logger.pass("Caption appeared for user " + (i + 1) + ".");
        }
    }

    @Test // Example validation
    public void verifyCaptionsContainCorrectUsernames() {
        Logger.info("Verifying captions contain correct usernames...");

        int avatarCount = hoversPage.getUserAvatarCount();
        Assert.assertTrue(avatarCount > 0, "No user avatars found.");

        for (int i = 0; i < avatarCount; i++) {
            Logger.info("Hovering over user " + (i + 1) + "...");
            hoversPage.hoverOverUser(i);

            Assert.assertTrue(hoversPage.isCaptionVisible(i),
                    "Caption should be visible for user " + (i + 1));

            String profileName = hoversPage.getProfileName(i);
            Logger.info("Extracted profile name:\n" + profileName);

            Assert.assertFalse(profileName.isEmpty(), "Profile name should not be empty.");
            Assert.assertTrue(profileName.contains("user"), "Profile name should contain 'user'."); // Example validation

            Logger.pass("Profile name extracted and validated for user " + (i + 1) + ".");
        }
    }


    @Test
    public void verifyProfileLinksAreClickable() {
        Logger.info("Verifying profile links are clickable...");
        int avatarCount = hoversPage.getUserAvatarCount();
        Assert.assertTrue(
                avatarCount > 0,
                "No user avatars found.");

        for (int i = 0; i < avatarCount; i++) {
            Logger.info("Hovering over user " + (i + 1) + "...");
            hoversPage.hoverOverUser(i);
            Assert.assertTrue(
                    hoversPage.isCaptionVisible(i),
                    "Caption should be visible for user " + (i + 1));

            Logger.info("Clicking on profile link for user " + (i + 1) + "...");
            hoversPage.clickProfileLink(i);

            Assert.assertTrue(
                    getPage().url().contains("users"),
                    "Clicking on the profile link should navigate to a user profile."); // Default: NOT FOUND

            Logger.pass("Successfully navigated to profile page for user " + (i + 1) + ".");

            // Navigate back to Hovers page for next test iteration
            navigateToPage(SubPage.HOVERS);
        }
    }
}
