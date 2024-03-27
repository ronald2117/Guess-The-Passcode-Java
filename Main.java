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

    public int[] checkGuess(int[] guess) {
        int[] result = new int[2];
        boolean[] checked = new boolean[Game.numDigits];
        for (int i = 0; i < Game.numDigits; i++) {
            if (guess[i] == Game.secretCode[i]) {
                result[0]++;
                checked[i] = true;
            }
        }
        
        for (int i = 0; i < Game.numDigits; i++) {
            if (!checked[i]) {
                for (int j = 0; j < Game.numDigits; j++) {
                    if (guess[i] == Game.secretCode[j] && !checked[j]) {
                        result[1]++;
                        break;
                    }
                }
            }
        }

        return result;

    }

    public void displayGame(Game game) {
        Deque<HashMap<int[], int[]>> guesses = new ArrayDeque<HashMap<int[], int[]>>();
        int round = 1;

        System.out.println("Guess the Passcode!");
        System.out.println("You have " + Game.maxTries + " tries to guess the " + Game.numDigits + "-digit number.");
        
        while (round <= Game.maxTries) {
            String input;
            String[] inputArr;
            int[] guess;
            HashMap<int[], int[]> guessMap;

            System.out.println("Round " + round + "/" + Game.maxTries);
            System.out.println("Guesses: ");
            for (HashMap<int[], int[]> entry : guesses) {
                for (int[] key : entry.keySet()) {
                    for (int i = 0; i < Game.numDigits; i++) {
                        System.out.print(key[i] + " ");
                    }
                    System.out.print("Result: ");
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            System.out.print("\u001B[32m" + entry.get(key)[i] + " "); // Green text
                        } else {
                            System.out.print("\u001B[31m" + entry.get(key)[i] + " "); // Red text
                        }
                    }
                    System.out.println();
                }
            }
            System.out.printf("Enter a %d-digit number from 1-%d:%n ", Game.numDigits, Game.maxNumber);
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            inputArr = input.split(" ");
            guess = new int[Game.numDigits];
            for (int i = 0; i < Game.numDigits; i++) {
                guess[i] = Integer.parseInt(inputArr[i]);
            }
            guessMap = new HashMap<int[], int[]>();
            guessMap.put(guess, checkGuess(guess));
            guesses.add(guessMap);
            round++;
        }
    }

    public static void main(String[] args) {
        
    }
}
