package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    public void testNested() throws Exception {
        String jsonFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested1.json";
        String jsonFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested2.json";

        String yamlFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/yamlFiles/fileNested1.yaml";
        String yamlFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/yamlFiles/fileNested2.yaml";

        String actualJson = Differ.generate(jsonFile1, jsonFile2);
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        assertEquals(expected, actualJson);

        String actualYaml = Differ.generate(yamlFile1, yamlFile2);

        assertEquals(expected, actualYaml);
    }

    @Test
    public void testPlainFormat() throws Exception {
        String jsonFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested1.json";
        String jsonFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested2.json";

        String actual = Differ.generate(jsonFile1, jsonFile2, "plain");
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        assertEquals(expected, actual);

    }

    @Test
    public void testFormatJson() throws Exception {
        String jsonFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/file1.json";
        String jsonFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/file2.json";

        String actual = Differ.generate(jsonFile1, jsonFile2, "json");

        String expected = "{\"follow\":{\"action\":\"remove\",\"value\":false},"
                + "\"proxy\":{\"action\":\"remove\",\"value\":\"123.234.53.22\"},"
                + "\"timeout\":{\"action\":\"replace\",\"old_value\":50,\"new_value\":20},"
                + "\"verbose\":{\"action\":\"add\",\"value\":true}}";

        assertEquals(expected, actual);
    }
}
