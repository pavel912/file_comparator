package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    public static Map<String, Object> getData(String filepath) throws Exception {
        Path absFilepath = Paths.get(filepath).toAbsolutePath();
        String fileContent = Files.readString(absFilepath);

        if (absFilepath.toString().endsWith(".json")) {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.readValue(fileContent, Map.class);
        } else if (absFilepath.toString().endsWith(".yaml") || absFilepath.toString().endsWith(".yml")) {
            ObjectMapper yamlMapper = new YAMLMapper();
            return yamlMapper.readValue(fileContent, Map.class);
        }

        throw new Exception("Wrong file format. Please use only json and yaml files");
    }
}
