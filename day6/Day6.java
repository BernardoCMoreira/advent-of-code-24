package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    static class Entry {
        int[] coordinates;
        char direction;

        public Entry(int[] coordinates, char direction) {
            this.coordinates = coordinates;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return direction == entry.direction && Arrays.equals(coordinates, entry.coordinates);
        }
    }

    public static void main(String[] args) throws IOException {
        String filename = "./day6/day6_input.txt";
        List<String> listString = readFile(filename);

        char[][] twoDimArray = new char[listString.size()][listString.get(0).length()];
        char[][] twoDimArray_2 = new char[listString.size()][listString.get(0).length()];

        // x,y
        int[] guardPosition = { -1, -1 };
        char guard = '^';

        createArrayAndFindGuard(listString, twoDimArray, guardPosition, guard);
        createArrayAndFindGuard(listString, twoDimArray_2, guardPosition, guard);

        int solution6_1 = solve6_1(twoDimArray, guardPosition);
        System.out.println(solution6_1);

        solve6_2(twoDimArray_2, guardPosition);
    }

    // end positions would be any y=0, x=0; x0, x=twoDimArray[0].length,
    // y=twoDimArray.length
    private static int solve6_1(char[][] twoDimArray, int[] guardPosition) {
        int result = 1; // include the initial position

        // move upwards from initial position
        char move = 'U'; // U, D, L R
        int xGuard = guardPosition[0];
        int yGuard = guardPosition[1];
        while (true) {
            twoDimArray[yGuard][xGuard] = '';
            switch (move) {
                case 'U':
                    if (yGuard - 1 >= 0 && twoDimArray[yGuard - 1][xGuard] != '#') {
                        yGuard--;
                        if (twoDimArray[yGuard][xGuard] != '')
                            result++;
                        if (yGuard == 0)
                            move = 'F';
                    } else {
                        move = 'R';
                    }
                    break;
                case 'D':
                    if (yGuard + 1 < twoDimArray.length && twoDimArray[yGuard + 1][xGuard] != '#') {
                        yGuard++;
                        if (twoDimArray[yGuard][xGuard] != '')
                            result++;
                        if (yGuard == twoDimArray.length - 1)
                            move = 'F';
                    } else {
                        move = 'L';
                    }
                    break;
                case 'L':
                    if (xGuard - 1 >= 0 && twoDimArray[yGuard][xGuard - 1] != '#') {
                        xGuard--;
                        if (twoDimArray[yGuard][xGuard] != '')
                            result++;
                        if (xGuard == 0)
                            move = 'F';
                    } else {
                        move = 'U';
                    }
                    break;

                case 'R':
                    if (xGuard + 1 < twoDimArray[yGuard].length && twoDimArray[yGuard][xGuard + 1] != '#') {
                        xGuard++;
                        if (twoDimArray[yGuard][xGuard] != '')
                            result++;
                        if (xGuard == twoDimArray.length - 1)
                            move = 'F';
                    } else {
                        move = 'D';
                    }
                    break;

                default:
                    break;
            }
            if (move == 'F') {
                return result;
            }
        }
    }

    private static void solve6_2(char[][] twoDimArray, int[] guardPosition){
        int count = 0;
        for(int i=0; i<twoDimArray.length; i++){
            for(int j=0; j<twoDimArray[i].length; j++){
                if(twoDimArray[i][j] == '.'){
                    twoDimArray[i][j] = '#';
                    if(solve6_2_infinite_loop(twoDimArray, guardPosition) == true){
                        count++;
                     }
                twoDimArray[i][j] = '.';
                }
            }
            
        }
        System.out.println(count);
    }

    private static boolean solve6_2_infinite_loop(char[][] twoDimArray, int[] guardPosition) {
        // move upwards from initial position
        List<Entry> checkedCoordinatesAndDirections = new ArrayList<>();

        char move = 'U'; // U, D, L R
        int xGuard = guardPosition[0];
        int yGuard = guardPosition[1];
        while (true) {
            Entry newMove = new Entry(new int []{xGuard, yGuard}, move);
            boolean exists = checkedCoordinatesAndDirections.contains(newMove);
            if (exists){
                return true;
            }
            checkedCoordinatesAndDirections.add(newMove);
            switch (move) {
                case 'U':
                    if (yGuard - 1 >= 0 && twoDimArray[yGuard - 1][xGuard] != '#') {
                        yGuard--;
                        if (yGuard == 0)
                            move = 'F';
                    } else {
                        move = 'R';
                    }
                    break;
                case 'D':
                    if (yGuard + 1 < twoDimArray.length && twoDimArray[yGuard + 1][xGuard] != '#') {
                        yGuard++;
                        if (yGuard == twoDimArray.length - 1)
                            move = 'F';
                    } else {
                        move = 'L';
                    }
                    break;
                case 'L':
                    if (xGuard - 1 >= 0 && twoDimArray[yGuard][xGuard - 1] != '#') {
                        xGuard--;
                        if (xGuard == 0)
                            move = 'F';
                    } else {
                        move = 'U';
                    }
                    break;

                case 'R':
                    if (xGuard + 1 < twoDimArray[yGuard].length && twoDimArray[yGuard][xGuard + 1] != '#') {
                        xGuard++;
                        if (xGuard == twoDimArray.length - 1)
                            move = 'F';
                    } else {
                        move = 'D';
                    }
                    break;

                default:
                    break;
            }
            if (move == 'F') {
                return false;
            }
        }
    }
    
    private static void createArrayAndFindGuard(List<String> listString, char[][] twoDimArray, int[] guardPosition,
            char guard) {
        for (int i = 0; i < listString.size(); i++) {
            twoDimArray[i] = listString.get(i).toCharArray();
            int index = listString.get(i).indexOf(guard);
            if (index != -1) {
                guardPosition[0] = index;
                guardPosition[1] = i;
            }
        }
    }

    private static List<String> readFile(String filename) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        return allLines;
    }
}
