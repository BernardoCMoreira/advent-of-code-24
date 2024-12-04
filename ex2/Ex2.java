package ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) throws IOException {
        String fileName = "./ex2/ex2_input.txt";
        List<ArrayList<Integer>> numbersList = readNumbersFromFile(fileName);

        solveEx2_1(numbersList);
        solveEx2_2(numbersList);
    }

    private static void solveEx2_1(List<ArrayList<Integer>> numbersList){
        int safeOperations = 0;

        for (ArrayList<Integer> array : numbersList) {
            if (analyseSequence(array, false)) {
                safeOperations++;
            }
        }

        System.out.println(safeOperations);
    }

    private static void solveEx2_2(List<ArrayList<Integer>> numbersList){
        int safeOperations = 0;

        for (ArrayList<Integer> array : numbersList) {
            if (analyseSequence(array, true)) {
                safeOperations++;
            }
        }

        System.out.println(safeOperations);
    }

    private static boolean analyseSequence(ArrayList<Integer> array, boolean canUseRecursion) {
        if (array.size() == 1) return true;
    
        boolean isInitialOpIncrease = array.get(0) < array.get(1);
        
        for (int i = 0; i < array.size() - 1; i++) {
            if (!analyseIfOperationIsAllowed(array.get(i), array.get(i + 1), isInitialOpIncrease)) {
                if (canUseRecursion) {
                    for (int j = 0; j < array.size(); j++) {
                        ArrayList<Integer> newArray = new ArrayList<>(array);
                        newArray.remove(j);
                        if (analyseSequence(newArray, false)) return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static boolean analyseIfOperationIsAllowed(int num1, int num2, boolean isInitialOpIncrease){
        if (isInitialOpIncrease && num1 >= num2 || !isInitialOpIncrease && num1 <= num2) return false;
        return analyseDiffBetweenTwoNumbers(num1, num2);
    }
    private static boolean analyseDiffBetweenTwoNumbers(int num1, int num2) {
        if (Math.abs(num1 - num2) >= 1 && Math.abs(num1 - num2) <= 3)
            return true;
        return false;
    }

    private static List<ArrayList<Integer>> readNumbersFromFile(String fileName) {
        List<ArrayList<Integer>> resultList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] stringNumbers = line.split("\\s+");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String stringNumber : stringNumbers) {
                    numbers.add(Integer.parseInt(stringNumber));
                }
                resultList.add(numbers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
