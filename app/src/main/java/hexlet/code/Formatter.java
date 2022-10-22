package hexlet.code;

import hexlet.code.formatters.FormatterJson;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.Map;
import java.util.TreeMap;

public class Formatter {
    public static String format(TreeMap<String, Map<String, Object>> keysParams, String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                return FormatterStylish.format(keysParams);
            }
            case "plain" -> {
                return FormatterPlain.format(keysParams);
            }
            case "json" -> {
                return FormatterJson.format(keysParams);
            }
            default -> throw new Exception("Unknown format for formatting");
        }
    }
}
