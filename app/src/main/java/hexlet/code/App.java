package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "Path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "Path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format")
    private String format = "stylish";

    @Override
    public Integer call() throws java.io.IOException {
        Map<String, Object> mappedContent1 = DataGetter.getData(filepath1);
        Map<String, Object> mappedContent2 = DataGetter.getData(filepath2);

        System.out.println(Differ.generate(mappedContent1, mappedContent2));

        return 0;
    }

    public static void main(String[] args) {
        CommandLine cli = new CommandLine(new App());
        int exitCode = cli.execute(args);
        System.exit(exitCode);
    }
}
