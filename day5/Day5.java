package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {
    public static void main(String[] args) throws IOException {
        List<String> updateLines = new ArrayList<>();
        List<String> rules = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        readFile(rules, updateLines, "./day5/day5_input.txt");
        Ex5_1(updateLines, rules, map);
        Ex5_2(updateLines, rules, map);
    }

    private static int Ex5_2(List<String> updateLines,List<String> rules, Map<Integer, List<Integer>> map){
        int result = 0;
        readListToHash(rules, map);

        for (String line : updateLines) {
            boolean lineIsIncorrect = false;
            String[] stringNumbers = line.trim().split(",");

            int[] numbers = Arrays.stream(stringNumbers)
                          .filter(s -> !s.isEmpty())
                          .mapToInt(Integer::parseInt)
                          .toArray();
            for(int i=1; i<=numbers.length-1; i++){
                for(int j=0; j<i;j++){
                    if(map.get(numbers[i]).contains(numbers[j])){
                        lineIsIncorrect = true;
                        int aux = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = aux;
                    }
                }
            }
            if(lineIsIncorrect && numbers.length > 0){
               int sum = numbers.length > 1 ? numbers[(numbers.length - 1) / 2] : numbers[0];
               result += sum;
            }
        }

        System.out.println(result);
        return result;
    }

    private static int Ex5_1(List<String> updateLines,List<String> rules, Map<Integer, List<Integer>> map){
        int result = 0;
        readListToHash(rules, map);

        for (String line : updateLines) {
            boolean lineIsIncorrect = false;
            String[] stringNumbers = line.trim().split(",");

            int[] numbers = Arrays.stream(stringNumbers)
                          .filter(s -> !s.isEmpty())
                          .mapToInt(Integer::parseInt)
                          .toArray();
            for(int i=1; i<=numbers.length-1; i++){
                if(!map.containsKey(numbers[i]) || lineIsIncorrect) break;
                for(int j=0; j<i;j++){
                    if(map.get(numbers[i]).contains(numbers[j])){
                        lineIsIncorrect = true;
                        break;
                    }
                }
            }
            if(!lineIsIncorrect && numbers.length > 0){
               int sum = numbers.length > 1 ? numbers[(numbers.length - 1) / 2] : numbers[0];
               result += sum;
            }
        }

        System.out.println(result);
        return result;
    }

    private static void readListToHash(List<String> rules, Map<Integer, List<Integer>> map){
        for (String rule : rules) {
            String[] parts = rule.split("\\|");
    
            int key = Integer.parseInt(parts[0].trim());
            int value = Integer.parseInt(parts[1].trim());
    
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
    }


    private static void readFile(List<String> rules, List<String> updateLines, String filename) throws IOException{
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        for (String line : allLines) {
            line = line.trim();
            if (line.contains("|")) {
                rules.add(line);
            } else {
                updateLines.add(line);
            }
        }
    }
}
