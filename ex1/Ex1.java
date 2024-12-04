import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HelloWorld {
    public static void main(String[] args) throws IOException {
        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(
                new FileReader("./ex1/ex1_input.txt"));
        String line = bf.readLine();

        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        bf.close();

        String[] array = listOfStrings.toArray(new String[0]);

        System.out.println(solveEx1_1(array));
        System.out.println(solveEx1_2(array));

    }

    private static int solveEx1_1(String[] array){
        int[] arr1 =  new int[array.length];
        int[] arr2 =  new int[array.length];
        int count = 0;
        for (String str : array) {
            String[] arrSplit = str.trim().split(" \\s+");
            arr1[count] = Integer.valueOf(arrSplit[0]);
            arr2[count] = Integer.valueOf(arrSplit[1]);
            count++;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int result = 0;

        for(int i = 0; i<=arr1.length-1 ; i ++){
            int diff = Math.abs(arr1[i] - arr2[i]);
            result += diff;
        }
        return result;
    }

    private static int solveEx1_2(String[] array){
        int[] arr1 =  new int[array.length];
        int[] arr2 =  new int[array.length];
        int count = 0;
        for (String str : array) {
            String[] arrSplit = str.trim().split(" \\s+");
            arr1[count] = Integer.valueOf(arrSplit[0]);
            arr2[count] = Integer.valueOf(arrSplit[1]);
            count++;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int similarity = 0;

        for(int i = 0; i<=arr1.length-1 ; i ++){
            int countAppear = 0;
            for(int j=0; j<=arr2.length-1; j++){
                if (arr1[i] == arr2[j]) countAppear++;
            }

            similarity += arr1[i]*countAppear;
        }
        return similarity;
    }

}