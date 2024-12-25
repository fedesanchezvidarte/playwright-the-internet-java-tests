package utils;

import com.microsoft.playwright.*;

public class BrowseFactory {

    private static Browser browser;
    private static BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
    private static Playwright playwright;

    public static Browser getBrowser(String browserType) {
        if (browserType == null) {
            playwright = Playwright.create();
            options.setHeadless(true); // Default configuration: headless
            switch (browserType.toLowerCase()) {
                case "firefox":
                    browser = playwright.firefox().launch(options);
                    break;
                case "webkit":
                    browser = playwright.webkit().launch(options);
                    break;
                case "chromium":
                default:
                    browser = playwright.chromium().launch(options);
                    break;
            }
        }
        return browser;
    }

    public static void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
