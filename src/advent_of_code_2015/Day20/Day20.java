package advent_of_code_2015.Day20;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day20 {

    private String fileName;

    public Day20(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static Object[] parseFile(ArrayList<String> rawLines) {
        return new Object[] {Long.parseLong(rawLines.get(0))};
    }

    //more efficient way to compute factors, especially for larger numbers
    private long[] allFactors(long n) {
        ArrayList<Long> lower = new ArrayList<>();
        //sqrt = 25 => 5
        //sqrt = 100 => 10
        //sqrt => 10 => 3
        //next time when it something about factores, use Math.sqrt(n) to get lower ones, and then get upper ones
        // upper ones is the opposite of lower, avoid duplication should use n / i != i
        for (long i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                lower.add(i);
            }
        }
        ArrayList<Long> upper = new ArrayList<>();
        for (long i : lower) {
            if (n / i != i) {
                upper.add(n / i);
            }
        }
        long[] result = new long[lower.size() + upper.size()];
        int index = 0;
        for (var i : lower) {
            result[index++] = i;
        }
        for (int i = upper.size() - 1; i >= 0; i--) {
            result[index++] = upper.get(i);
        }
        return result;
    }
    //my method is inefficient for large number, since it will iterate one by one
    private List<Long> getAllFactors(long n) {
        List<Long> res = new ArrayList<>();
        for (long i = n; i >= 1; i--) {
            if (n % i == 0) {
                res.add(i);
            }
        }
        return res;
    }

    private long totalGifts (long number) {
        long totalGifts = 0;
        var allFactors = allFactors(number);
        for (var factor : allFactors) {
            totalGifts += factor;
        }
        return totalGifts * 10;
    }

    private long totalGifts_v2 (long number) {
        long totalGifts = 0;
        var  allFactors = allFactors(number);
        for (var factor : allFactors) {
            if (number / factor <= 50) {
                totalGifts += factor * 11;
            }
        }
        return  totalGifts;
    }


    private Object[] solve() {
        long k = 10;
        while (true) {
            long curTotalGifts = totalGifts(k);
            if (curTotalGifts >= 34000000) {
                System.out.println("part1 found" + k);
                break;
            }
            k++;
        }
        long j = 1;
        while (true) {
            long curTotalGifts_v2 = totalGifts_v2(j);
            if (curTotalGifts_v2 >= 34000000) {
                System.out.println("part2 found" + j);
                break;
            }
            j++;
        }
        return new Object[] {k, j};
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
        final Object[] result = solve();
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
        String[] files = new String[] {"src/advent_of_code_2015/Day20/sample.txt"};
        for (String filename : files) {
            final Day20 day20 = new Day20(filename);
            day20.execute();
        }
    }
}
