package advent_of_code_2015.Day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day2 {

    private String fileName;

    public Day2(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static List<List<Integer>>  parseFile(ArrayList<String> rawLines) {
        List<List<Integer>> res = new ArrayList<>();
        for (var rawLine: rawLines) {
            String[] stringNumbers = rawLine.split("x");
            List<Integer> curRes = new ArrayList<>();
            for (String s: stringNumbers) {
                curRes.add(Integer.parseInt(s));
            }
            res.add(curRes);
        }
        return res;
    }

    private Object[] solve(List<List<Integer>> data) {
        int total = 0;
        int total2 = 0;
        for (var eachData : data) {
            int lengthWidth = eachData.get(0) * eachData.get(1);
            int widthHeight = eachData.get(1) * eachData.get(2);
            int heightLength = eachData.get(0) * eachData.get(2);
            int smallestSide = Math.min(heightLength, Math.min(lengthWidth, widthHeight));
            total += smallestSide + (lengthWidth * 2) + (widthHeight * 2) + (heightLength * 2);

            //sort the array
            var array = eachData.toArray(new Integer[0]);
            Arrays.sort(array);

            int lowest = array[0] * 2 ;
            int secondLowest = array[1] * 2;

            total2 += lowest + secondLowest + (eachData.get(0) * eachData.get(1) * eachData.get(2));
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
        final List<List<Integer>> data = parseFile(rawLines);
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
        String[] files = new String[] {"src/advent_of_code_2015/Day2/sample.txt", "src/advent_of_code_2015/Day2/input.txt"};
        for (String filename : files) {
            final Day2 day2 = new Day2(filename);
            day2.execute();
        }
    }
}