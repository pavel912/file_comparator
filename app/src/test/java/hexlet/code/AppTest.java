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

        filesYaml.add(Parser.getData(yamlDir + "/file1.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file2.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file3.yaml"));
        filesYaml.add(Parser.getData(yamlDir + "/file4.yaml"));
    }

    @Test
    public void testFlatJson() {
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
    public void testFlatYaml() {
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
}
