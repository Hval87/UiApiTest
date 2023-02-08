package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

@Log4j
public class DataManager{

    private static final String CONFIG_SRC = "/ConfigData.json";
    private final static String CRED_SRC = "/Credentials.json";
    private final static String TEST_SOURCE = "/TestData.json";
    private static JSONParser parser;
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static <T> T getTestValue(String key) {
        parser = new JSONParser();
        JSONObject data = null;
        try {
            data = (JSONObject) parser.parse(new FileReader(FileProvider.getTestPath(TEST_SOURCE)));
        } catch (Exception e) {
            log.error("parsing error", e);
        }
        parser=null;
        return (T) data.get(key);
    }

    public static <T> T getConfigValue(String key) {
        parser = new JSONParser();
        JSONObject data = null;
        try {
            data = (JSONObject) parser.parse(new FileReader(FileProvider.getTestPath(CONFIG_SRC)));
        } catch (Exception e) {
            log.error("parsing error", e);
        }
        return (T) data.get(key);
    }

    public static <T> T getCredentialsValue(String key) {
        parser = new JSONParser();
        JSONObject data = null;
        try {
            data = (JSONObject) parser.parse(new FileReader(FileProvider.getTestPath(CRED_SRC)));
        } catch (Exception e) {
            log.error("parsing error", e);
        }
        return (T) data.get(key);
    }
}