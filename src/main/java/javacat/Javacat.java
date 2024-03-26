package javacat;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Javacat {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("n", "number",false, "number all output lines");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        Set<String> catOptions = new HashSet<>();
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("n")) {
                catOptions.add("number");
            }
            cat(args, catOptions);
        } catch (ParseException ex) {
            ex.getMessage();
        }
    }

    private static void cat(String[] args, Set<String> catOptions) {
        String file = catOptions.isEmpty() ? args[0] : args[1];
        try(Scanner scanner = new Scanner(new File(file))) {
            int lineNumber = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (catOptions.contains("number")) {
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