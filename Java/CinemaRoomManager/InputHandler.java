package cinema;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    static int validateInput() {
        try {
            Scanner input = new Scanner(System.in);
            int inputValue = input.nextInt();

            if (inputValue <= 0) {
                System.out.println("Invalid input. Try again.");
                return validateInput();
            }
            return inputValue;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again.");
            return validateInput();
        }
    }

    public static int getUserInput(String message) {
        System.out.println(message);
        return validateInput();
    }

    public static int validateMenuOption() {
        try {
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();

            if (option < 0 || option > Menu.menuItems.size() - 1) {
                System.out.println("Invalid option. Try again.");
                return validateMenuOption();
            }

            return option;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again.");
            return validateMenuOption();
        }
    }
}
