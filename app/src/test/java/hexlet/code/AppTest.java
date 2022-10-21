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
        String expected = DataGetter
                .getData("src/test/resources/expectedOutput/expectedStylish")
                .get("content");

        assertEquals(expected, actualJson);

        String actualYaml = Differ.generate(yamlFile1, yamlFile2);

        assertEquals(expected, actualYaml);
    }

    @Test
    public void testPlainFormat() throws Exception {
        String jsonFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested1.json";
        String jsonFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/fileNested2.json";

        String actual = Differ.generate(jsonFile1, jsonFile2, "plain");
        String expected = DataGetter
                .getData("src/test/resources/expectedOutput/expectedPlain")
                .get("content");

        assertEquals(expected, actual);

    }

    @Test
    public void testFormatJson() throws Exception {
        String jsonFile1 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/file1.json";
        String jsonFile2 = Paths.get("").toAbsolutePath() + "/src/test/resources/jsonFiles/file2.json";

        String actual = Differ.generate(jsonFile1, jsonFile2, "json");

        String expected = DataGetter
                .getData("src/test/resources/expectedOutput/expectedJson.json")
                .get("content");

        assertEquals(expected, actual);
    }
}
