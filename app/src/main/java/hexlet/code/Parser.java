package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> parseData(String content, String contentFormat) throws Exception {

        switch (contentFormat) {
            case "json" -> {
                ObjectMapper jsonMapper = new ObjectMapper();
                return jsonMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            }
            case "yaml" -> {
                ObjectMapper yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            }
            default -> throw new Exception("Wrong file format. Please use only json and yaml files");
        }
    }
}
