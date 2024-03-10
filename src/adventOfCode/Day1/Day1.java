package adventOfCode.Day1;

import adventOfCode.Util.InputReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day1 {

    private final Map<String, String> myMap = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public String getCalibrationValue(String calibrationValue) {
        int cur = 0;
        int lastNumber = 0;
        boolean firstNumberEncounter = false;
        for (Character c : calibrationValue.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!firstNumberEncounter) {
                    cur += Integer.parseInt(String.valueOf(c));
                    firstNumberEncounter = true;
                }
                lastNumber = Integer.parseInt(String.valueOf(c));
            }
        }
        cur = cur * 10;
        cur += lastNumber;
        return Integer.toString(cur);
    }

    public String part2(String input) {
        var test = input.toCharArray();
        int cur = 0;
        int lastNumber = 0;
        boolean firstNumberEncounter = false;
        for (int i = 0; i < test.length; i++) {
            if (Character.isDigit(test[i])) {
                if (!firstNumberEncounter) {
                    cur += Integer.parseInt(String.valueOf(test[i]));
                    firstNumberEncounter = true;
                } else {
                    lastNumber = Integer.parseInt(String.valueOf(test[i]));
                }
            } else {
                StringBuilder curNumber = new StringBuilder();
                curNumber.append(test[i]);
                for (int j = i; j <= test.length; j++) {
                    if (myMap.containsKey(curNumber.toString())) {
                        if (!firstNumberEncounter) {
                            cur += Integer.parseInt(myMap.get(curNumber.toString()));
                            firstNumberEncounter = true;
                        } else {
                            lastNumber = Integer.parseInt(myMap.get(curNumber.toString()));
                        }
                        break;
                    } else {
                        if (j == test.length) {
                            break;
                        }
                        if (j != i) {
                            curNumber.append(test[j]);
                        }
                    }
                }
            }
        }
        if (lastNumber == 0) {
            lastNumber = cur;
        }
        cur = cur * 10;

        cur += lastNumber;
//        System.out.println(cur);
        return Integer.toString(cur);
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/adventofcode.day1/input.tcv", this.getClass());

        int result = input.stream().map(this::part2).map(Integer::parseInt).reduce(0, Integer::sum);
        System.out.println("Result of day 1 part 2: " + result);
    }
    public static void main (String[] args) {
        Day1 day1Part1 = new Day1();

//        List<String> test = new ArrayList<String>();
//        test.add("1abc2");
//        test.add("pqr3stu8vwx");
//        test.add("a1b2c3d4e5f");
//        test.add("treb7uchet");
//        day1Part1.getCalibrationValues(test);
        List<String> test2 = new ArrayList<String>();
        test2.add("two1nine");
        test2.add("eightwothree");
        test2.add("abcone2threexyz");
        test2.add("xtwone3four");
        test2.add("4nineeightseven2");
        test2.add("zoneight234");
        test2.add("7pqrstsixteen");
        test2.add("treb7uchet");

        for (var s : test2) {
            var t = day1Part1.part2(s);
            System.out.println(t);
        }

        day1Part1.findResult();
//        day1Part1.part2("two1nine");
    }

}
