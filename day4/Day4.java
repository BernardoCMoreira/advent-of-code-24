package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day4 {
    
    public static void main(String[] args) throws IOException {
        char[][] grid = readFile("./day4/day4_input.txt");
        solve4_1(grid);
        solve4_2(grid);
    }

    private static int solve4_1(char[][] grid) {
        int result = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= cols - 1; j++) {
                if (grid[i][j] == 'X') {
                    // search left
                    if (canLookUpOrLeft(j)) {
                        if (grid[i][j - 1] == 'M' && grid[i][j - 2] == 'A' && grid[i][j - 3] == 'S')
                            result++;
                    }
                    // search up
                    if (canLookUpOrLeft(i)) {
                        if (grid[i - 1][j] == 'M' && grid[i - 2][j] == 'A' && grid[i - 3][j] == 'S')
                            result++;
                    }
                    // search right
                    if (canLookRight(j, cols - 1)) {
                        if (grid[i][j + 1] == 'M' && grid[i][j + 2] == 'A' && grid[i][j + 3] == 'S')
                            result++;
                    }
                    // search down
                    if (canLookDown(i, rows - 1)) {
                        if (grid[i + 1][j] == 'M' && grid[i + 2][j] == 'A' && grid[i + 3][j] == 'S')
                            result++;
                    }
                    // search diagonal right down
                    if (canLookRight(j, cols - 1) && canLookDown(i, rows - 1)) {
                        if (grid[i + 1][j + 1] == 'M' && grid[i + 2][j + 2] == 'A' && grid[i + 3][j + 3] == 'S')
                            result++;
                    }
                    // search diagonal right up
                    if (canLookRight(j, cols - 1) && canLookUpOrLeft(i)) {
                        if (grid[i - 1][j + 1] == 'M' && grid[i - 2][j + 2] == 'A' && grid[i - 3][j + 3] == 'S')
                            result++;
                    }

                    // search diagonal left down
                    if (canLookUpOrLeft(j) && canLookDown(i, rows - 1)) {
                        if (grid[i + 1][j - 1] == 'M' && grid[i + 2][j - 2] == 'A' && grid[i + 3][j - 3] == 'S')
                            result++;
                    }

                    // search diagonal left up
                    if (canLookUpOrLeft(j) && canLookUpOrLeft(i)) {
                        if (grid[i - 1][j - 1] == 'M' && grid[i - 2][j - 2] == 'A' && grid[i - 3][j - 3] == 'S')
                            result++;
                    }
                }
            }
        }
        System.out.println("Ex4_1: " + result);
        return result;
    }

    private static int solve4_2(char[][] grid) {
        int result = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <=cols - 1; j++) {
                if (grid[i][j] == 'A') {
                    if(findRestOfCharsInXFormat(grid, i,j,rows-1,cols-1)) result++;
                }
            }
        }
        System.out.println("Ex4_2: " + result);
        return result;
    }

    private static boolean findRestOfCharsInXFormat(char[][] grid, int posRow, int posCol, int maxRow, int maxCol){
        // up is M.S, down needs to be M.S
        if(posCol-1 >= 0 && posRow - 1 >= 0 && posCol+1 <= maxCol &&grid[posRow-1][posCol-1] == 'M' && grid[posRow-1][posCol+1] == 'S'){
            if(posCol-1 >= 0 && posCol+1 <= maxCol && posRow+1 <= maxRow &&grid[posRow+1][posCol-1] == 'M' && grid[posRow+1][posCol+1] == 'S') return true;
        }
        // up is S.M, down needs to be S.M
        if(posCol-1 >= 0 && posRow - 1 >= 0 && posCol+1 <= maxCol &&grid[posRow-1][posCol-1] == 'S' && grid[posRow-1][posCol+1] == 'M'){
            if(posCol-1 >= 0 && posCol+1 <= maxCol && posRow+1 <= maxRow &&grid[posRow+1][posCol-1] == 'S' && grid[posRow+1][posCol+1] == 'M') return true;
        }
        // up is M.M, down needs to be S.S
        if(posCol-1 >= 0 && posRow - 1 >= 0 && posCol+1 <= maxCol &&grid[posRow-1][posCol-1] == 'M' && grid[posRow-1][posCol+1] == 'M'){
            if(posCol-1 >= 0 && posCol+1 <= maxCol && posRow+1 <= maxRow &&grid[posRow+1][posCol-1] == 'S' && grid[posRow+1][posCol+1] == 'S') return true;
        }
        // up is S.S, down needs to be M.M
        if(posCol-1 >= 0 && posRow - 1 >= 0 && posCol+1 <= maxCol &&grid[posRow-1][posCol-1] == 'S' && grid[posRow-1][posCol+1] == 'S'){
            if(posCol-1 >= 0 && posCol+1 <= maxCol && posRow+1 <= maxRow &&grid[posRow+1][posCol-1] == 'M' && grid[posRow+1][posCol+1] == 'M') return true;
        }
        return false;
    }
    private static boolean canLookUpOrLeft(int pos) {
        if (pos >= 3)
            return true;
        return false;
    }

    private static boolean canLookRight(int pos, int nCols) {
        if (pos + 3 <= nCols)
            return true;
        return false;
    }

    private static boolean canLookDown(int pos, int nRows) {
        if (pos + 3 <= nRows)
            return true;
        return false;
    }

    private static char[][] readFile(String filename) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(filename));
        char[][] grid = new char[allLines.size()][];
        for (int i = 0; i < allLines.size(); i++) {
            grid[i] = allLines.get(i).toCharArray();
        }

        return grid;
    }

}
