package advent_of_code_2015.Day3;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day3 {

    class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    private String fileName;

    public Day3(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static String parseFile(ArrayList<String> rawLines) {
        return rawLines.get(0);
    }

    public Map<Point, Integer> deliver(String data) {
        Map<Point, Integer> gifts = new HashMap<>();
        Point startPoint = new Point(0, 0);
        gifts.put(startPoint, 1);
        long curr_x = 0;
        long curr_y = 0;

        for (char ch : data.toCharArray()) {
            if (ch == '<') {
                curr_x -= 1;
            } else if (ch == '>') {
                curr_x += 1;
            } else if (ch == '^') {
                curr_y -= 1;
            } else {
                curr_y += 1;
            }
            Point currentPoint = new Point(curr_x, curr_y);
            gifts.put(currentPoint, gifts.getOrDefault(currentPoint, 0) + 1);
        }
        return gifts;
    }

    private Object[] solve(String data) {
        int size = deliver(data).size();

        Set<Point> combined = new HashSet<>(deliver(data.substring(0, data.length() / 2)).keySet());
        combined.addAll(deliver(data.substring(data.length() / 2)).keySet());
        return new Object[] {size, combined.size()};
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
        String[] files = new String[] {"src/advent_of_code_2015/Day3/input.txt"};
        for (String filename : files) {
            final Day3 day2 = new Day3(filename);
            day2.execute();
        }
    }
}