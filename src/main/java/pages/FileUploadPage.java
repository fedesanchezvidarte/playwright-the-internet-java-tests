package pages;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

/**
 * Page Object Model for the File Upload page.
 */
public class FileUploadPage {

    private final Page page;

    // Selectors
    private static final String FILE_INPUT = "#file-upload";
    private static final String UPLOAD_BUTTON = "#file-submit";
    private static final String UPLOADED_FILE_NAME = "#uploaded-files";

    public FileUploadPage(Page page) {
        this.page = page;
    }

    /**
     * Uploads a file using the file input.
     * @param filePath The path of the file to upload.
     */
    public void uploadFile(String filePath) {
        page.setInputFiles(FILE_INPUT, Paths.get(filePath));
        page.click(UPLOAD_BUTTON);
    }

    /**
     * Retrieves the uploaded file name displayed on the page.
     * @return The uploaded file name.
     */
    public String getUploadedFileName() {
        return page.locator(UPLOADED_FILE_NAME).textContent().trim();
    }
}
