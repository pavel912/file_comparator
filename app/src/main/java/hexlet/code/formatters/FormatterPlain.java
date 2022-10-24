package hexlet.code.formatters;

import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

public class FormatterPlain {

    public static String format(TreeMap<String, Map<String, Object>> keysParams) throws RuntimeException {
        StringJoiner sj = new StringJoiner("\n");

        keysParams
                .navigableKeySet()
                .stream()
                .filter(key -> !keysParams
                        .get(key)
                        .get("action")
                        .equals("same"))
                .forEach(key -> {
                    try {
                        sj.add(formatAction(key, keysParams.get(key)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return sj.toString();
    }
    private static String formatAction(String key, Map<String, Object> params) throws Exception {
        String action = params.get("action").toString();

        return switch (action) {
            case "add" -> String.format("Property '%s' was added with value: %s",
                    key,
                    transformValue(params.get("value")));
            case "remove" -> String.format("Property '%s' was removed", key);
            case "same" -> null;
            case "replace" -> String.format("Property '%s' was updated. From %s to %s",
                    key, transformValue(params.get("old_value")), transformValue(params.get("new_value")));
            default -> throw new Exception("Unknown action to format");
        };
    }

    private static boolean isComplexValue(Object value) {
        return value.toString().startsWith("{") & value.toString().endsWith("}")
                | value.toString().startsWith("[") & value.toString().endsWith("]");
    }

    private static String transformValue(Object value) {
        if (value == null) {
            return "null";
        }

        if (isComplexValue(value)) {
            return "[complex value]";
        }

        if (value.toString().equals("true") | value.toString().equals("false")) {
            return value.toString();
        }

        try {
            double number = Double.parseDouble(value.toString());
            return value.toString();
        } catch (java.lang.NumberFormatException e) {
            return String.format("'%s'", value);
        }
    }
}
