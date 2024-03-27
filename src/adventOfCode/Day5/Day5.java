package adventOfCode.Day5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day5 {

    private String fileName;

    public Day5(final String fileName) {
        this.fileName = fileName;
        createFileIfNotExist(fileName);
    }

    class Mapping {
        long destination;
        long source;
        long range;
        long offset;
        long start;
        long end;

        Mapping(long destination, long source, long range) {
            this.destination = destination;
            this.source = source;
            this.range = range;
            this.offset = this.destination - this.source;
            this.start = this.source;
            this.end = this.source + range;
        }
    }

    class XYMap {
        String fromName;
        String toName;
        List<Mapping> mappings;

        XYMap(String fromName, String toName, List<Mapping> mappings) {
            this.fromName = fromName;
            this.toName = toName;
            this.mappings = mappings;

            mappings.sort((x, y) -> {
                if (x.source != y.source)
                    return Long.compare(x.source, y.source);
                if (x.range != y.range)
                    return Long.compare(x.range, y.range);
                return Long.compare(x.destination, y.destination);
            });
        }
    }

    private Object parseFile(ArrayList<String> rawLines) {
        List<Long> seeds = new ArrayList<>();
        List<XYMap> lines = new ArrayList<>();
        String fromName = "";
        String toName = "";
        List<Mapping> mappings = new ArrayList<>();
        for (String rawLine : rawLines) {
            String[] parts = rawLine.trim().split("\\s+");
            if (seeds.isEmpty()) {
                for (int i = 1; i < parts.length; i++) {
                    seeds.add(Long.parseLong(parts[i]));
                }
            } else {
                if (!rawLine.isEmpty()) {
                    if (fromName.isEmpty() && toName.isEmpty()) {
                        String[] titleParts = parts[0].split("-");
                        fromName = titleParts[0];
                        toName = titleParts[titleParts.length - 1];
                    } else {
                        long destination = Long.parseLong(parts[0]);
                        long source = Long.parseLong(parts[1]);
                        long range = Long.parseLong(parts[2]);
                        mappings.add(new Mapping(destination, source, range));
                    }
                } else {
                    if (!fromName.isEmpty() && !toName.isEmpty() && mappings.size() > 0) {
                        lines.add(new XYMap(fromName, toName, mappings));
                        fromName = "";
                        toName = "";
                        mappings = new ArrayList<>();
                    }
                }
            }
        }
        if (!fromName.isEmpty() && !toName.isEmpty() && mappings.size() > 0) {
            lines.add(new XYMap(fromName, toName, mappings));
            fromName = "";
            toName = "";
            mappings = new ArrayList<>();
        }
        return new Object[]{seeds, lines};
    }

    private Long nextValue (Long seed, List<Mapping> mappings) {
        for (var mapping : mappings) {
            if (seed >= mapping.start && seed < mapping.end) {
                return mapping.destination + (seed - mapping.source);
            }
        }
        return seed;
    }

    public List<Long[]> nextRange(Long start, Long end, List<Mapping> mappings) {
        List<Long[]> ranges = new ArrayList<>();

        if (start < mappings.get(0).start) {
            ranges.add(new Long[]{start, mappings.get(0).start - 1});
            start = mappings.get(0).start;
        }

        for (Mapping m : mappings) {
            if (start > m.end) continue;
            if (end < m.start) break;
            if (start < m.start) {
                ranges.add(new Long[]{start, m.start - 1});
                start = m.start;
            }
            Long maxRange = Math.min(m.end - start, end - start);
            ranges.add(new Long[]{start + m.offset, start + maxRange + m.offset});
            start += maxRange;
        }

        if (end > mappings.get(mappings.size() - 1).end) {
            ranges.add(new Long[]{Math.max(start, mappings.get(mappings.size() - 1).end + 1), end});
        }

        return ranges;
    }

    private Long solve(Object[] data) {

        List<Long> seeds = (List<Long>) data[0];
        List<XYMap> maps = (List<XYMap>) data[1];

        long part1 = Integer.MAX_VALUE;
        String nextMap = "seed";
        for (Long seed : seeds) {
            nextMap = "seed";
            for (var map : maps) {
                if (map.fromName.equals(nextMap)) {
                    nextMap = map.toName;
                    seed = nextValue(seed, map.mappings);
                }
            }
            part1 = Math.min(part1, seed);
        }

        Long part2 = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i += 2) {
            Long start = seeds.get(i);
            Long end = start + seeds.get(i + 1);
            List<Long[]> ranges = new ArrayList<>();
            ranges.add(new Long[] {start, end});

            for (var map : maps) {
                List<Long[]> newRanges = new ArrayList<>();
                for (Long[] r : ranges) {
                    newRanges.addAll(nextRange(r[0], r[1],map.mappings));
                }
                ranges = newRanges;
            }
            part2 = Math.min(part2, ranges.stream().mapToLong(r -> r[0]).min().orElse(Integer.MAX_VALUE));
        }
        return part2;
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
        var data = parseFile(rawLines);
        double parsingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Parsing time for %s: %.3fms", this.fileName, parsingTime));
        start = System.nanoTime();
        Long result = solve((Object[]) data);
        double solvingTime = (System.nanoTime() - start) / 1000000.;
        System.out.println(String.format("Solving time for %s: %.3fms", this.fileName, solvingTime));
        System.out.println(String.format("Final Result for %s: %s", this.fileName, result));
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
        String[] files = new String[] {"/Users/thamiriszhang/Desktop/AdventOfCode2023/src/resources/day5/input.txt", "data.txt"};
        for (String filename : files) {
            final Day5 day5 = new Day5(filename);
            day5.execute();
        }
    }
}
