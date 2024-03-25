package javacat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Javacat {
    public static void main(String[] args) throws FileNotFoundException {
        String file = args[0];
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

        scanner.close();
    }
}