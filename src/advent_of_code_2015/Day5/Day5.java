package advent_of_code_2015.Day5;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day5 {

    private String fileName;

    public Day5(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static List<String> parseFile(List<String> rawLines) {
        return rawLines;
    }

    private Object[] solve(List<String> data) {
        Set<Character> vowels = Set.of('a', 'e', 'i','o','u');
        Set<String> notAllowed = Set.of("ab", "cd", "pq", "xy");

        int part1_totalNiceStrings = 0;
        for (String string : data) {
            char[] array = string.toCharArray();
            int containsVowels = 0;
            boolean containsNotAllowed = false;
            boolean containsDoubleLetter = false;
            for (int i = 0; i < array.length; i++) {
                if (vowels.contains(array[i])) {
                    containsVowels++;
                }
                StringBuilder sb = new StringBuilder();
                if (i + 1 < array.length) {
                    if (array[i] == array[i + 1]) {
                        containsDoubleLetter = true;
                    }
                    sb.append(array[i]).append(array[i + 1]);
                    if (notAllowed.contains(sb.toString())) {
                        containsNotAllowed = true;
                        break;
                    }
                }
            }
            if (containsVowels >= 3 && containsDoubleLetter && !containsNotAllowed) {
                part1_totalNiceStrings++;
            }
        }

        int part2_totalNiceStrings = 0;
        for (String string : data) {
            Map<String, int[]> pairs = new HashMap<>();
            boolean repetition = false;
            for (int i = 1; i < string.length(); i++) {
                String pair = string.substring(i - 1, i + 1);
                if (!pairs.containsKey(pair)) {
                    //store the index
                    pairs.put(pair, new int[]{i, i});
                } else {
                    //update the index
                    pairs.get(pair)[1] = i;
                }

                if (i < string.length() - 1 && string.charAt(i - 1) == string.charAt(i + 1)) {
                    repetition = true;
                }
            }
            for (int[] indices : pairs.values()) {
                if (indices[1] - indices[0] > 1 && repetition) {
                    part2_totalNiceStrings++;
                    break;
                }
            }

        }
        return new Object[] {part1_totalNiceStrings, part2_totalNiceStrings};
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
        final List<String> data = parseFile(rawLines);
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
        String[] files = new String[] {"src/advent_of_code_2015/Day5/sample.txt", "src/advent_of_code_2015/Day5/input.txt"};
        for (String filename : files) {
            final Day5 day5 = new Day5(filename);
            day5.execute();
        }
    }
}