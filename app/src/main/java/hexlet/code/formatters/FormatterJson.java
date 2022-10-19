package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public class FormatterJson {
    public static String format(TreeMap<String, Map<String, Object>> keysParams)
            throws RuntimeException, JsonProcessingException {

        TreeMap<String, Map<String, Object>> keysParamsNotSame = new TreeMap<>();

        keysParams
                .navigableKeySet()
                .stream()
                .filter(key -> !keysParams
                        .get(key)
                        .get("action")
                        .equals("same"))
                .forEach(key -> keysParamsNotSame.put(key, keysParams.get(key)));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(keysParamsNotSame);
    }
}
