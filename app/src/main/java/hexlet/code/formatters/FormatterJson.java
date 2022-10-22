package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FormatterJson {
    public static String format(TreeMap<String, Map<String, Object>> keysParams)
            throws RuntimeException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(keysParams);
    }
}
