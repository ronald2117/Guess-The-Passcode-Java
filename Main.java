import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

class Game {

    Game(int numDigits, int maxTries, int maxNumber) {
        this.numDigits = numDigits;
        this.maxTries = maxTries;
        this.maxNumber = maxNumber;
        secretCode = new int[numDigits];
    }

    int numDigits;
    int maxTries;
    int maxNumber;
    int secretCode[];
    int correctCount = 0;
}

public class Main {
    static Game game;

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void displayMainMenu() {
        clearConsole();
        System.out.println("Welcome to the Guess the Code Game!\n");
        System.out.println("1. Play Game");
        System.out.println("2. Leaderboard");
        System.out.println("3. Exit");

        System.out.print("\nSelect: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                displayGameMenu();
                break;
            case 2:
                // displayLeaderboard();
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

    public static void displayGameMenu() {
        clearConsole();
        System.out.println("Choose the difficulty level:\n");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Custom");
        System.out.println("5. Main Menu");

        System.out.print("\nEnter your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                game = new Game(3, 8, 4);
                displayGame(game);
                break;
            case 2:
                game = new Game(4, 10, 6);
                displayGame(game);
                break;
            case 3:
                game = new Game(5, 12, 8);
                displayGame(game);
                break;
            case 4:
                System.out.print("Enter number of digits: ");
                int numDigits = sc.nextInt();
                System.out.print("Enter max number: ");
                int maxNumber = sc.nextInt();
                System.out.print("Enter max tries: ");
                int maxTries = sc.nextInt();
                game = new Game(numDigits, maxTries, maxNumber);
                displayGame(game);
                break;
            case 5:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        sc.close();
    }

    public void displayLeaderboard() {
        clearConsole();
        System.out.println("Leaderboard");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                // displayEasyLeaderboard();
                break;
            case 2:
                // displayMediumLeaderboard();
                break;
            case 3:
                // displayHardLeaderboard();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        sc.close();
    }

    public static void setSecretCode() {
        Random rand = new Random();
        for (int i = 0; i < game.numDigits; i++) {
            game.secretCode[i] = rand.nextInt(game.maxNumber) + 1;
        }
    }

    public static String convertMsToMinsAndSecs(long time) {
        int seconds = (int) time / 1000;
        int minutes = seconds / 60;

        if (minutes == 0) {
            return seconds + "s";
        } else {
            return minutes + "m " + seconds + "s";
        }
    }
    
    public static void displayWinPage(long time, int round) {
        System.out.println("\n\u001B[32mCongratulations!\u001B[0m");
        System.out.print("The secret code is: ");

        for (int i = 0; i < game.numDigits; i++) {
            System.out.print(game.secretCode[i] + " ");
        }

        System.out.println();

        System.out.println("Time spent: " + convertMsToMinsAndSecs(time));

        System.out.println("\n1. Play Again");
        System.out.println("2. Main Menu");

        System.out.print("Select: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                displayGameMenu();
                break;
            case 2:
                displayMainMenu();
            default:
                break;
        }

        sc.close();
    }

    public static int[] checkGuess(int[] guess) {
        int[] result = new int[2];
        boolean[] correctlyPlaced = new boolean[game.numDigits];
        boolean[] incorrectlyPlaced = new boolean[game.numDigits];
        for (int i = 0; i < game.numDigits; i++) {
            correctlyPlaced[i] = false;
            incorrectlyPlaced[i] = false;
        }

        for (int i = 0; i < game.numDigits; i++) {
            if (guess[i] == game.secretCode[i]) {
                result[0]++;
                correctlyPlaced[i] = true;
            }
        }

        for (int i = 0; i < game.numDigits; i++) {
            if (!correctlyPlaced[i]) {
                for (int j = 0; j < game.numDigits; j++) {
                    if (guess[i] == game.secretCode[j] && !incorrectlyPlaced[j] && !correctlyPlaced[j] && i != j) {
                        result[1]++;
                        incorrectlyPlaced[j] = true;
                        break;
                    }
                }
            }
        }

        return result;

    }

    public static void displayGuesses(Deque<HashMap<int[], int[]>> guesses) {
        for (HashMap<int[], int[]> entry : guesses) {
            for (int[] key : entry.keySet()) {
                for (int i = 0; i < game.numDigits; i++) {
                    System.out.print(key[i] + " ");
                }
                System.out.print("-> ");
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        System.out.print("\u001B[32m" + entry.get(key)[i] + " "); // Green text
                        game.correctCount = entry.get(key)[i];
                    } else {
                        System.out.print("\u001B[31m" + entry.get(key)[i] + "\u001B[0m"); // Red text
                    }
                }
                System.out.println();
            }
        }
    }

    public static void displayGame(Game game) {
        clearConsole();
        setSecretCode();
        long initialTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        Deque<HashMap<int[], int[]>> guesses = new ArrayDeque<HashMap<int[], int[]>>();
        int round = 1;

        System.out.println("Guess the Passcode!");
        System.out.println("You have " + game.maxTries + " tries to guess the " + game.numDigits + "-digit number.");

        while (round <= game.maxTries) {
            clearConsole();
            String input;
            String[] inputArr;
            int[] guess;
            HashMap<int[], int[]> guessMap;

            System.out.println("Round " + round + "/" + game.maxTries);

            if (round != 1) {
                System.out.println("\nGuesses: ");
            }
            
            displayGuesses(guesses);

            // Handle win and lose
            if (game.correctCount == game.numDigits) {
                long timeFinished = System.currentTimeMillis() - initialTime;
                displayWinPage(timeFinished, round);
                break;
            } else {
                System.out.printf("%nEnter a %d-digit number from 1-%d:%n", game.numDigits, game.maxNumber);
                input = sc.nextLine();
                inputArr = input.split(" ");
                guess = new int[game.numDigits];
                for (int i = 0; i < game.numDigits; i++) {
                    guess[i] = Integer.parseInt(inputArr[i]);
                }
                guessMap = new HashMap<int[], int[]>();
                guessMap.put(guess, checkGuess(guess));
                guesses.add(guessMap);
                round++;
            }

        }
        sc.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.displayMainMenu();
    }
}
