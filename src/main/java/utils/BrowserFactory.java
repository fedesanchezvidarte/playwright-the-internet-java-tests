package utils;

import com.microsoft.playwright.*;

public class BrowserFactory {

    private static Browser browser;
    private static final BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
    private static Playwright playwright;

    public static Browser getBrowser(String browserType) {
        if (browserType != null) {
            playwright = Playwright.create();

            // Headless mode configuration
            String headlessProperty = ConfigReader.getProperty("headless");
            boolean isHeadless = Boolean.parseBoolean(headlessProperty);
            options.setHeadless(isHeadless);

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
