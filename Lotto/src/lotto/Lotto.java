package lotto;

import java.util.Random;

public class Lotto {

    //declare variables
    private final int NUMBERS_ARRAY_SIZE;
    private final int MIN_RANDOM_NUMBER;
    private final int MAX_RANDOM_NUMBER;
    private final int USER_CHOICE_ROWS_NUMBER;

    private int[] lotteryNumbers; // lottery random set numbers 1D array
    private int[][] userNumbers,check; // user three guess set  numbers array
    private Random random; // random numbers variable
    private int count1, count2, count3; // successful guess counters
    private String win1, win2, win3; // variables for game result status
    private boolean duplicates; // variable to check if duplicate numbers are entered

    public Lotto() {
        //initialize variables
        NUMBERS_ARRAY_SIZE = 6;
        MIN_RANDOM_NUMBER = 1;
        MAX_RANDOM_NUMBER = 40;
        USER_CHOICE_ROWS_NUMBER = 3;

        lotteryNumbers = new int[NUMBERS_ARRAY_SIZE];
        userNumbers = new int[USER_CHOICE_ROWS_NUMBER][NUMBERS_ARRAY_SIZE];
        check = new int[USER_CHOICE_ROWS_NUMBER][NUMBERS_ARRAY_SIZE];
        random = new Random();
        count1=0;
        count2=0;
        count3=0;
        win1=" - No Prize, try again ";
        win2=" - No Prize, try again";
        win3=" - No Prize, try again";

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
        
        //too little numbers entered
        if(numbers.length < NUMBERS_ARRAY_SIZE) {
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

    // Compute
    // iteration through each set of user numbers to check with lottery numbers matches.
    // if user guess is matching to lottery number check array is getting updated with matched number ==> line 126
    public void compute(){
        for (int i = 0; i < userNumbers.length; i++){
            for(int j = 0; j < userNumbers[i].length; j++){
                for(int k = 0; k < lotteryNumbers.length; k++){
                    if(lotteryNumbers[k] == userNumbers[i][j]){
                        check[i][j] = userNumbers[i][j];
                    }
                }
            }
        }
        // Count of player's successful guess numbers in check array.
        //  if number is not zero, then count is getting incremented by 1
        for (int i = 0; i < check.length; i++){
            for (int j=0; j < check[i].length; j++){
                if ((i == 0) && (check[i][j] != 0)){
                    count1++;
                }
                if ((i == 1) && (check[i][j] != 0)){
                    count2++;
                }
                if ((i == 2) && (check[i][j] != 0)){
                    count3++;
                }
            }
        }
    // check if player is entitled to prize for each set of numbers as per project requirement
        switch(count1){
            case 3: win1 = "€125";
                break;

            case 4: win1="€300";
                break;

            case 5: win1 = "€1500";
                break;

            case 6: win1 = "Jackpot!";
                break;
        }

        switch(count2){
            case 3: win2 = "€125";
                break;

            case 4: win2 = "€300";
                break;

            case 5: win2 = "€1500";
                break;

            case 6: win2 = "Jackpot!";
                break;
        }
        switch(count3){
            case 3: win3 = "€125";
                break;

            case 4: win3 = "€300";
                break;

            case 5: win3 = "€1500";
                break;

            case 6: win3 = "Jackpot!";
                break;
        }
    }

    //getters
    public int[] getLotteryNumbers() {
        return lotteryNumbers;
    }

    public int[][] getUserNumbers() {
        return userNumbers;
    }

    //getting the first user matched numbers counter
    public int getCount1(){
        return count1;
    }

    //getting the second user matched numbers counter
    public int getCount2(){
        return count2;
    }

    //getting the third  user matched numbers counter
    public int getCount3(){
        return count3;
    }
    // returning game status results for each set of user numbers
    public String getWin1() {
        return win1;
    }

    public String getWin2() {
        return win2;
    }

    public String getWin3() {
        return win3;
    }
}
