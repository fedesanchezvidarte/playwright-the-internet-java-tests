package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.BrowserFactory;
import utils.FrameworkConfig;
import utils.Logger;
import utils.SubPage;

import java.nio.file.Paths;

public class BaseTest {

    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    private boolean shouldSaveTrace = false;

    /**
     * Sets up the BrowserContext and Page for the current thread before each test class.
     * Starts tracing to enable detailed test insights.
     */
    @BeforeClass
    public void setup() {
        Logger.info("Starting browser setup...");
        Browser browser = BrowserFactory.getBrowser(FrameworkConfig.getBrowser());
        BrowserContext localContext = browser.newContext();
        context.set(localContext);

        // Start tracing
        Logger.debug("Starting trace recording...");
        localContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page localPage = localContext.newPage();
        page.set(localPage);
        Logger.info("Browser setup complete.");

    }

    /**
     * Marks trace saving if the test fails.
     *
     * @param result The TestNG test result object
     */
    @AfterMethod
    public void markTraceIfFailed(ITestResult result) {
        if (!result.isSuccess()) {
            Logger.warn("Test failed: " + result.getName() + ". Trace will be saved.");
            shouldSaveTrace = true;
        } else {
            shouldSaveTrace = false;
        }
    }

    /**
     * Cleans up the BrowserContext and Browser after each test class.
     * Stops tracing and saves it only if the test has failed.
     */
    @AfterClass
    public void tearDown() {
        Logger.info("Stopping trace recording...");
        BrowserContext localContext = context.get();

        if (localContext != null) {
            if (shouldSaveTrace) {
                Logger.info("Saving trace for debugging...");
                localContext.tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("target/trace/trace.zip")));
            } else {
                Logger.info("Skipping trace saving as no test failed.");
                localContext.tracing().stop();
            }
        }

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
