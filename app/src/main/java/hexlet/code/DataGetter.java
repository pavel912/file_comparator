package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataGetter {
    public static Map<String, String> getData(String filepath) throws Exception {
        Path absFilepath = Paths.get(filepath).toAbsolutePath();
        String fileFormat;

        if (absFilepath.toString().endsWith("json")) {
            fileFormat = "json";
        } else if (absFilepath.toString().endsWith("yaml") || absFilepath.toString().endsWith("yml")) {
            fileFormat = "yaml";
        } else {
            fileFormat = null;
        }

        // files have \r in each string for some reason
        String fileContent = Files.readString(absFilepath).replaceAll("\r", "");

        return new HashMap<>() {{
                put("content", fileContent);
                put("format", fileFormat);
            }
        };
    }
}
