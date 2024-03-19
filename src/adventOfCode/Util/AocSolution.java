package adventOfCode.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class AocSolution {
    private char[][] grid;
    private String fileName;

    public AocSolution(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private Object parseFile(ArrayList<String> rawLines) {
        // process raw data from rawLines
        // and generate certain data type for yourself to solve the problem
        int rows = rawLines.size();
        int cols = rawLines.get(0).length();

        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = rawLines.get(i);
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        return grid;

        // Example: return rawLines.get(0);
    }

    private String solve(final Object d) {
        // Example: final String data = (String) d;
        // Example: return String.valueOf(data.length());
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println(); // Move to the next line after printing each row
        }
        return null;
    }

    public Object generateGrid () {
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
        final Object data = parseFile(rawLines);
        return data;
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
        final Object data = parseFile(rawLines);
        double parsingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Parsing time for %s: %.3fms", this.fileName, parsingTime));
        start = System.nanoTime();
        final String result = solve(data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, result));
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
        String[] files = new String[] {"/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/day3/input.txt", "data.txt"};
        for (String filename : files) {
            final AocSolution aoc = new AocSolution(filename);
            aoc.execute();
        }
    }
}
