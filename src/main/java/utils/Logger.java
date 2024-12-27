package utils;

public class Logger {

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public static void info(String message) {
        System.out.println("[INFO]: " + message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to log.
     */
    public static void error(String message) {
        System.err.println("[ERROR]: " + message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to log.
     */
    public static void debug(String message) {
        if (Boolean.parseBoolean(ConfigReader.getProperty("debugMode"))) {
            System.out.println("[DEBUG]: " + message);
        }
    }

    /**
     * Logs a passing step.
     *
     * @param message The message to log.
     */
    public static void pass(String message) {
        System.out.println("[PASS]: " + message);
//        if (ExtentReportManager.getTest() != null) {
//            ExtentReportManager.getTest().log(Status.PASS, message);
//        }
    }

    /**
     * Logs a failing step.
     *
     * @param message The message to log.
     */
    public static void fail(String message) {
        System.out.println("[FAIL]: " + message);
//        if (ExtentReportManager.getTest() != null) {
//            ExtentReportManager.getTest().log(Status.FAIL, message);
//        }
    }
}
