package advent_of_code_2023.Day2;

import advent_of_code_2023.Util.InputReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    private final Map<String, Integer> validCubes = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    private Map<String, List<Integer>> parseString (String game) {
        Map<String, List<Integer>> map = new HashMap<>();
        var splitedString = game.replaceAll(" ", "").split(":");
        var gameNumber = splitedString[0].toCharArray();
        var gameVariety = splitedString[1].toCharArray();
        int gameN = -1;
        for (int i = 0; i < gameNumber.length; i++) {
            if (Character.isDigit(gameNumber[i])) {
                if (gameN == -1) {
                    gameN = Integer.parseInt(String.valueOf(gameNumber[i]));
                } else {
                    gameN *= 10;
                    gameN += Integer.parseInt(String.valueOf(gameNumber[i]));
                }
            }
        }
        map.put("game", List.of(gameN));
        List<Integer> red = new ArrayList<>();
        List<Integer> blue = new ArrayList<>();
        List<Integer> green = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int curNumber = -1;
        for (int i = 0; i < gameVariety.length; i++) {
            if (Character.isDigit(gameVariety[i])) {
                if (curNumber == -1) {
                    curNumber = Integer.parseInt(String.valueOf(gameVariety[i]));
                } else {
                    curNumber *= 10;
                    curNumber += Integer.parseInt(String.valueOf(gameVariety[i]));
                }
            } else if (gameVariety[i] == ',' || gameVariety[i] == ';') {
                continue;
            } else {
                sb.append(gameVariety[i]);
                if (sb.toString().equals("red")) {
                    red.add(curNumber);
                    curNumber = -1;
                    sb.delete(0, sb.length());
                } else if (sb.toString().equals("blue")) {
                    blue.add(curNumber);
                    curNumber = -1;
                    sb.delete(0, sb.length());
                } else if (sb.toString().equals("green")) {
                    green.add(curNumber);
                    curNumber = -1;
                    sb.delete(0, sb.length());
                }
            }
        }
        map.put("red", red);
        map.put("blue", blue);
        map.put("green", green);
        return map;
    }

    public int getValidGames (String game) {
        Map<String, List<Integer>> map = parseString(game);
        for (int i : map.get("red")) {
            if (i > validCubes.get("red")) {
                return 0;
            }
        }
        for (int i : map.get("blue")) {
            if (i > validCubes.get("blue")) {
                return 0;
            }
        }
        for (int i : map.get("green")) {
            if (i > validCubes.get("green")) {
                return 0;
            }
        }
        return map.get("game").get(0);
    }

    public int getValidGamesPart2 (String game) {
        int red = 1;
        int blue = 1;
        int green = 1;
        Map<String, List<Integer>> map = parseString(game);
        for (int i : map.get("red")) {
            if (i > red) {
                red = i;
            }
        }
        for (int i : map.get("blue")) {
            if (i > blue) {
                blue = i;
            }
        }
        for (int i : map.get("green")) {
            if (i > green) {
                green = i;
            }
        }
        return red * blue * green;
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("src/resources/day2/input.tcv", this.getClass());

        int result = input.stream().map(this::getValidGamesPart2).reduce(0, Integer::sum);
        System.out.println("Result of day 2 part 2: " + result);
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
//        int cur = 0;
//        for (var game : test1) {
//            cur = day2Part1.getValidGamesPart2(game);
//            if (cur != 0) {
//                result += cur;
//            }
//        }
//        System.out.println(result);
        day2Part1.findResult();
    }
}
