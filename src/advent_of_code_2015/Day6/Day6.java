package advent_of_code_2015.Day6;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day6 {

    private String fileName;

    public Day6(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private int[][] grid = new int[1000][1000];
    private int[][] grid2 = new int[1000][1000];


    private static List<Object> parseFile(List<String> rawLines) {
        List<Object> L = new ArrayList<>();
        for (var line : rawLines) {
            String[] parts = line.split("\\s+");
            List<Object> sublist = new ArrayList<>();
            //start from the end to the beginning
            sublist.add(parts[parts.length - 4]);
            String[] nums1 = parts[parts.length - 3].split(",");
            String[] nums2 = parts[parts.length - 1].split(",");
            for (String num : nums1) {
                sublist.add(Integer.parseInt(num));
            }
            for (String num : nums2) {
                sublist.add(Integer.parseInt(num));
            }
            L.add(sublist);
        }
        return L;
    }

    private int part1 (List<Object> data) {
        for (var curObject : data) {
            List<?> curList = (List<?>) curObject;
            String curApproach = (String) curList.get(0);
            int[] start = new int[] {(int)curList.get(1), (int)curList.get(2)};
            int[] end = new int[] {(int)curList.get(3), (int)curList.get(4)};
            for (int row = start[0]; row <= end[0]; row++) {
                for (int col = start[1]; col <= end[1]; col++) {
                    if (curApproach.equals("on")) {
                        grid[row][col] = 1;
                    } else if (curApproach.equals("off")) {
                        grid[row][col] = 0;
                    } else {
                        grid[row][col] = grid[row][col] == 1 ? 0 : 1;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    total++;
                }
            }
        }
        return total;
    }
    private int part2 (List<Object> data) {
        for (var curObject : data) {
            List<?> curList = (List<?>) curObject;
            String curApproach = (String) curList.get(0);
            int[] start = new int[] {(int)curList.get(1), (int)curList.get(2)};
            int[] end = new int[] {(int)curList.get(3), (int)curList.get(4)};
            for (int row = start[0]; row <= end[0]; row++) {
                for (int col = start[1]; col <= end[1]; col++) {
                    if (curApproach.equals("on")) {
                        grid2[row][col] +=  1;
                    } else if (curApproach.equals("off")) {
                        grid2[row][col] = Math.max(0, grid2[row][col] - 1);
                    } else {
                        grid2[row][col] += 2;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2.length; j++) {
                if (grid2[i][j] > 0) {
                    total += grid2[i][j];
                }
            }
        }
        return total;
    }

    private Object[] solve(List<Object> data) {
        int total1 = part1(data);
        int total2 = part2(data);
        return new Object[] {total1, total2};
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
        String[] files = new String[] {"src/advent_of_code_2015/Day6/sample.txt", "src/advent_of_code_2015/Day6/input.txt"};
        for (String filename : files) {
            final Day6 day6 = new Day6(filename);
            day6.execute();
        }
    }
}