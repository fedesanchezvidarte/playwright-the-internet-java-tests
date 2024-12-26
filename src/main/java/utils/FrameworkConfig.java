package utils;

public class FrameworkConfig {

    private static final String BASE_URL = ConfigReader.getProperty("baseUrl");
    private static final String BROWSER = ConfigReader.getProperty("browser");
    private static final boolean HEADLESS = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBrowser() {
        return BROWSER;
    }

    public static boolean isHeadless() {
        return HEADLESS;
    }
}
