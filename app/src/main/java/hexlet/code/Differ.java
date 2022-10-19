package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(Map<String, Object> content1,
                                  Map<String, Object> content2, String format) throws Exception {
        TreeMap<String, Map<String, Object>> keyDiff = new TreeMap<>();

        List<String> allKeysList = getAllKeysSortedList(content1.keySet(), content2.keySet());

        allKeysList.forEach(x -> keyDiff.put(x, generateDiff(content1, content2, x)));

        return Formatter.format(keyDiff, format);
    }

    public static String generate(Map<String, Object> content1,
                                  Map<String, Object> content2) throws Exception {
        return generate(content1, content2, "stylish");
    }

    private static Map<String, Object> generateDiff(Map<String, Object> content1, Map<String, Object> content2,
                                                    String key) {
        Object value1 = content1.get(key);
        Object value2 = content2.get(key);

        Map<String, Object> actions = new HashMap<>();

        if (!content1.containsKey(key)) {
            return new HashMap<>() { { put("action", "add"); put("value", value2); } };
        }

        if (!content2.containsKey(key)) {
            return new HashMap<>() { { put("action", "remove"); put("value", value1); } };
        }

        if (value1 == null & value2 == null) {
            return new HashMap<>() { { put("action", "add"); put("value", "null"); } };
        }

        if (value1 != null) {
            if (value1.equals(value2)) {
                return new HashMap<>() { { put("action", "same"); put("value", value1); } };
            }
        }

        return new HashMap<>() { { put("action", "replace"); put("old_value", value1); put("new_value", value2); } };
    }

    public static List<String> getAllKeysSortedList(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new HashSet<>(keys1);
        allKeys.addAll(keys2);

        return new ArrayList<>(allKeys).stream().sorted().toList();
    }
}
