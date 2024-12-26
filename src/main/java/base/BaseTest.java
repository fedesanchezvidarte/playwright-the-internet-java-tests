package base;

import com.microsoft.playwright.*;
import utils.*;
import org.testng.annotations.*;

public class BaseTest {

    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setup() {
        Logger.info("Starting browser setup...");
        browser = BrowserFactory.getBrowser(FrameworkConfig.getBrowser());
        context = browser.newContext();
        page = context.newPage();
        Logger.info("Browser setup complete.");
    }

    @AfterClass
    public void tearDown() {
        Logger.info("Closing browser...");
        if (context != null) {
            context.close();
        }
        BrowserFactory.closeBrowser();
        Logger.info("Browser closed.");
    }

    /**
     * Navigate to a specific page using the main menu text.
     *
     * @param subPage Name of the page in the menu.
     */
    protected void navigateToPage(SubPage subPage) {
        try {
            // Obtain complete URL by using SubPage
            String url = FrameworkConfig.getBaseUrl() + subPage.getRelativeUrl();
            Logger.info("Navigating to: " + url);
            page.navigate(url);
        } catch (Exception e) {
            Logger.error("Error navigating to page: " + subPage.getName());
            throw new RuntimeException("Failed to navigate to page: " + subPage.getName(), e);
        }
    }


    /**
     * Creates and returns an instance of the specified Page Object class.
     *
     * This method uses generics to dynamically initialize Page Object classes, ensuring
     * type safety and flexibility. It finds the appropriate constructor that accepts a
     * Playwright `Page` object and uses it to create the instance.
     *
     * @param <T> The type of the Page Object to be returned.
     * @param pageClass The class of the Page Object to be instantiated.
     * @return An instance of the specified Page Object class.
     * @throws RuntimeException If the Page Object cannot be instantiated.
     */
    protected <T> T getPage(Class<T> pageClass) {
        try {
            // Finds the constructor of the given class that accepts a Page as an argument
            return pageClass.getDeclaredConstructor(Page.class)
                    // Creates a new instance of the class using the Playwright Page instance
                    .newInstance(page);
        } catch (Exception e) {
            // Throws a runtime exception with a meaningful error message if instantiation fails
            throw new RuntimeException("Failed to initialize Page Object: " + pageClass.getName(), e);
        }
    }

}
