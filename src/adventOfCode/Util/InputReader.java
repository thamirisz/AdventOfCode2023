package adventOfCode.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    public static List<String> readInputByLine(String inputLocation, Class<?> callingClass) {
        // Using the provided file path
        File file = new File(inputLocation);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read lines and collect them into a list
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
