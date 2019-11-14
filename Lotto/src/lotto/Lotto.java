package lotto;

import java.util.Arrays;
import java.util.Random;

public class Lotto {

    //declare variables
    private final int NUMBERS_ARRAY_SIZE;
    private final int MIN_RANDOM_NUMBER;
    private final int MAX_RANDOM_NUMBER;
    private final int USER_CHOICE_ROWS_NUMBER;

    private int[] lotteryNumbers;
    private int[][] userNumbers;
    private Random random;

    public Lotto() {
        //initialize variables
        NUMBERS_ARRAY_SIZE = 6;
        MIN_RANDOM_NUMBER = 1;
        MAX_RANDOM_NUMBER = 40;
        USER_CHOICE_ROWS_NUMBER = 3;

        lotteryNumbers = new int[NUMBERS_ARRAY_SIZE];
        userNumbers = new int[USER_CHOICE_ROWS_NUMBER][NUMBERS_ARRAY_SIZE];
        random = new Random();

        //generate lottery numbers
        generateLotteryNumbers();
    }

    //clear user input and secret number between rounds/games
    public void clear() {
        lotteryNumbers = new int[NUMBERS_ARRAY_SIZE];
        userNumbers = new int[USER_CHOICE_ROWS_NUMBER][NUMBERS_ARRAY_SIZE];
    }

    public boolean validateUserInput(String selectedNumbers) {

        //check if input is null or contains only spaces
        if (selectedNumbers == null || selectedNumbers.trim().isEmpty()) {
            return false;
        }

        //extract numbers from user input
        String[] numbers = exctractNumbers(selectedNumbers.trim());
        
        //too many numbers entered or too many spaces between them
        if(numbers.length > NUMBERS_ARRAY_SIZE) {
            return false;
        }

        //validate extracted numbers - array should contain only numbers between 1 and 40
        for (int i = 0; i < numbers.length; i++) {
            try {
                int temp = Integer.parseInt(numbers[i]);
                if (temp < MIN_RANDOM_NUMBER || temp > MAX_RANDOM_NUMBER) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /* Random returns random int value between 0 (inclusive) and the specified value (exclusive).
       So to get values between 1 and some number we have to add 1 to the randomly generated number.
     */
    private int getRandomNumber() {
        return random.nextInt(MAX_RANDOM_NUMBER) + MIN_RANDOM_NUMBER;
    }

    private void generateLotteryNumbers() {
        for (int i = 0; i < NUMBERS_ARRAY_SIZE; i++) {
            int randomNumber = getRandomNumber();
            lotteryNumbers[i] = randomNumber;

            //if any number is the same as the one generated, generate new random for all numbers up to current index again
            for (int j = 0; j < i; j++) {
                if (lotteryNumbers[j] == randomNumber) {
                    lotteryNumbers[j] = getRandomNumber();
                }
            }
        }
    }

    private String[] exctractNumbers(String numbers) {
        String delimeter = " ";
        return numbers.split(delimeter);
    }

    //setters
    public void setUserChoice(int row, String numbers) {
        String[] selectedNumbers = exctractNumbers(numbers);

        for (int i = 0; i < NUMBERS_ARRAY_SIZE; i++) {
            int temp = Integer.parseInt(selectedNumbers[i]);
            userNumbers[row][i] = temp;
        }
    }

    //getters
    public int[] getLotteryNumbers() {
        return lotteryNumbers;
    }

    public int[][] getUserNumbers() {
        return userNumbers;
    }
}
