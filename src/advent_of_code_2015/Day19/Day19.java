package advent_of_code_2015.Day19;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day19 {

    private String fileName;

    public Day19(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    private static Object[] parseFile(ArrayList<String> rawLines) {
        Map<String, List<String>> map = new HashMap<>();
        String replacement = "";
        for (String rawLine : rawLines) {
            String[] parts = rawLine.split(" => ");
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];
                List<String> values = new ArrayList<>();
                if (map.containsKey(key)) {
                    values = map.get(key);
                }
                values.add(value);
                map.put(key, values);
            } else {
                if (!rawLine.isEmpty()) {
                    replacement = rawLine;
                }
            }
        }
        return new Object[] {map, replacement};
    }

    private Object[] solve(Object[] data) {
        Map<String, List<String>> map = (Map<String, List<String>>) data[0];
        String target = (String) data[1];
        int part1 = part1(map, target);
        int part2 = part2_v2(map, target);
        return new Object[] {part1, part2};
    }

    private int part2 (Map<String, List<String>> map, String message) {
        System.out.println(map);
        Deque<String> deque = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        deque.addLast("e");
        int step = 0;
        int length = message.length();
        boolean findStep = false;
        while (!deque.isEmpty()) {
            int curSize = deque.size();
            while (curSize > 0) {
                String curKey = deque.pollFirst();
                int curKeyLength = curKey.length();

                if (curKeyLength == length) {
                    if (curKey.equals(message)) {
                        findStep = true;
                        break;
                    }
                }

                for (int i = 0; i < curKeyLength; i++) {
                    char c = curKey.charAt(i);
                    String cur = String.valueOf(c);

                    if (map.containsKey(cur)) {
                        List<String> replaceStrings = map.get(cur);
                        for (String replaceString : replaceStrings) {
                            StringBuilder sb = new StringBuilder();
                            String before = "";
                            String after = "";
                            if (i == 0) {
                                if (curKeyLength == 1 && replaceString.length() == 1) {
                                    sb.append(replaceString);
                                } else {
                                    if (curKey.length() == 1) {
                                        sb.append(replaceString);
                                    } else {
                                        after = curKey.substring(i + 1, curKeyLength);
                                        sb.append(replaceString).append(after);
                                    }
                                }
                            } else if (i == curKeyLength - 1) {
                                before = curKey.substring(0, i);
                                sb.append(before).append(replaceString);
                            } else {
                                before = curKey.substring(0, i);
                                after = curKey.substring(i + 1, curKeyLength);
                                sb.append(before).append(replaceString).append(after);
                            }

                            if (!visited.contains(sb.toString())) {
                                visited.add(sb.toString());
                                deque.addLast(sb.toString());
                            }
                        }
                    }

                    if (curKeyLength == 2) {
                        if (map.containsKey(curKey)) {
                            List<String> replaceStrings = map.get(cur);
                            for (String replaceString : replaceStrings) {
                                StringBuilder sb = new StringBuilder();
                                String before = "";
                                String after = "";
                                if (i == 0) {
                                    after = replaceString.substring(i + 2, length);
                                    sb.append(replaceString).append(after);
                                } else if (i == length - 2) {
                                    before = replaceString.substring(0, i);
                                    sb.append(before).append(replaceString);
                                } else {
                                    before = replaceString.substring(0, i);
                                    after = replaceString.substring(i + 2, length);
                                    sb.append(before).append(replaceString).append(after);
                                }
                                if (!visited.contains(sb.toString())) {
                                    visited.add(sb.toString());
                                    deque.addLast(sb.toString());
                                }
                            }
                        }
                    }
                    System.out.println(step + " " + visited.size());
                }
                curSize--;
            }
            if (findStep) {
                return step;
            }
            step++;
        }
        return -1;
    }

    private int part2_v2(Map<String, List<String>> replacements, String target) {
        Map<String, List<String>> newReplacements = new HashMap<>();
        for (String key : replacements.keySet()) {
            for (String w : replacements.get(key)) {
                if (!newReplacements.containsKey(w)) {
                    newReplacements.put(w, new ArrayList<>());
                }
                newReplacements.get(w).add(key);
            }
        }
        replacements = newReplacements;
        System.out.println(replacements);

        Set<String> visited = new HashSet<>();
        PriorityQueue<String> q = new PriorityQueue<>();
        q.add(target);

        int step = 0;
        while (!q.isEmpty()) {
            List<String> nextLine = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals("e")) {
                    return step;
                }
                for (String key : replacements.keySet()) {
                    int foundIndex = word.indexOf(key);
                    while (foundIndex != -1) {
                        for (String replacement : replacements.get(key)) {
                            String newWord = word.substring(0, foundIndex) + replacement + word.substring(foundIndex + key.length());
                            if (!visited.contains(newWord)) {
                                nextLine.add(newWord);
                                visited.add(newWord);
                            }
                        }
                        foundIndex = word.indexOf(key, foundIndex + 1);
                    }
                }
            }
            step++;
            nextLine.sort(Comparator.comparing(String::length));
            q.addAll(nextLine.subList(0, Math.min(1000, nextLine.size())));
            System.out.println(step + " " + visited.size());
        }
        return -1;
    }

    private int part1 (Map<String, List<String>> map, String replacement) {
        Set<String> set = new HashSet<>();
        int length = replacement.length();

        for (int i = 0; i < replacement.length(); i++) {
            char c = replacement.charAt(i);
            String cur = String.valueOf(c);

            if (map.containsKey(cur)) {
                List<String> replaceStrings = map.get(cur);
                for (String replaceString : replaceStrings) {
                    StringBuilder sb = new StringBuilder();
                    String before = "";
                    String after = "";
                    if (i == 0) {
                        after = replacement.substring(i + 1, length);
                        sb.append(replaceString).append(after);
                    } else if (i == length - 1) {
                        before = replacement.substring(0, i);
                        sb.append(before).append(replaceString);
                    } else {
                        before = replacement.substring(0, i);
                        after = replacement.substring(i + 1, length);
                        sb.append(before).append(replaceString).append(after);
                    }
                    set.add(sb.toString());
                }
            }

            if (i < length - 2) {
                cur = replacement.substring(i, i + 2);
                if (map.containsKey(cur)) {
                    List<String> replaceStrings = map.get(cur);
                    for (String replaceString : replaceStrings) {
                        StringBuilder sb = new StringBuilder();
                        String before = "";
                        String after = "";
                        if (i == 0) {
                            after = replacement.substring(i + 2, length);
                            sb.append(replaceString).append(after);
                        } else if (i == length - 2) {
                            before = replacement.substring(0, i);
                            sb.append(before).append(replaceString);
                        } else {
                            before = replacement.substring(0, i);
                            after = replacement.substring(i + 2, length);
                            sb.append(before).append(replaceString).append(after);
                        }
                        set.add(sb.toString());
                    }
                }
            }
        }
        return set.size();
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
        final Object[] result = solve(data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 1:" + (int) result[0]));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, "Result 2: " + (int) result[1]));
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
        String[] files = new String[] {"src/advent_of_code_2015/Day19/sample.txt", "src/advent_of_code_2015/Day19/input.txt"};
        for (String filename : files) {
            final Day19 day19 = new Day19(filename);
            day19.execute();
        }
    }
}