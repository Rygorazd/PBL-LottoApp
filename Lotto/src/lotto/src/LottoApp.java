import java.util.Scanner;

public class LottoApp{

    //initialize variables as class properties so they can be reused within different methods
    private static final int QUESTIONS_AMOUNT=3;
    private static Scanner scanner=new Scanner(System.in);
    private static Lotto lotto=new Lotto();

    public static void main(String[] args){
        int firstRoundMatchedNumbers,secondRoundMatchedNumbers,thirdRoundMatchedNumbers;
        String firstRoundResult, secondRoundResult, thirdRoundResult, reply;
        int lotteryNumbers[];
        int userNumbers[][];

        //ask user for his/her lottery numbers
        System.out.println("Welcome to the Lotto game!");
        //start of the loop
        do{
            lotto.InrementTotalGamesPlayed();
            lotto.clear();
            lotto.generateLotteryNumbers();
            askUsersForTheirGuess();
            // calling compute
            lotto.compute();
            // Getting lotto game results from Lotto class
            firstRoundMatchedNumbers=lotto.getfirstRoundMatchedNumbers();
            secondRoundMatchedNumbers=lotto.getsecondRoundMatchedNumbers();
            thirdRoundMatchedNumbers=lotto.getthirdRoundMatchedNumbers();
            firstRoundResult=lotto.getfirstRoundResult();
            secondRoundResult=lotto.getsecondRoundResult();
            thirdRoundResult=lotto.getthirdRoundResult();
            userNumbers=lotto.getUserNumbers();
            lotteryNumbers=lotto.getLotteryNumbers();

            // displaying game results -- this is just for outputting results from compute
            // may be output could be done using JOptionpane ?
            System.out.println("Count of successful numbers: ");

            System.out.println("First set: " + firstRoundMatchedNumbers + firstRoundResult);
            System.out.println("Second set: " + secondRoundMatchedNumbers + secondRoundResult);
            System.out.println("Third set: " + thirdRoundMatchedNumbers + thirdRoundResult);

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
            //loop
            System.out.println("Would you like to continue?(yes/no) ");
            reply=scanner.nextLine();
        }
        while(reply.equalsIgnoreCase("yes"));
        
        //display total games played, total games won and average total win
        System.out.println("You played " + lotto.getTotalGamesPlayed() + " game(s) in total.");
        System.out.println("You won " + lotto.getTotalWinnings() + " out of " + lotto.getTotalGamesPlayed() + " game(s) played.");
        System.out.println("Your average win is €" + lotto.getAverageWin());
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
                lotto.setUserChoice(i,numbers);
            }
        }
    }
}