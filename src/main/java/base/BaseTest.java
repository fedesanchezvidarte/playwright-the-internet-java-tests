package base;

import com.microsoft.playwright.*;
import utils.BrowserFactory;
import utils.ConfigReader;
import org.testng.annotations.*;
import utils.PageSelector;
import utils.SubPage;

public class BaseTest {

    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setup() {
        String browserType = ConfigReader.getProperty("browser");
        browser = BrowserFactory.getBrowser(browserType);
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterClass
    public void tearDown() {
        if (context != null) {
            context.close();
        }
        BrowserFactory.closeBrowser();
    }

    /**
     * Navigate to a specific page using the main menu text.
     *
     * @param subPage Name of the page in the menu.
     */
    protected void navigateToPage(SubPage subPage) {
        String url = PageSelector.getPageUrl(subPage);
        page.navigate(url);
    }
}
