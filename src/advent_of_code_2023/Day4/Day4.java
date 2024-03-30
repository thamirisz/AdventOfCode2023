package advent_of_code_2023.Day4;

import advent_of_code_2023.Util.InputReader;

import java.util.*;

public class Day4 {

    private final Map<Integer, Integer> matchingMap = new HashMap<>();

    private Integer parseCardNumber(String card) {
        var cardKeyValue = card.split(":");
        Set<Integer> cardNumber = storeInteger(cardKeyValue[0].toCharArray());
        var array = cardNumber.toArray();
        return (Integer) array[0];
    }

    private String[] parse (String card) {
        var cardKeyValue = card.split(":");
        return cardKeyValue[1].split("\\|");
    }

    private Set<Integer> storeInteger (char[] array) {
        Set<Integer> set = new HashSet<>();
        int curNumber = 0;
        for (char i : array) {
            if (Character.isDigit(i)) {
                if (curNumber != 0) {
                    curNumber = curNumber * 10 + Integer.parseInt(String.valueOf(i));
                } else {
                    curNumber += Integer.parseInt(String.valueOf(i));
                }
            } else {
                if (curNumber != 0) {
                    set.add(curNumber);
                    curNumber = 0;
                }
            }
        }
        if (curNumber != 0) {
            set.add(curNumber);
        }
        return set;
    }

    private int getResult(String card) {
        Integer cardNumber = parseCardNumber(card);
        String[] numbers = parse(card);
        Set<Integer> key = storeInteger(numbers[0].toCharArray());
        Set<Integer> value = storeInteger(numbers[1].toCharArray());
        int total = 0;
        int matchingNumber = 0;
        for (int n : key) {
            if (value.contains(n)) {
                if (total == 0) {
                    total = 1;
                } else {
                    total *= 2;
                }
                matchingNumber++;
            }
        }
        matchingMap.put(cardNumber, matchingMap.getOrDefault(cardNumber, 0) + matchingNumber);
        return total;
    }

    private int getResult2 () {
        int totalInstances = 0;
        int[] copyCards = new int[matchingMap.size() + 1];
        for (Map.Entry<Integer, Integer> entry : matchingMap.entrySet()) {
            int curInstance = 0;
            int curCardNumber = entry.getKey();
            int matchingNumber = entry.getValue();
            curInstance = 1 + copyCards[curCardNumber];
            for (int i = 0; i < curInstance; i++) {
                for (int j = matchingNumber; j >= 0; j--) {
                    copyCards[curCardNumber + j] = copyCards[curCardNumber + j] + 1;
                }
            }
            totalInstances += curInstance;

        }
        return totalInstances;
    }

    public void findResult() {
        List<String> input = InputReader.readInputByLine("src/advent_of_code_2023/Day4/input.txt", this.getClass());
        int result = input.stream().map(this::getResult).reduce(0, Integer::sum);
        System.out.println("Result of day 4 part 1: " + result);
        int result2 = getResult2();
        System.out.println("Result of day 4 part 2: " + result2);
    }

    public static void main (String[] args) {
        Day4 day4Part1 = new Day4();
        day4Part1.findResult();
    }
}
