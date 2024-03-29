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

        try {
            cmd = parser.parse(options, args);
            cat(cmd);
        } catch (ParseException ex) {
            ex.getMessage();
        }
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("b", "number-nonblank", false, "number nonempty output lines, overrides -n");
        options.addOption("E", "show-ends", false, "display $ at end of each line");
        options.addOption("n", "number",false, "number all output lines");
        options.addOption("s", "squeeze-blank", false, "suppress repeated empty output lines");
        return options;
    }

    private static void cat(CommandLine cmd) {
        String[] files = cmd.getArgs();

        int lineNumber = 1;
        for (String file: files) {
            try (Scanner scanner = new Scanner(new File(file))) {
                String prevLine = "prevline";
                String currLine;

                while (scanner.hasNext()) {
                    currLine = scanner.nextLine();
                    if (cmd.hasOption("s") && currLine.isBlank() && prevLine.isBlank()) {
                        continue;
                    }
                    if ((cmd.hasOption("b") && !currLine.isBlank()) || cmd.hasOption("n")) {
                        System.out.print(lineNumber);
                        if (!currLine.isBlank()) {
                            System.out.print(" ");
                        }
                        lineNumber++;
                    }
                    System.out.println(cmd.hasOption("E") ? currLine + "$" : currLine);
                    prevLine = currLine;
                }
            } catch (FileNotFoundException ex) {
                ex.getMessage();
            }
        }
    }
}