package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> content1 = Parser.parseData(getData(filepath1), getFileFormat(filepath1));
        Map<String, Object> content2 = Parser.parseData(getData(filepath2), getFileFormat(filepath1));

        TreeMap<String, Map<String, Object>> keyDiff = DiffBuilder.buildDifference(content1, content2);

        if (format.equals("plain") || format.equals("json")) {
            List<String> keysSame = keyDiff
                    .navigableKeySet()
                    .stream()
                    .filter(key -> keyDiff
                            .get(key)
                            .get("action")
                            .equals("same")).toList();

            keyDiff.keySet().removeAll(keysSame);
        }

        return Formatter.format(keyDiff, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getData(String filepath) throws Exception {
        Path absFilepath = Paths.get(filepath).toAbsolutePath();

        // files have \r in each string for some reason
        return Files.readString(absFilepath).replaceAll("\r", "");
    }

    private static String getFileFormat(String filepath) {
        Path absFilepath = Paths.get(filepath).toAbsolutePath();
        String fileExtension;

        if (absFilepath.toString().endsWith("json")) {
            return "json";
        } else if (absFilepath.toString().endsWith("yaml") || absFilepath.toString().endsWith("yml")) {
            return "yaml";
        } else {
            return "other";
        }
    }
}
