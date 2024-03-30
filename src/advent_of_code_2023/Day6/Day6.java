package advent_of_code_2023.Day6;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day6 {

    private String fileName;

    public Day6(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static Object[] parseFile(ArrayList<String> rawLines) {
        List<int[]> races = new ArrayList<>();
        int[] times = parseLine(rawLines.get(0));
        int[] distances = parseLine(rawLines.get(1));
        for (int i = 0; i < times.length && i < distances.length; i++) {
            System.out.println("races:" + times[i] + "," + distances[i]);
            int[] race = {times[i], distances[i]};
            races.add(race);
        }
        List<long[]> races2 = new ArrayList<>();
        long times2 = parseLine2(rawLines.get(0));
        long distance2 = parseLine2(rawLines.get(1));
        long[] race2 = {times2, distance2};
        races2.add(race2);
        return new Object[] {races, races2};
    }

    private static Long parseLine2(String rawLine) {
        String[] parts = rawLine.trim().split(":");
        return Long.parseLong(String.valueOf(parts[1].replaceAll(" ", "")));
    }

    private static int[] parseLine(String rawLine) {
        String[] parts = rawLine.trim().split("\\s+");
        int[] values = new int[parts.length - 1];
        for (int i = 1; i < parts.length; i++) {
            values[i - 1] = Integer.parseInt(parts[i]);
        }
        return values;
    }

    private Object[] solve(Object[] data) {
        List<int[]> data1 = (List<int[]>) data[0];
        List<long[]> data2 = (List<long[]>) data[1];

        int total = 1;
        for (int[] race : data1) {
            int time = race[0];
            int distance = race[1];
            int curPossible = 0;
            for (int i = 1; i <= time; i++) {
                int maxDistance = (time - i) * i;
                if (maxDistance > distance) {
                    curPossible++;
                }
            }
            total *= curPossible;
        }

        long total2 = 1;
        for (long[] race : data2) {
            long time = race[0];
            long distance = race[1];
            long curPossible = 0;
            for (long i = 1; i <= time; i++) {
                long maxDistance = (time - i) * i;
                if (maxDistance > distance) {
                    curPossible++;
                }
            }
            total2 = curPossible;
        }
        return new Object[] {total, total2};
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
        final Object[] data = parseFile(rawLines);
        double parsingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Parsing time for %s: %.3fms", this.fileName, parsingTime));
        start = System.nanoTime();
        final Object[] result = solve(data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 1:" + (int) result[0]));
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
        String[] files = new String[] {"src/advent_of_code_2023/Day6/data.txt", "src/advent_of_code_2023/Day6/input.txt"};
        for (String filename : files) {
            final Day6 day6 = new Day6(filename);
            day6.execute();
        }
    }
}
