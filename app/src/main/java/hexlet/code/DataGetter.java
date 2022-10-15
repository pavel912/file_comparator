package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class DataGetter {
    public static Map<String, Object> getData(String filepath) throws IOException {
        String fileContent = Files.readString(Paths.get(filepath));

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(fileContent, Map.class);
    }
}
