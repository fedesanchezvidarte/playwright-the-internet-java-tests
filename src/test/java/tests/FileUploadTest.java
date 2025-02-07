package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FileUploadPage;
import utils.SubPage;
import utils.TestDataManager;

import java.util.Map;

/**
 * Test cases for the File Upload page.
 */
public class FileUploadTest extends BaseTest {

    private FileUploadPage fileUploadPage;
    private Map<String, Object> testData;

    @BeforeMethod
    public void navigateToFileUploadPage() {
        navigateToPage(SubPage.FILE_UPLOAD);
        fileUploadPage = getPageObject(FileUploadPage.class);

        // Load test data
        testData = TestDataManager.getSection("fileUpload");
    }

    @Test
    public void uploadValidFile() {
        String filePath = (String) testData.get("validFilePath");
        String expectedFileName = (String) testData.get("validFileName");

        fileUploadPage.uploadFile(filePath);

        Assert.assertEquals(fileUploadPage.getUploadedFileName(), expectedFileName,
                "Uploaded file name should match expected.");
    }
}
