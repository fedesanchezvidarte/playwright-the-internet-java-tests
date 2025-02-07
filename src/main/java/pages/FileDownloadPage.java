package pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Page Object Model for the File Download page.
 */
public class FileDownloadPage {

    private final Page page;

    // Selector
    private static final String DOWNLOAD_LINK = "a[href*='download']";

    public FileDownloadPage(Page page) {
        this.page = page;
    }

    /**
     * Downloads a file and returns the path where it's saved.
     * @return The path of the downloaded file.
     */
    public Path downloadFile() {
        Download download = page.waitForDownload(() -> page.locator(DOWNLOAD_LINK).first().click());
        Path downloadPath = Paths.get("target/downloads/" + download.suggestedFilename());
        download.saveAs(downloadPath);
        return downloadPath;
    }

    /**
     * Gets the expected file name from the download link.
     * @return The expected file name.
     */
    public String getExpectedFileName() {
        return page.locator(DOWNLOAD_LINK).first().textContent().trim();
    }
}
