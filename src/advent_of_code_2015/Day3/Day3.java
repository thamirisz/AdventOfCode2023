package advent_of_code_2015.Day3;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day3 {

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //will need equals and hashCode
        //traditional equals is comparing object references
        //for hashmap and hashset we need to implement our own equals method to compare based on x and y coordinates
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        //hashCode(): compute the hash code of a Point object.
        //Hash codes are used by hash-based collections for hashmap or hashset to efficiently organize and retrieve objects.
        //When you store a custom object as a key in a HashMap or add it to a HashSet
        // Java uses the object's hash code to determine
        // where to place the object in memory or which bucket to use for storage and retrieval.
        // Therefore, it's essential that the hashCode method be consistent with the equals method.
        // If two objects are equal according to the equals method,
        // they must produce the same hash code.
        // In the overridden hashCode method,
        // we typically combine the hash codes of the object's fields,
        // in this case, x and y,
        // using a suitable hashing algorithm to ensure proper distribution of hash codes across the hash table.
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    private String fileName;

    Map<Point, Integer> gifts = new HashMap<>();

    public Day3(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static String parseFile(ArrayList<String> rawLines) {
        return rawLines.get(0);
    }

    public Map<Point, Integer> deliver(String data) {
        Point startPoint = new Point(0, 0);
        gifts.put(startPoint, 1);
        int curX = 0;
        int curY = 0;

        for (char ch : data.toCharArray()) {
            if (ch == '<') {
                curX -= 1;
            } else if (ch == '>') {
                curX += 1;
            } else if (ch == '^') {
                curY -= 1;
            } else {
                curY += 1;
            }
            Point currentPoint = new Point(curX, curY);
            gifts.put(currentPoint, gifts.getOrDefault(currentPoint, 0) + 1);
        }
        return gifts;
    }

    private Object[] solve(String data) {
//        int size = deliver(data).size();
        Map<Point, Integer> robotMap = new HashMap<>();
        List<Character> santa = new ArrayList<>();
        List<Character> robot = new ArrayList<>();
        char[] array = data.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                santa.add(array[i]);
            } else {
                robot.add(array[i]);
            }
        }
//        var t = deliver(santa.toString());
//        var r = deliver(robot.toString());
        return new Object[] {0, gifts.size()};
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