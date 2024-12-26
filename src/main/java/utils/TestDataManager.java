package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class TestDataManager {

    private static final Map<String, Object> data;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(new File("src/main/resources/TestData.json"), Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from JSON", e);
        }
    }

    /**
     * Get a value by key from the JSON data.
     *
     * @param key The key to retrieve.
     * @return The value associated with the key.
     */
    public static Object get(String key) {
        return data.get(key);
    }

    /**
     * Get a subsection of the JSON data as a Map.
     *
     * @param section The section to retrieve.
     * @return A Map representing the subsection.
     */
    public static Map<String, Object> getSection(String section) {
        return (Map<String, Object>) data.get(section);
    }
}
