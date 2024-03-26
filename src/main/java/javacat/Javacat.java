package javacat;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Javacat {
    public static void main(String[] args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        Set<String> catOptions = new HashSet<>();
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("n")) {
                catOptions.add("n");
            }
            if (cmd.hasOption("b")) {
                catOptions.add("b");
                catOptions.remove("n");
            }
            cat(args, catOptions);
        } catch (ParseException ex) {
            ex.getMessage();
        }
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("A", "show-all", false, "equivalent to -vET");
        options.addOption("b", "number-nonblank", false, "number nonempty output lines, overrides -n");
        options.addOption("e", false, "equivalent to -vE");
        options.addOption("E", "show-ends", false, "display $ at end of each line");
        options.addOption("n", "number",false, "number all output lines");
        options.addOption("s", "squeeze-blank", false, "suppress repeated empty output lines");
        options.addOption("t", false, "equivalent to -vT");
        options.addOption("T", "show-tabs", false, "display TAB characters as ^I");
        options.addOption("v", "show-nonprinting", false, "use ^ and M- notation, except for LFD and TAB");
        return options;
    }

    private static void cat(String[] args, Set<String> catOptions) {
        String file = catOptions.isEmpty() ? args[0] : args[1];
        try(Scanner scanner = new Scanner(new File(file))) {
            int lineNumber = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (catOptions.contains("n")) {
                    System.out.print(lineNumber);
                    if (!line.isBlank()) {
                        System.out.print(" ");
                    }
                }
                System.out.println(line);
                lineNumber++;
            }
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
    }
}