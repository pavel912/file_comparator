package hexlet.code.formatters;

import java.util.StringJoiner;
import java.util.TreeMap;

public class FormatterStylish {
    public static String format(TreeMap<String, Object[]> keysParams) throws RuntimeException {
        StringJoiner sj = new StringJoiner("\n", "{\n", "\n}");

        keysParams.navigableKeySet().forEach(key -> {
            try {
                sj.add(formatAction(keysParams.get(key)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return sj.toString();
    }

    private static String formatAction(Object[] params) throws Exception {
        String action = params[0].toString();
        String key = params[1].toString();
        Object value1 = params[2];
        Object value2 = params[3];

        return switch (action) {
            case "add" -> formatLine("+", key, value2);
            case "remove" -> formatLine("-", key, value1);
            case "same" -> formatLine(" ", key, value2);
            case "replace" -> formatLine("-", key, value1) + "\n"
                    + formatLine("+", key, value2);
            default -> throw new Exception("Unknown action to format");
        };
    }

    private static String formatLine(String operator, String key, Object value) {
        return String.format("  %s %s: %s", operator, key, value);
    }
}
