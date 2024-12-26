package utils;

public enum SubPage {
    AB_TESTING("A/B Testing", "/abtest"),
    ADD_REMOVE_ELEMENTS("Add/Remove Elements", "/add_remove_elements/"),
    BASIC_AUTH("Basic Auth", "/basic_auth"),
    BROKEN_IMAGES("Broken Images", "/broken_images"),
    CHALLENGING_DOM("Challenging DOM", "/challenging_dom"),
    CHECKBOXES("Checkboxes", "/checkboxes"),
    CONTEXT_MENU("Context Menu", "/context_menu"),
    DISAPPEARING_ELEMENTS("Disappearing Elements", "/disappearing_elements"),
    DRAG_AND_DROP("Drag and Drop", "/drag_and_drop"),
    DROPDOWN("Dropdown", "/dropdown"),
    DYNAMIC_CONTENT("Dynamic Content", "/dynamic_content"),
    DYNAMIC_CONTROLS("Dynamic Controls", "/dynamic_controls"),
    DYNAMIC_LOADING("Dynamic Loading", "/dynamic_loading"),
    ENTRY_AD("Entry Ad", "/entry_ad"),
    EXIT_INTENT("Exit Intent", "/exit_intent"),
    FILE_DOWNLOAD("File Download", "/download"),
    FILE_UPLOAD("File Upload", "/upload"),
    FLOATING_MENU("Floating Menu", "/floating_menu"),
    FORGOT_PASSWORD("Forgot Password", "/forgot_password"),
    FORM_AUTHENTICATION("Form Authentication", "/login"),
    FRAMES("Frames", "/frames"),
    GEOLOCATION("Geolocation", "/geolocation"),
    HORIZONTAL_SLIDER("Horizontal Slider", "/horizontal_slider"),
    HOVERS("Hovers", "/hovers"),
    INFINITE_SCROLL("Infinite Scroll", "/infinite_scroll"),
    INPUTS("Inputs", "/inputs"),
    JQUERY_UI_MENUS("JQuery UI Menus", "/jqueryui/menu"),
    JAVASCRIPT_ALERTS("JavaScript Alerts", "/javascript_alerts"),
    JAVASCRIPT_ONLOAD_EVENT_ERROR("JavaScript onload event error", "/javascript_error"),
    KEY_PRESSES("Key Presses", "/key_presses"),
    LARGE_DEEP_DOM("Large & Deep DOM", "/large"),
    MULTIPLE_WINDOWS("Multiple Windows", "/windows"),
    NESTED_FRAMES("Nested Frames", "/nested_frames"),
    NOTIFICATION_MESSAGES("Notification Messages", "/notification_message_rendered"),
    REDIRECT_LINK("Redirect Link", "/redirector"),
    SECURE_FILE_DOWNLOAD("Secure File Download", "/download_secure"),
    SHADOW_DOM("Shadow DOM", "/shadowdom"),
    SHIFTING_CONTENT("Shifting Content", "/shifting_content"),
    SLOW_RESOURCES("Slow Resources", "/slow"),
    SORTABLE_DATA_TABLES("Sortable Data Tables", "/tables"),
    STATUS_CODES("Status Codes", "/status_codes"),
    TYPOS("Typos", "/typos"),
    WYSIWYG_EDITOR("WYSIWYG Editor", "/tinymce");

    private final String name;
    private final String relativeUrl;

    SubPage(String name, String relativeUrl) {
        this.name = name;
        this.relativeUrl = relativeUrl;
    }

    public String getName() {
        return name;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    @Override
    public String toString() {
        return name; // Returns visible text associated to ENUM
    }
}
