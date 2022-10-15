package hexlet.code;

import java.util.Map;
import java.util.StringJoiner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Differ {
    public static String generate(Map<String, Object> content1, Map<String, Object> content2) {
        StringJoiner sj = new StringJoiner("\n", "{\n", "\n}");

        List<String> allKeysList = getAllKeysSortedList(content1.keySet(), content2.keySet());

        for (String key : allKeysList) {
            sj.add(
                    generateDiffString(
                            content1.containsKey(key),
                            content2.containsKey(key),
                            key,
                            content1.get(key),
                            content2.get(key)));
        }

        return sj.toString();
    }

    public static String generateDiffString(boolean isInFirstFile, boolean isInSecondFile,
                                            String key, Object value1, Object value2) {
        if (!isInFirstFile) {
            return "  + " + key + ": " + value2;
        }

        if (!isInSecondFile) {
            return "  - " + key + ": " + value1;
        }

        if (value1.equals(value2)) {
            return "    " + key + ": " + value1;
        }

        return "  - " + key + ": " + value1 + "\n  + " + key + ": " + value2;
    }

    public static List<String> getAllKeysSortedList(Set<String> keys1, Set<String> keys2) {
        Set<String> allKeys = new HashSet<>(keys1);
        allKeys.addAll(keys2);

        return new ArrayList<>(allKeys).stream().sorted().toList();
    }
}
