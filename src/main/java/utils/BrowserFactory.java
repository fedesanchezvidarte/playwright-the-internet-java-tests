package utils;

import com.microsoft.playwright.*;

public class BrowserFactory {

    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();

    /**
     * Retrieves or creates a Browser instance for the current thread.
     *
     * @param browserType The type of browser to launch (e.g., "chromium", "firefox", "webkit").
     * @return A Browser instance.
     */
    public static Browser getBrowser(String browserType) {
        if (playwright.get() == null) {
            playwright.set(Playwright.create());
        }

        if (browser.get() == null) {
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(FrameworkConfig.isHeadless()); // Configure headless mode via FrameworkConfig

            switch (browserType.toLowerCase()) {
                case "firefox":
                    browser.set(playwright.get().firefox().launch(options));
                    break;
                case "webkit":
                    browser.set(playwright.get().webkit().launch(options));
                    break;
                case "chromium":
                default:
                    browser.set(playwright.get().chromium().launch(options));
                    break;
            }
        }

        return browser.get();
    }

    /**
     * Closes the Browser and Playwright instances for the current thread.
     */
    public static void closeBrowser() {
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }
}
