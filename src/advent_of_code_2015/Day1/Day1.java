package advent_of_code_2015.Day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    private String fileName;

    public Day1(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static String parseFile(ArrayList<String> rawLines) {
        return rawLines.get(0);
    }

    private Object[] solve(String data) {
        char[] parentheses = data.toCharArray();
        long left = 0;
        long right = 0;
        for (var c : parentheses) {
            if (c == '(') {
                left++;
            } else {
                right++;
            }
        }

        long number = 0;
        long index = 0;
        for (var c : parentheses) {
            if (c == '(') {
                number++;
            } else {
                number--;
            }
            index++;
            if (number == -1) {
                break;
            }
        }
        return new Object[] {left - right, index};
    }

    public void execute() {
        ArrayList<String> rawLines = new ArrayList<>();
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
        final String data = parseFile(rawLines);
        double parsingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Parsing time for %s: %.3fms", this.fileName, parsingTime));
        start = System.nanoTime();
        final Object[] result = solve(data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 1:" + (long) result[0]));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 2: " + (long) result[1]));
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
        String[] files = new String[] {"src/advent_of_code_2015/Day1/sample.txt"};
        for (String filename : files) {
            final Day1 day1 = new Day1(filename);
            day1.execute();
        }
    }
}