import java.util.Scanner;// dev branch for Y.Practicum

public class Main {

    public static void main(String[] args) {
        System.out.println("How many peoples?");
        Scanner scanner = new Scanner(System.in);
        int amountPerson = scanner.nextInt();

        while (amountPerson <= 1) {
            System.out.println("Try again.");
            amountPerson = scanner.nextInt();
        }
        System.out.println("This place for calculator");

    }

}

