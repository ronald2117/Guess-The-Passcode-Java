import java.util.Scanner;
import java.util.Random;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

class Game {
    static int numDigits;
    static int maxTries;
    static int maxNumber;
    static int secretCode[] = new int[numDigits];
    static Deque<HashMap<int[], int[]>> guesses = new ArrayDeque<HashMap<int[], int[]>>();

    public static void setSecretCode() {
        Random rand = new Random();
        for (int i = 0; i < numDigits; i++) {
            secretCode[i] = rand.nextInt(maxNumber) + 1;
        }
    }

    Game(int numDigits, int maxTries, int maxNumber) {
        Game.numDigits = numDigits;
        Game.maxTries = maxTries;
        Game.maxNumber = maxNumber;
    }
}

public class Main {
    static Game game;

    public void displayMainMenu() {
        System.out.println("Welcome to the Guess the Code Game");
        System.out.println("Main Menu");
        System.out.println("1. Play Game");
        System.out.println("2. Leaderboard");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                displayGameMenu();
                break;
            case 2:
                //displayLeaderboard();
                break;
                case 3:
                System.exit(0);
                break;
                default:
                System.out.println("Invalid choice");
                break;
            }
            sc.close();
    }

    public void displayGameMenu() {
        System.out.println("Game Menu");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Custom");
        System.out.println("5. Main Menu");
    }

    public void displayLeaderboard() {
        System.out.println("Leaderboard");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                //displayEasyLeaderboard();
                break;
            case 2:
                //displayMediumLeaderboard();
                break;
            case 3:
                //displayHardLeaderboard();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void displayGame(Game game) {
        System.out.println("Guess the Passcode!");
        System.out.printf("Enter a %d-digit number from 1-%d%:n ", Game.numDigits, Game.maxNumber);
        //get space separated integers from the user and store it in an array
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArr = input.split(" ");
        int[] guess = new int[Game.numDigits];
        for (int i = 0; i < Game.numDigits; i++) {
            guess[i] = Integer.parseInt(inputArr[i]);
        }
    }

    public static void main(String[] args) {
        
    }
}
