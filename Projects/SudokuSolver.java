import java.util.*;

public class SudokuSolver {
    public static void printBoard(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println("Go ahead to fill all the other boxes!\n");
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (board[row][i] == num)
                return false;
        }
        for (int i = 0; i < n; i++) {
            if (board[i][col] == num)
                return false;
        }
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++)
            for (int j = sc; j < sc + 3; j++)
                if (board[i][j] == num)
                    return false;

        return true;
    }

    public static void sudokuSolver(int[][] board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Check out the sudoku first and then fill the empty boxes ! ");
        printBoard(board);
        int n = board.length;
        System.out.println("Enter the numbers in the sudoku: \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    System.out.println("The box board[" + i + "][" + j + "]" + "is already filled\n");
                    continue;
                }
                System.out.println("\nEnter the number at board[" + i + "][" + j + "] in the range [1-9] : ");
                int num = 0;
                while (num < 1 || num > 9) {
                    num = sc.nextInt();
                    if (num < 1 || num > 9)
                        System.out.println("Enter a proper number within the range [1-9] :");
                }
                while (!isSafe(board, i, j, num)) {
                    System.out.println("The number entered is not safe at this position.\nEnter a new number ");
                    num = sc.nextInt();
                    while (num < 1 || num > 9) {
                        num = sc.nextInt();
                        if (num < 1 || num > 9)
                            System.out.println("Enter a proper number within the range [1-9] :");
                    }

                }
                board[i][j] = num;
                System.out.println("Successfully filled ");
                System.out.println("Press 10 if you want to see the sudoku: ");
                if (sc.nextInt() == 10)
                    printBoard(board);
            }
        }
        sc.close();
        System.out.println("The sudoku is solved : ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
        System.out.println("Hey! Welcome to the world of SUDOKU : \n \nLet us solve a sudoku•••\n");
        sudokuSolver(board);
    }
}