package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, String> file1Data = DataGetter.getData(filepath1);
        Map<String, String> file2Data = DataGetter.getData(filepath2);

        Map<String, Object> content1 = Parser.parseData(file1Data.get("content"), file1Data.get("format"));
        Map<String, Object> content2 = Parser.parseData(file2Data.get("content"), file2Data.get("format"));

        TreeMap<String, Map<String, Object>> keyDiff = DiffBuilder.buildDifference(content1, content2);

        return Formatter.format(keyDiff, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
