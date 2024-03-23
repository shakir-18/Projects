import java.util.*;

public class TicTacToe {
    public static boolean won(String[][] a, String s) {
        if (a[0][0].equals(s)) {
            if (a[0][1].equals(s) && a[0][2].equals(s))
                return true;
            else if (a[1][1].equals(s) && a[2][2].equals(s))
                return true;
            else if (a[1][0].equals(s) && a[2][0].equals(s))
                return true;
        } else if (a[0][2].equals(s)) {
            if (a[1][2].equals(s) && a[2][2].equals(s))
                return true;
            else if (a[1][1].equals(s) && a[2][0].equals(s))
                return true;
        } else if (a[2][0].equals(s) && a[2][1].equals(s) && a[2][2].equals(s))
            return true;
        else if (a[0][1].equals(s) && a[1][1].equals(s) && a[2][1].equals(s))
            return true;
        else if (a[1][0].equals(s) && a[1][1].equals(s) && a[1][2].equals(s))
            return true;

        return false;
    }

    public static boolean player1(String[][] a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLAYER 1 :");
        System.out.println("Enter the row and column you want to insert : ");
        System.out.print("Row : ");
        int row = sc.nextInt();
        System.out.print("Col : ");
        int col = sc.nextInt();
        if (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.println("You haven't entered proper position! Enter again ");
            player1(a);
        } else if (a[row - 1][col - 1] != "•") {
            System.out.println("The position is already filled ! Try a different one : ");
            player1(a);
        } else {
            a[row - 1][col - 1] = "x";
            if (won(a, "x")) {
                System.out.println("Player 1 wins the game!");
                print(a);
                sc.close();
                return true;
            }
        }
        sc.close();
        return false;
    }

    public static boolean player2(String[][] a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLAYER 2 :");
        System.out.println("Enter the row and column you want to insert : ");
        System.out.print("Row : ");
        int row = sc.nextInt();
        System.out.print("Col : ");
        int col = sc.nextInt();
        if (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.println("You haven't entered proper position! Enter again ");
            player2(a);
        } else if (a[row - 1][col - 1] != "•") {
            System.out.println("The position is already filled ! Try a different one : ");
            player2(a);
        } else {
            a[row - 1][col - 1] = "0";
            if (won(a, "0")) {
                System.out.println("Player 2 wins the game!");
                print(a);
                sc.close();
                return true;
            }
        }
        sc.close();
        return false;
    }

    public static void print(String[][] a) {
        System.out.println("—————————————");
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                System.out.print("| " + a[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("—————————————");

        }

    }

    public static void change(String[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = "•";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = "•";
        print(board);
        int c = 0;
        while (c < 9) {
            if (turn == 0) {
                if (player1(board))
                    break;
                turn = 1;
                System.out.println("The board is : ");
                print(board);
            } else {
                if (player2(board))
                    break;
                turn = 0;
                System.out.println("The board is : ");
                print(board);
            }
            c++;
            if (c == 9) {
                System.out.println("Game over! No winner");
                System.out.println("Do you want to play again? \n Press any number for Yes and 0 for No\n" +
                        "Yes/No : ");
                int choice = sc.nextInt();
                if (choice != 0) {
                    change(board);
                    c = 0;
                    if (turn == 0)
                        turn = 1;
                    else
                        turn = 0;
                    print(board);
                } else
                    break;
            }
        }
        sc.close();
    }
}