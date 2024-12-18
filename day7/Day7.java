package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws IOException {
        List<String> file = readFile("./day7/day7_input.txt");
        List<long[]> numbersMap = new ArrayList<>();

        for (String line : file) {
            String[] split = line.split(":");
            Long objective = Long.parseLong(split[0]);
            String cleaned = split[1].trim();

            long[] numbers = Arrays.stream(cleaned.split(" "))
                        .filter(s -> !s.isEmpty())
                        .mapToLong(Long::parseLong)
                        .toArray();
            
            long[] allNumbers = new long[numbers.length + 1];
            allNumbers[0] = objective;
            System.arraycopy(numbers, 0, allNumbers, 1, numbers.length);
            numbersMap.add(allNumbers);
        }
        solve7_1(numbersMap);
        solve7_2(numbersMap);
    }

    private static long solve7_1(List<long[]> numbersMap) {
        long result = 0;

        for (long[] entry : numbersMap) {
            Long key = entry[0];
            boolean canCreate = canCreateObjective(entry, 2, entry[1], key, false);
            if (canCreate){
                result += key;
            }
        }
        System.out.println(result);
        return result;
    }

    private static long solve7_2(List<long[]> numbersMap) {
        long result = 0;

        for (long[] entry : numbersMap) {
            Long key = entry[0];
            boolean canCreate = canCreateObjective(entry, 2, entry[1], key, true);
            if (canCreate){
                result += key;
            }
        }
        System.out.println(result);
        return result;
    }

    private static boolean canCreateObjective(long[] numbers, int index, long currentValue, long target, boolean canConcat) {

        if (index == numbers.length) {
            return currentValue == target;
        }
        long number = numbers[index];

        if(canConcat){
            if (canCreateObjective(numbers, index + 1, Long.valueOf(String.valueOf(currentValue) + String.valueOf(number)), target, canConcat)) return true;
        }

        return canCreateObjective(numbers, index + 1, (currentValue + number), target, canConcat) || canCreateObjective(numbers, index + 1, (currentValue * number), target, canConcat); 
    }

    private static List<String> readFile(String filename) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        return allLines;
    }
}
