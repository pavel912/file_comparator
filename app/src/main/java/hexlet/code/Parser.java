package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> parseData(String fileContent, String fileFormat) throws Exception {

        switch (fileFormat) {
            case "json" -> {
                ObjectMapper jsonMapper = new ObjectMapper();
                return jsonMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
            }
            case "yaml" -> {
                ObjectMapper yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
            }
            default -> throw new Exception("Wrong file format. Please use only json and yaml files");
        }
    }
}
