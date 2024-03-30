package advent_of_code_2023.Day1;

import advent_of_code_2023.Util.InputReader;
import java.util.List;
import java.util.Map;

public class Day1 {

    private final Map<String, Integer> myMap = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    private int getFirstDigit (String calibrationValue) {
        int firstNumber = 0;
        char[] array = calibrationValue.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                    firstNumber = Integer.parseInt(String.valueOf(array[i]));
                    break;
            } else {
                int end = i + 5 <= array.length ? i + 5 : array.length;
                firstNumber = checkIfLetterIsNumber(calibrationValue.substring(i, end), false);
                if (firstNumber != 0) {
                    return firstNumber;
                }
            }
        }
        return firstNumber;
    }

    private int getLastDigit (String calibrationValue) {
        int lastNumber = 0;
        char[] array = calibrationValue.toCharArray();
        for (int i = array.length - 1; i >= 0; i--) {
            if (Character.isDigit(array[i])) {
                lastNumber = Integer.parseInt(String.valueOf(array[i]));
                break;
            } else {
                int start = i - 4 >= 0 ? i - 4 : 0;
                String reversedString = reverseString(calibrationValue.substring(start, i + 1));
                lastNumber = checkIfLetterIsNumber(reversedString, true);
                if (lastNumber != 0) {
                    return lastNumber;
                }
            }
        }
        return lastNumber;
    }

    private String reverseString(String value) {
        int i = 0, j = value.length() - 1;
        char[] array = value.toCharArray();
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
//        sdsd;
        return array.toString();
    }
    private int checkIfLetterIsNumber (String letter, boolean isReversedString) {

        for (int i = 0; i < letter.length(); i++) {
            if (i + 3 <= letter.length()
                    && (isReversedString
                    ? myMap.containsKey(reverseString(letter.substring(i, i + 3)))
                    : myMap.containsKey(letter.substring(i, i + 3)))) {
                return isReversedString ? myMap.get(reverseString(letter.substring(i, i + 3))) : myMap.get(letter.substring(i, i + 3));
            } else if (i + 4 <= letter.length()
                    && (isReversedString
                    ? myMap.containsKey(reverseString(letter.substring(i, i + 4)))
                    : myMap.containsKey(letter.substring(i, i + 4)))){
                return isReversedString ? myMap.get(reverseString(letter.substring(i, i + 4))) : myMap.get(letter.substring(i, i + 4));
            } else if (i + 5 <= letter.length()
                    && (isReversedString
                    ? myMap.containsKey(reverseString(letter.substring(i, i + 5)))
                    : myMap.containsKey(letter.substring(i, i + 5)))) {
                return isReversedString ? myMap.get(reverseString(letter.substring(i, i + 5))) : myMap.get(letter.substring(i, i + 5));
            }
        }
        return 0;
    }

    public Integer getCalibrationValues (String calibrationValue) {
        int firstNumber = getFirstDigit(calibrationValue);
        int lastNumber = getLastDigit(calibrationValue);
        return (firstNumber * 10) + lastNumber;
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("src/resources/adventofcode.day1/input.tcv", this.getClass());

        int result = input.stream().map(this::getCalibrationValues).reduce(0, Integer::sum);
        System.out.println("Result of day 1 part 2: " + result);
    }
    public static void main (String[] args) {
        Day1 day1Part1 = new Day1();

//        List<String> test2 = new ArrayList<String>();
//        test2.add("two1nine");
//        test2.add("eightwothree");
//        test2.add("abcone2threexyz");
//        test2.add("xtwone3four");
//        test2.add("4nineeightseven2");
//        test2.add("zoneight234");
//        test2.add("7pqrstsixteen");
//        test2.add("treb7uchet");
//
//      for (var s : test2) {
//          var t = day1Part1.getCalibrationValues(s);
//          System.out.println(t);
//      }
        day1Part1.findResult();
    }

}
