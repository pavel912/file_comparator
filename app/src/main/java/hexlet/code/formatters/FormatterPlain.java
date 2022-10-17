package hexlet.code.formatters;

import java.util.StringJoiner;
import java.util.TreeMap;

public class FormatterPlain {

    public static String format(TreeMap<String, Object[]> keysParams) throws RuntimeException {
        StringJoiner sj = new StringJoiner("\n", "", "\n");

        keysParams.navigableKeySet().stream().filter(key -> !keysParams.get(key)[0].equals("same")).forEach(key -> {
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
            case "add" -> String.format("Property '%s' was added with value: %s", key, transformValue(value2));
            case "remove" -> String.format("Property '%s' was removed", key);
            case "same" -> null;
            case "replace" -> String.format("Property '%s' was updated. From %s to %s",
                    key, transformValue(value1), transformValue(value2));
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
