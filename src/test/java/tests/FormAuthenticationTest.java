package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormAuthenticationPage;
import utils.SubPage;
import utils.TestDataManager;

import java.util.Map;

public class FormAuthenticationTest extends BaseTest {

    private FormAuthenticationPage formAuthPage;
    private Map<String, Object> testData;

    @BeforeMethod
    public void navigateToFormAuthPage() {
        navigateToPage(SubPage.FORM_AUTHENTICATION);
        formAuthPage = getPageObject(FormAuthenticationPage.class);

        // Load test data
        testData = TestDataManager.getSection("formAuthentication");
    }

    @Test
    public void successfulLogin() {
        String validUsername = (String) testData.get("validUsername");
        String validPassword = (String) testData.get("validPassword");
        String successMessage = (String) testData.get("successMessage");

        formAuthPage.login(validUsername, validPassword);
        Assert.assertTrue(formAuthPage.isSecureAreaVisible(),
                "Secure area should be visible after successful login.");
        Assert.assertTrue(formAuthPage.getLoginMessage().contains(successMessage),
                "Success message should be displayed.");
    }

    @Test
    public void failedLogin() {
        String invalidUsername = (String) testData.get("invalidUsername");
        String invalidPassword = (String) testData.get("invalidPassword");
        String failureMessage = (String) testData.get("failureMessage");

        formAuthPage.login(invalidUsername, invalidPassword);
        Assert.assertTrue(formAuthPage.getLoginMessage().contains(failureMessage),
                "Failure message should be displayed after incorrect login.");
    }

    @Test
    public void logoutTest() {
        String validUsername = (String) testData.get("validUsername");
        String validPassword = (String) testData.get("validPassword");
        String logoutMessage = (String) testData.get("logoutMessage");

        // Login
        formAuthPage.login(validUsername, validPassword);
        Assert.assertTrue(formAuthPage.isLogoutButtonVisible(), "Logout button should be visible.");

        // Logout
        formAuthPage.logout();
        Assert.assertTrue(formAuthPage.getLoginMessage().contains(logoutMessage),
                "Logout message should be displayed.");
    }
}
