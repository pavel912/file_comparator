package hexlet.code;

import java.util.Map;
import java.util.StringJoiner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Differ {
    public static String generate(Map<String, Object> content1,
                                  Map<String, Object> content2, String format) throws Exception {
        StringJoiner sj = new StringJoiner("\n", "{\n", "\n}");

        List<String> allKeysList = getAllKeysSortedList(content1.keySet(), content2.keySet());

        for (String key : allKeysList) {
            sj.add(
                    Formatter.format(generateDiff(
                            content1,
                            content2,
                            key), format));
        }

        return sj.toString();
    }

    public static String generate(Map<String, Object> content1,
                                  Map<String, Object> content2) throws Exception {
        return generate(content1, content2, "stylish");
    }

    public static Object[] generateDiff(Map<String, Object> content1, Map<String, Object> content2, String key) {
        Object value1 = content1.get(key);
        Object value2 = content2.get(key);

        if (!content1.containsKey(key)) {
            return new Object[]{"add", key, null, value2};
        }

        if (!content2.containsKey(key)) {
            return new Object[]{"remove", key, value1, null};
        }

        if (value1 == null & value2 == null) {
            return new Object[]{"same", key, null, null};
        }

        if (value1 != null) {
            if (value1.equals(value2)) {
                return new Object[]{"same", key, value1, value2};
            }
        }

        return new Object[]{"replace", key, value1, value2};
    }

    public static List<String> getAllKeysSortedList(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new HashSet<>(keys1);
        allKeys.addAll(keys2);

        return new ArrayList<>(allKeys).stream().sorted().toList();
    }
}
