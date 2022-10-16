package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    static List<Map<String, Object>> filesJson = new ArrayList<>();
    static List<Map<String, Object>> filesYaml = new ArrayList<>();

    @BeforeAll
    public static void loadData() throws Exception {
        String jsonDir = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles";
        String yamlDir = Paths.get("").toAbsolutePath() + "/src/test/resources/yamlFiles";

        filesJson.add(Parser.getData(jsonDir + "/file1.json"));
        filesJson.add(Parser.getData(jsonDir + "/file2.json"));
        filesJson.add(Parser.getData(jsonDir + "/file3.json"));
        filesJson.add(Parser.getData(jsonDir + "/file4.json"));
        filesJson.add(Parser.getData(jsonDir + "/fileNested1.json"));
        filesJson.add(Parser.getData(jsonDir + "/fileNested2.json"));

        filesYaml.add(Parser.getData(yamlDir + "/file1.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file2.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file3.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file4.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/fileNested1.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/fileNested2.yaml"));
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
}
