package adventOfCode.Day2;

import adventOfCode.Day1.Day1;
import adventOfCode.Util.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day2 {

    private final Map<String, Integer> validCubes = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    public int getValidPossibleGames (String game) {
        int curGame = 0;
        char[] array = game.toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == ':') {
                curGame = Integer.parseInt(String.valueOf(array[i - 1]));
                if (Character.isDigit(array[i - 2])) {
                    for (int k = i - 2; k >= 0; k--) {
                        if (Character.isDigit(array[k])) {
                            if (curGame == 0 && Integer.parseInt(String.valueOf(array[k])) == 0 && Integer.parseInt(String.valueOf(array[k - 1])) == 0) {
                                curGame = 100;
                            }
                            int value = Integer.parseInt(String.valueOf(array[k])) * 10;
                            curGame = value + curGame;
                        } else {
                            break;
                        }
                    }
                }
            } else if (array[i] == ',' || array[i] == ';' || i == array.length - 1) {
                StringBuilder sb = new StringBuilder();
                for (int j = i == array.length - 1 ? i : i - 1; j >= 0; j--) {
                    if (Character.isDigit(array[j])) {
                        int curDigit = Integer.parseInt(String.valueOf(array[j]));
                        if (Character.isDigit(array[j - 1])) {
                            for (int k = j - 1; k >= 0; k--) {
                                if (Character.isDigit(array[k])) {
                                    int value = Integer.parseInt(String.valueOf(array[k])) * 10;
                                    curDigit = value + curDigit;
                                } else {
                                    break;
                                }
                            }
                        }
                        String curKey = sb.reverse().toString();
                        if (curDigit <= validCubes.get(curKey)) {
                           break;
                        } else {
                            return 0;
                        }
                    } else if (array[j] == ' ') {
                        continue;
                    } else {
                        sb.append(array[j]);
                    }
                }
            }
        }
        return curGame;
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/day2/input.tcv", this.getClass());

        int result = input.stream().map(this::getValidPossibleGames).reduce(0, Integer::sum);
        System.out.println("Result of day 2 part 1: " + result);
    }
    public static void main (String[] args) {
        Day2 day2Part1 = new Day2();

//        List<String> test1 = new ArrayList<String>();
//        test1.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
//        test1.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
//        test1.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
//        test1.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
//        test1.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
//
//        int result = 0;
//        for (var game : test1) {
//            result += day2Part1.getValidPossibleGames(game);
////            System.out.println(result);
//        }
//        System.out.println(result);
        day2Part1.findResult();

    }
}
