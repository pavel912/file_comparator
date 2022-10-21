package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class FormatterJson {
    public static String format(TreeMap<String, Map<String, Object>> keysParams)
            throws RuntimeException, IOException {

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


        mapper.writeValue(new File(Paths.get("").toAbsolutePath()
                + "/src/main/resources/output.json"), keysParamsNotSame);
        return mapper.writeValueAsString(keysParamsNotSame);
    }
}
