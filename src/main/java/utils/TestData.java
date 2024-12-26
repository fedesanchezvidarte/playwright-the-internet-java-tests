package utils;

import java.util.Map;

public class TestData {

    private static final Map<String, String> data = Map.of(
            "username", "admin",
            "password", "admin",
            "checkboxDefaultState", "unchecked"
    );

    public static String get(String key) {
        return data.get(key);
    }
}
