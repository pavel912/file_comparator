package hexlet.code;

public class Formatter {
    private static String formatStylish(String action, String key, Object value1, Object value2) throws Exception {
        return switch (action) {
            case "add" -> formatLineStylish("+", key, value2);
            case "remove" -> formatLineStylish("-", key, value1);
            case "same" -> formatLineStylish(" ", key, value2);
            case "replace" -> formatLineStylish("-", key, value1) + "\n"
                    + formatLineStylish("+", key, value2);
            default -> throw new Exception("Unknown action to format");
        };
    }

    private static String formatLineStylish(String operator, String key, Object value) {
        return String.format("  %s %s: %s", operator, key, value);
    }

    public static String format(Object[] params, String format) throws Exception {
        if (format.equals("stylish")) {
            return formatStylish(params[0].toString(), params[1].toString(), params[2], params[3]);
        } else {
            throw new Exception("Unknown format for formatting");
        }
    }

    public static String format(Object[] params) throws Exception {
        return format(params, "stylish");
    }
}
