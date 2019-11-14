package lotto;

import java.util.Scanner;

public class LottoApp {

    //initialize variables as class properties so they can be reused within different methods
    private static final int QUESTIONS_AMOUNT = 3;
    private static Scanner scanner = new Scanner(System.in);
    private static Lotto lotto = new Lotto();

    public static void main(String[] args) {

        //ask user for his/her lottery numbers
        System.out.println("Welcome to the Lotto game!");
        askUsersForTheirGuess();
    }

    //ask user for his/her numbers and validate them
    private static void askUsersForTheirGuess() {
        for (int i = 0; i < QUESTIONS_AMOUNT; i++) {
            System.out.println("Guess " + (i + 1) + " out of " + QUESTIONS_AMOUNT + ".");
            System.out.println("Please enter SIX numbers between 1 and 40. Please separate them by ONE space.");
            String numbers = scanner.nextLine().trim();
            if (!lotto.validateUserInput(numbers)) {
                System.out.println("Please enter valid numbers.");
                if (i >= 0) {
                    i--;
                }
            } else {
                lotto.setUserChoice(i, numbers);
            }
        }
    }
}
