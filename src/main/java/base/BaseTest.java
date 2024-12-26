package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.FrameworkConfig;
import utils.Logger;
import utils.SubPage;

public class BaseTest {

    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    /**
     * Sets up the BrowserContext and Page for the current thread before each test class.
     */
    @BeforeClass
    public void setup() {
        Logger.info("Starting browser setup...");
        Browser browser = BrowserFactory.getBrowser(FrameworkConfig.getBrowser());
        BrowserContext localContext = browser.newContext();
        context.set(localContext);
        Page localPage = localContext.newPage();
        page.set(localPage);
        Logger.info("Browser setup complete.");
    }

    /**
     * Cleans up the BrowserContext and Browser after each test class.
     */
    @AfterClass
    public void tearDown() {
        Logger.info("Closing browser...");
        BrowserFactory.closeBrowser();
        Logger.info("Browser closed.");
    }

    /**
     * Gets the current Page instance for the current thread.
     *
     * @return The Page instance.
     */
    protected Page getPage() {
        return page.get();
    }

    /**
     * Navigates to a specific URL relative to the base URL.
     *
     * @param subPage The relative URL of the page to navigate to.
     */
    protected void navigateToPage(SubPage subPage) {
        String url = FrameworkConfig.getBaseUrl() + subPage.getRelativeUrl();
        Logger.info("Navigating to: " + url);
        getPage().navigate(url);
    }

    /**
     * Creates an instance of a Page Object.
     *
     * @param pageClass The class of the Page Object.
     * @return An instance of the specified Page Object.
     */
    protected <T> T getPageObject(Class<T> pageClass) {
        try {
            Logger.debug("Initializing Page Object: " + pageClass.getName());
            return pageClass.getDeclaredConstructor(Page.class).newInstance(getPage());
        } catch (Exception e) {
            Logger.error("Failed to initialize Page Object: " + pageClass.getName());
            throw new RuntimeException("Failed to initialize Page Object: " + pageClass.getName(), e);
        }
    }
}
