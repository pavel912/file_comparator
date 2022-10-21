package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> parseData(String fileContent, String fileFormat) throws Exception {

        switch (fileFormat) {
            case "json" -> {
                ObjectMapper jsonMapper = new ObjectMapper();
                return jsonMapper.readValue(fileContent, Map.class);
            }
            case "yaml" -> {
                ObjectMapper yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(fileContent, Map.class);
            }
            default -> throw new Exception("Wrong file format. Please use only json and yaml files");
        }
    }
}
