import java.util.Scanner;

public class sum {
    public static void main(String args[]) {
        System.out.print("Enter a number between 0 and 1000:");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int res = 0;
        for (; number != 0;) {
            res += number % 10;
            number = number / 10;
        }
        System.out.print("\n" + "The sum of the digit is " + res + "\n");

    }

}