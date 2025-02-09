package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.NotificationMessagesPage;
import utils.Logger;
import utils.SubPage;

import java.util.List;

public class NotificationMessagesTest extends BaseTest {

    private NotificationMessagesPage notificationMessagesPage;
    private static final List<String> EXPECTED_MESSAGES = List.of(
            "Action successful",
            "Action unsuccessful, please try again",
            "Action unsuccesful, please try again"   // Temporal message with typo (bug)
    );


    @BeforeMethod
    public void navigateToNotificationMessagesPage() {
        Logger.info("Navigating to Notification Messages page...");
        navigateToPage(SubPage.NOTIFICATION_MESSAGES);
        notificationMessagesPage = getPageObject(NotificationMessagesPage.class);
    }

    @Test
    public void verifyNotificationMessages() {
        Logger.info("Verifying notification messages...");

        for (int i = 0; i < 5; i++) {  // Run multiple times to validate random message
            Logger.info("Generating notification message (attempt " + (i + 1) + ")...");
            notificationMessagesPage.clickGenerateNotification();

            Assert.assertTrue(notificationMessagesPage.isNotificationVisible(),
                    "Notification message should be visible.");

            String actualMessage = notificationMessagesPage.getNotificationMessage();
            String cleanMessage = actualMessage.replace("×", "").trim(); // Clean close button "×"

            Assert.assertTrue(EXPECTED_MESSAGES.contains(cleanMessage),
                    "Notification message should be one of the expected values. Found: " + cleanMessage);

            Logger.pass("Verified notification message: " + cleanMessage);
        }
    }
}
