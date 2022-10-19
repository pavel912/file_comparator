package hexlet.code.formatters;

import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

public class FormatterStylish {
    public static String format(TreeMap<String, Map<String, Object>> keysParams) throws RuntimeException {
        StringJoiner sj = new StringJoiner("\n", "{\n", "\n}");

        keysParams.navigableKeySet().forEach(key -> {
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
            case "add" -> formatLine("+", key, params.get("value"));
            case "remove" -> formatLine("-", key, params.get("value"));
            case "same" -> formatLine(" ", key, params.get("value"));
            case "replace" -> formatLine("-", key, params.get("old_value")) + "\n"
                    + formatLine("+", key, params.get("new_value"));
            default -> throw new Exception("Unknown action to format");
        };
    }

    private static String formatLine(String operator, String key, Object value) {
        return String.format("  %s %s: %s", operator, key, value);
    }
}
