package hexlet.code;

import hexlet.code.formatters.FormatterJson;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.Map;
import java.util.TreeMap;

public class Formatter {
    public static String format(TreeMap<String, Map<String, Object>> keysParams, String format) throws Exception {
        if (format.equals("stylish")) {
            return FormatterStylish.format(keysParams);
        } else if (format.equals("plain")) {
            return FormatterPlain.format(keysParams);
        } else if (format.equals("json")) {
            return FormatterJson.format(keysParams);
        } else {
            throw new Exception("Unknown format for formatting");
        }
    }
}
