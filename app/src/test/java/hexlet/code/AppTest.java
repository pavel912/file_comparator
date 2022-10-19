package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    static List<String> filesJson = new ArrayList<>();
    static List<String> filesYaml = new ArrayList<>();

    @BeforeAll
    public static void loadData() throws Exception {
        String jsonDir = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles";
        String yamlDir = Paths.get("").toAbsolutePath() + "/src/test/resources/yamlFiles";

        filesJson.add(jsonDir + "/file1.json");
        filesJson.add(jsonDir + "/file2.json");
        filesJson.add(jsonDir + "/file3.json");
        filesJson.add(jsonDir + "/file4.json");
        filesJson.add(jsonDir + "/fileNested1.json");
        filesJson.add(jsonDir + "/fileNested2.json");

        filesYaml.add(yamlDir + "/file1.yaml");
        filesYaml.add(yamlDir + "/file2.yaml");
        filesYaml.add(yamlDir + "/file3.yaml");
        filesYaml.add(yamlDir + "/file4.yaml");
        filesYaml.add(yamlDir + "/fileNested1.yaml");
        filesYaml.add(yamlDir + "/fileNested2.yaml");
    }

    @Test
    public void testFlatJson() throws Exception {
        String actual1 = Differ.generate(filesJson.get(0), filesJson.get(1));
        String expected1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(expected1, actual1);

        // no matching keys
        String actual2 = Differ.generate(filesJson.get(0), filesJson.get(2));
        String expected2 = """
                {
                  + based: hexlet.io
                  + child: false
                  + cringe: 50
                  - follow: false
                  - host: hexlet.io
                  + parent: 123.234.53.22
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";

        assertEquals(expected2, actual2);

        //datatype change
        String actual3 = Differ.generate(filesJson.get(2), filesJson.get(3));
        String expected3 = """
                {
                  - based: hexlet.io
                  + based: yes
                  - child: false
                  + child: yes
                  - cringe: 50
                  + cringe: -50
                  - parent: 123.234.53.22
                  + parent: no
                }""";

        assertEquals(expected3, actual3);
    }

    @Test
    public void testFlatYaml() throws Exception {
        String actual1 = Differ.generate(filesYaml.get(0), filesYaml.get(1));
        String expected1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(expected1, actual1);

        // no matching keys
        String actual2 = Differ.generate(filesYaml.get(0), filesYaml.get(2));
        String expected2 = """
                {
                  + based: hexlet.io
                  + child: false
                  + cringe: 50
                  - follow: false
                  - host: hexlet.io
                  + parent: 123.234.53.22
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";

        assertEquals(expected2, actual2);

        //datatype change
        String actual3 = Differ.generate(filesYaml.get(2), filesYaml.get(3));
        String expected3 = """
                {
                  - based: hexlet.io
                  + based: yess
                  - child: false
                  + child: yess
                  - cringe: 50
                  + cringe: -50
                  - parent: 123.234.53.22
                  + parent: noo
                }""";

        assertEquals(expected3, actual3);
    }

    @Test
    public void testNested() throws Exception {
        String actualJson = Differ.generate(filesJson.get(4), filesJson.get(5));
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

        String actualYaml = Differ.generate(filesYaml.get(4), filesYaml.get(5));

        assertEquals(expected, actualYaml);
    }

    @Test
    public void testPlainFormat() throws Exception {
        String actual = Differ.generate(filesJson.get(4), filesJson.get(5), "plain");
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
                Property 'setting3' was updated. From true to 'none'
                """;

        assertEquals(expected, actual);

    }

    @Test
    public void testFormatJson() throws Exception {
        String actual = Differ.generate(filesJson.get(0), filesJson.get(1), "json");

        String expected = "{\"follow\":{\"action\":\"remove\",\"value\":false},"
                + "\"proxy\":{\"action\":\"remove\",\"value\":\"123.234.53.22\"},"
                + "\"timeout\":{\"action\":\"replace\",\"old_value\":50,\"new_value\":20},"
                + "\"verbose\":{\"action\":\"add\",\"value\":true}}";

        assertEquals(expected, actual);
    }
}
