package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataGetter {
    public static Map<String, Object> getData(String filepath) throws IOException {
        String fileContent = Files.readString(Paths.get(filepath));
        if (Paths.get(filepath).endsWith(".json")) {
        	ObjectMapper jsonMapper = new ObjectMapper();
        	return jsonMapper.readValue(fileContent, Map.class);
        } else if (Paths.get(filepath).endsWith(".yaml")) {
        	ObjectMapper yamlMapper = new YAMLMapper();
        	return yamlMapper.readValue(fileContent, Map.class);
        }

        throw new Exception("Wrong file format. Please use only json and yaml files");
    }
}
