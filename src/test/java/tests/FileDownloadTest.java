package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FileDownloadPage;
import utils.SubPage;
import utils.TestDataManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Test cases for the File Download page.
 */
public class FileDownloadTest extends BaseTest {

    private FileDownloadPage fileDownloadPage;
    private Map<String, Object> testData;

    @BeforeMethod
    public void navigateToFileDownloadPage() {
        navigateToPage(SubPage.FILE_DOWNLOAD);
        fileDownloadPage = getPageObject(FileDownloadPage.class);

        // Load test data
        testData = TestDataManager.getSection("fileDownload");
    }

    @Test
    public void downloadFileTest() {
        // Get expected file name
        String expectedFileName = fileDownloadPage.getExpectedFileName();

        // Download the file
        Path downloadedFile = fileDownloadPage.downloadFile();

        // Validate file exists
        Assert.assertTrue(Files.exists(downloadedFile),
                "The downloaded file should exist: " + downloadedFile);

        // Validate file name
        Assert.assertEquals(downloadedFile.getFileName().toString(), expectedFileName,
                "The downloaded file name should match expected.");
    }
}
