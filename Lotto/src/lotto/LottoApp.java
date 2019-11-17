package lotto;

import java.util.Scanner;

public class LottoApp{

    //initialize variables as class properties so they can be reused within different methods
    private static final int QUESTIONS_AMOUNT=3;
    private static Scanner scanner=new Scanner(System.in);
    private static Lotto lotto=new Lotto();

    public static void main(String[] args){
        int count1,count2,count3;
        String win1, win2, win3;
        int lotteryNumbers[];
        int userNumbers[][];

        //ask user for his/her lottery numbers
        System.out.println("Welcome to the Lotto game!");
        askUsersForTheirGuess();

        // calling compute
        lotto.compute();
        // Getting lotto game results from Lotto class
        count1=lotto.getCount1();
        count2=lotto.getCount2();
        count3=lotto.getCount3();
        win1=lotto.getWin1();
        win2=lotto.getWin2();
        win3=lotto.getWin3();
        userNumbers=lotto.getUserNumbers();
        lotteryNumbers=lotto.getLotteryNumbers();

        // displaying game results -- this is just for outputting results from compute
        // may be output could be done using JOptionpane ?
        System.out.println("count of successful numbers: ");

        System.out.println("First set: " + count1 + win1);
        System.out.println("Second set: " + count2 + win2);
        System.out.println("Third set: " + count3 + win3);

        for(int i=0;i<3;i++ ){
            System.out.println((i+1)+" set numbers");
            for(int j=0;j<6;j++){
                System.out.print(userNumbers[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println("Lottery numbers: ");
        for(int i=0;i<6;i++){
            System.out.print(lotteryNumbers[i]+" ");
        }
    }

    //ask user for his/her numbers and validate them
    private static void askUsersForTheirGuess(){
        for(int i=0;i<QUESTIONS_AMOUNT;i++){
            System.out.println("Guess " + (i+1) + " out of " + QUESTIONS_AMOUNT + ".");
            System.out.println("Please enter SIX numbers between 1 and 40. Please separate them by ONE space.");
            String numbers=scanner.nextLine().trim();
            if(!lotto.validateUserInput(numbers)){
                System.out.println("Please enter valid numbers.");
                if (i>=0){
                    i--;
                }
            }else{
                lotto.setUserChoice(i, numbers);
            }
        }
    }
}
