package advent_of_code_2015.Day8;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {

    private String fileName;

    public Day8(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }


    private static List<Object> parseFile(List<String> rawLines) {
        List<Object> L = new ArrayList<>();
        return L;
    }

    private Object[] solve(List<Object> data) {
        return null;
    }

    public void execute() {
        List<String> rawLines = new ArrayList<>();
        try {
            final File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                rawLines.add(line);
            }
        } catch (Exception e) {
            // ignore
        }
        long start = System.nanoTime();
        final List<Object> data = parseFile(rawLines);
        double parsingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Parsing time for %s: %.3fms", this.fileName, parsingTime));
        start = System.nanoTime();
        final Object[] result = solve(data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 1:" + result[0]));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 2: " + result[1]));
    }

    /**
     * Create a file if it does not exist.
     */
    private void createFileIfNotExist(final String fileName) {
        final File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            // ignore
        }
    }

    public static void main(String[] args) {
        String[] files = new String[] {"src/advent_of_code_2015/Day8/sample.txt", "src/advent_of_code_2015/Day8/input.txt"};
        for (String filename : files) {
            final advent_of_code_2015.Day6.Day6 day6 = new advent_of_code_2015.Day6.Day6(filename);
            day6.execute();
        }
    }
}