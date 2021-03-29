import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int length,noBlack, noWhite,noGuesses=0;

        System.out.println("Welcome to the Mastermind game! Your objective is to find a secret number. Depending on your guess, you will be given the number of blacks and whites. ");
        System.out.println("Blacks - the number of digits that are correct and in the right position,\nWhites - the number of digits that are correct but not in the right position");
        System.out.println("The game will end when you guess the secret number");
        System.out.println("Please write the length of the number you want to guess (between 2 and 6)");

        while(true){
            length = scanner.nextInt();
            if(length>=2 && length<=6){
                break;
            }
            System.out.println("Invalid input! The length must be between 2 and 6.");
        }

        char[] charRandom = new char[length];
        char[] charInput = new char[length];
        boolean[] boolBlackFound = new boolean[length];
        boolean[] boolVisited = new boolean[length];
        int intRandom = (int) (random.nextInt( 9 * (int)Math.pow(10,length-1)) + Math.pow(10,length-1)); // 1000-9999

        String s = String.valueOf(intRandom);
        System.out.println(s);
        System.out.println("Begin the game! Enter your guess!");

        while (true) {
            noBlack = noWhite = 0;
            String strRandom = scanner.nextLine();

            if (strRandom.length() != length) {
                System.out.println("You entered a number of length different than " + length);
                continue;
            }

            for (int i = 0; i < length; i++) {
                boolBlackFound[i] = false;
                boolVisited[i] = false;
                charRandom[i] = s.charAt(i);
                charInput[i] = strRandom.charAt(i);
                if (charRandom[i] == charInput[i]) {
                    noBlack++;
                    boolBlackFound[i] = true;
                    boolVisited[i] = true;
                }
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (!boolBlackFound[i] && charInput[i] == charRandom[j] && !boolVisited[j]) {
                        noWhite++;
                        boolVisited[j] = true;
                        break;
                    }
                }
            }

            noGuesses++;
            if (noBlack == length) {
                System.out.println("Congratulations! You guessed after " + noGuesses + " attempts.");
                break;
            } else {
                System.out.println("blacks: " + noBlack + ", whites: " + noWhite);
            }
        }
    }
}