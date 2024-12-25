package utils;

public class PageSelector {

    /**
     * Obtain complete page URL according main menu text.
     *
     * @param subPage Page Name (text in the menu).
     * @return Complete URL.
     */
    public static String getPageUrl(SubPage subPage) {
        String baseUrl = "https://the-internet.herokuapp.com";
        return baseUrl + subPage.getRelativeUrl();
    }
}
