import java.util.*;

public class NUMBER {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int c = 0;
        int magicNumber = random.nextInt(1, 15);
        System.out.println("-----NUMBER GUESSING GAME!-----");
        while (true) {
            if (c == 1)
                magicNumber = random.nextInt(1, 15);
            int choice, element;
            System.out.println("1.Start ");
            System.out.println("2.Quit ");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the number from 1 to 15: ");
                    // System.out.println();
                    element = sc.nextInt();
                    if (element == magicNumber) {
                        System.out.println("Congrats ! You have won the game .");
                        c++;
                    } else
                        System.out.println("OOPS...the number does not match the magic number !!!");
                }
                default -> {
                    System.out.println("Bye bye !");
                    return;
                }
            }
        }
    }
}