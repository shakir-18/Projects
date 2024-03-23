import java.util.*;

public class NumberToWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = a;
        int[] l = { 10000000, 100000, 1000, 100, 90, 80, 70, 60, 50, 40, 30, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10,
                9, 8, 7, 6, 5, 4, 3, 2, 1 };
        String[] str = { "Crore", "Lakh", "Thousand", "Hundred", "Ninety", "Eighty", "Seventy", "Sixty", "Fifty",
                "Fourty", "Thirty", "Twenty", "Nineteen", "Eighteen", "Seventeen", "Sixteen", "Fifteen", "Fourteen",
                "Thirteen", "Twelve", "Eleven", "Ten", "Nine", "Eight", "Seven", "Six", "Five", "Four", "Three", "Two",
                "One" };
        int k;
        int c;
        String s = "";
        while (a > 0) {
            for (int i = 0; i < l.length; i++) {
                if (a >= l[i] && a > 0) {
                    k = a / l[i];
                    c = 0;
                    for (int j = 0; j < l.length; j++) {
                        if (l[j] == k)
                            c += 1;
                    }
                    if (c > 0) {
                        for (int j = 0; j < l.length; j++) {
                            if (k == l[j] && a >= 100)
                                s = s + str[j];
                        }
                    } else {
                        int d = 0;
                        for (int g = 0; g < l.length; g++) {
                            for (int h = g + 1; h < l.length; h++) {
                                if (l[g] + l[h] == k) {
                                    s += str[g] + str[h];
                                    d += 1;
                                    break;
                                }
                            }
                            if (d > 0)
                                break;
                        }
                    }

                    c = 0;
                    s = s + str[i] + " ";
                    a = a - k * l[i];
                }
            }
        }
        if (b == 0)
            System.out.println("Zero");
        else
            System.out.println(s);
        sc.close();
    }
}