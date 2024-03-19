package adventOfCode.Day3;

import adventOfCode.Util.AocSolution;

import java.util.*;

public class Day3 {
    private final int[][] DIRECTIONS =
            {
                {-1, -1}, {-1,0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
            };

    Set<int[]> starPosition = new HashSet<>();
    Map<int[], List<Integer>> starMap = new HashMap<>();

    private boolean isSymbol (char[][] grid, int row, int column) {
        return grid[row][column] != '.' && !Character.isDigit(grid[row][column]);
    }

    private boolean isStar (char[][] grid, int row, int column) {
        return grid[row][column] == '*';
    }

    private int getGearRations(char[][] grid) {
        int total = 0;
        for (int row = 0; row < grid.length; row++) {
            boolean isAdjacentNumber = false;
            boolean isStar = false;
            int curNumber = 0;
            for (int column = 0; column < grid[0].length; column++) {
                if (Character.isDigit(grid[row][column])) {
                    if (curNumber == 0) {
                        curNumber += Integer.parseInt(String.valueOf(grid[row][column]));
                    } else {
                        curNumber *= 10;
                        curNumber += Integer.parseInt(String.valueOf(grid[row][column]));
                    }
                    if (!isAdjacentNumber) {
                        for (int[] direction : DIRECTIONS) {
                            int nextRow = row + direction[0];
                            int nextColumn = column + direction[1];
                            if (nextRow >= 0 && nextRow < grid.length && nextColumn >= 0 && nextColumn < grid[0].length) {
                                if (isSymbol(grid, nextRow, nextColumn)) {
                                    isAdjacentNumber = true;
                                }
                                if (isStar(grid, nextRow, nextColumn)) {
                                    isStar = true;
                                    int[] position = {nextRow, nextColumn};
                                    starPosition.add(position);
                                }
                            }
                        }
                    }
                } else {
                    if (curNumber != 0) {
                        if (isAdjacentNumber) {
                            total += curNumber;
                        }

                        if (isStar) {
                            int[] position = {row, column};
                            if (!starMap.containsKey(position)) {
                                List<Integer> list = new ArrayList<>();
                                list.add(curNumber);
                                starMap.put(position, list);
                            } else {
                                List<Integer> list = starMap.get(position);
                                list.add(curNumber);
                                starMap.put(position, list);
                            }
                        }
                        curNumber = 0;
                        isAdjacentNumber = false;
                        isStar = false;
                    }
                }
            }
            if (curNumber != 0) {
                if (isAdjacentNumber) {
                    total += curNumber;
                }
                if (isStar) {
                    int[] position = {row, grid[0].length - 1};
                    if (!starMap.containsKey(position)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(curNumber);
                        starMap.put(position, list);
                    } else {
                        List<Integer> list = starMap.get(position);
                        list.add(curNumber);
                        starMap.put(position, list);
                    }
                }
            }
        }

        int result = 0;
        for (Map.Entry<int[], List<Integer>> entry : starMap.entrySet()) {
            List<Integer> list = entry.getValue();
            if (list.size() == 2) {
                int firstValue = list.get(0);
                int secondValue = list.get(1);
                int sum = firstValue * secondValue;
                result += sum;
            }
        }

        return result;
    }
    public void findResult() {
        final AocSolution aoc = new AocSolution("/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/day3/input.txt");
        char[][] grid = (char[][]) aoc.generateGrid();
        int result = getGearRations(grid);
        System.out.println("Result of day 3 part 1: " + result);
    }
    public static void main (String[] args) {
        Day3 day3Part1 = new Day3();
        day3Part1.findResult();
    }
}
