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

    public Integer getFirstDigit (String calibrationValue) {
        int firstNumber = 0;
        char[] array = calibrationValue.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                    firstNumber = Integer.parseInt(String.valueOf(array[i]));
                    break;
            } else {
                StringBuilder firstStringNumber = new StringBuilder();
                firstStringNumber.append(array[i]);
                for (int j = i + 1; j < array.length; j++) {
                    if (myMap.containsKey(firstStringNumber.toString())) {
                        firstNumber = Integer.parseInt(myMap.get(firstStringNumber.toString()));
                        break;
                    } else {
                        firstStringNumber.append(array[j]);
                    }
                }
                if (firstNumber != 0) {
                    break;
                }
            }
        }
        return firstNumber;
    }

    public Integer getLastDigit (String calibrationValue) {
        int lastNumber = 0;
        char[] array = calibrationValue.toCharArray();
        for (int i = array.length - 1; i >= 0; i--) {
            if (Character.isDigit(array[i])) {
                lastNumber = Integer.parseInt(String.valueOf(array[i]));
                break;
            } else {
                StringBuilder lastStringNumber = new StringBuilder();
                lastStringNumber.append(array[i]);
                for (int j = i - 1; j >= 0; j--) {
                    StringBuilder reverseVersion = new StringBuilder();
                    for (var s : lastStringNumber.toString().toCharArray()) {
                        reverseVersion.append(s);
                    }
                    String lastNumberFind = reverseVersion.reverse().toString();
                    if (myMap.containsKey(lastNumberFind)) {
                        lastNumber = Integer.parseInt(myMap.get(lastNumberFind));
                        break;
                    } else {
                        lastStringNumber.append(array[j]);
                    }
                }
                if (lastNumber != 0) {
                    break;
                }
            }
        }
        return lastNumber;
    }

    public String getCalibrationValues (String calibrationValue) {
        int firstNumber = getFirstDigit(calibrationValue);
        int lastNumber = getLastDigit(calibrationValue);
        return Integer.toString((firstNumber * 10) + lastNumber);
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/adventofcode.day1/input.tcv", this.getClass());

        int result = input.stream().map(this::getCalibrationValues).map(Integer::parseInt).reduce(0, Integer::sum);
        System.out.println("Result of day 1 part 2: " + result);
    }
    public static void main (String[] args) {
        Day1 day1Part1 = new Day1();

//      List<String> test = new ArrayList<String>();
//      test.add("1abc2");
//      test.add("pqr3stu8vwx");
//      test.add("a1b2c3d4e5f");
//      test.add("treb7uchet");
        List<String> test2 = new ArrayList<String>();
        test2.add("two1nine");
        test2.add("eightwothree");
        test2.add("abcone2threexyz");
        test2.add("xtwone3four");
        test2.add("4nineeightseven2");
        test2.add("zoneight234");
        test2.add("7pqrstsixteen");
        test2.add("treb7uchet");

//      for (var s : test2) {
//          var t = day1Part1.getCalibrationValues(s);
//          System.out.println(t);
//      }

        day1Part1.findResult();
//      day1Part1.part2("two1nine");
    }

}
