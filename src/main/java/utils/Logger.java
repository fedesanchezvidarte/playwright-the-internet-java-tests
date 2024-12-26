package utils;

public class Logger {

    public static void info(String message) {
        System.out.println("[INFO]: " + message);
    }

    public static void error(String message) {
        System.err.println("[ERROR]: " + message);
    }

    public static void debug(String message) {
        if (Boolean.parseBoolean(ConfigReader.getProperty("debugMode"))) {
            System.out.println("[DEBUG]: " + message);
        }
    }
}