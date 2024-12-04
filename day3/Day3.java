package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        try {
            List<String> stringList = readFile("./day3/day3_input.txt");

            solve3_1(stringList);
            solve3_2(stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solve3_1(List<String> stringList){
        int result = 0;
        String input = String.join(":", stringList);
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        for (String match : matches) {
            String formattedMatch = match.replace("mul(", "").replace(")","");
            String[] numbers = formattedMatch.split(",");
            result += (Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]));
        }
        System.out.println(result);
        return result;
    }

    private static int solve3_2(List<String> stringList){
        int result = 0;
        String input = String.join(":", stringList);
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }
        boolean canDoOp = true;
        for (String match : matches) {
            if(match.equals("do()")){
                canDoOp = true;
            }
            else if(match.equals("don't()")){
                canDoOp = false;
            }
            else if (canDoOp){
                String formattedMatch = match.replace("mul(", "").replace(")","");
                String[] numbers = formattedMatch.split(",");
                result += (Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]));
            }
        }
        System.out.println(result);
        return result;
    }

    private static List<String> readFile(String filename) throws IOException{
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        return allLines;
    }
}
