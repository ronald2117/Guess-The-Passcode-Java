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

    public static String randomLoseStatement() {
        String loseStatement = "You lose!";

        String[] loseStatements = {
            "Better luck next time!",
            "Nice try!",
            "Keep trying!",
            "Try and try until you succeed!",
            "Don't give up!",
            "You can do it!",
            "You're almost there!",
            "You're getting closer!",
            "You're on the right track!",
            "You're doing great!",
            "You're doing well!",
            "You're doing fine!",
            "You're doing good!",
            "You're doing okay!",
            "You're doing alright!",
            "Failure is the mother of success!",
            "Failure is the key to success!",
            "Failure is the stepping stone to success!",
        };

        Random random = new Random();
        for (int i = 0; i < loseStatement.length(); i++) {
            int index = random.nextInt(loseStatements.length);
            loseStatement = loseStatements[index];
        }

        return loseStatement;
    }

    public static String randomPraises() {
        String praise = "Lol";

        String[] praises = {
            "Great job!",
            "Well done!",
            "You're amazing!",
            "Fantastic work!",
            "Impressive!",
            "Bravo!",
            "Keep it up!",
            "Outstanding!",
            "Excellent!",
            "You're a rockstar!",
            "Awesome!",
            "Incredible!",
            "Superb!",
            "Terrific!",
            "Way to go!",
            "Spectacular!",
            "Phenomenal!",
            "Marvelous!",
            "Exceptional!",
            "Unbelievable!"
        };

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(praises.length);
            praise = praises[index];
        }

        return praise;
    }

    public static void displayMainMenu() {
        clearConsole();
        System.out.println("Welcome to the Guess the Code Game!\n");
        System.out.println("1. Play Game");
        System.out.println("2. How to Play");
        System.out.println("3. Exit");

        System.out.print("\nSelect: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                displayGameMenu();
                break;
            case 2:
                displayHowToPlay();
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

    public static void displayHowToPlay() {
        clearConsole();
        System.out.println("How to Play:\n");
        System.out.println("1. The computer will generate a secret code consisting of a sequence of numbers.");
        System.out.println("2. The player will have to guess the secret code.");
        System.out.println("3. The player will have a limited number of tries to guess the secret code.");
        System.out.println("4. After each guess, the player will be given feedback on the correctness of the guess.");
        System.out.println("5. The feedback will consist of two numbers:");
        System.out.println("   - The number of digits that are correct and in the correct position.");
        System.out.println("   - The number of digits that are correct but in the wrong position.");
        System.out.println("6. The player wins the game if they are able to guess the secret code within the given number of tries.");
        System.out.println("7. The player loses the game if they are unable to guess the secret code within the given number of tries.");
        System.out.println("8. The player can choose to play the game at different difficulty levels.");
        System.out.println("9. The player can also choose to customize the game by setting the number of digits, the maximum number, and the maximum number of tries.");
        System.out.println("10. The player can view the main menu at any time to start a new game or exit the game.");
        System.out.println("\nPress Enter to go back to the main menu.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayMainMenu();
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
    
    public static void displayWinPage(long time, int tries, Deque<HashMap<int[], int[]>> guesses) {
        clearConsole();
        System.out.println("\n\u001B[32m" + randomPraises() + "\u001B[0m\n");
        System.out.print("The secret code is: ");

        for (int i = 0; i < game.numDigits; i++) {
            System.out.print(game.secretCode[i] + " ");
        }
        System.out.println();

        System.out.println("Time spent: " + convertMsToMinsAndSecs(time));
        System.out.println("Tries: " + tries + "/" + game.maxTries);

        System.out.println("\nGuesses:");
        displayGuesses(guesses);

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

    public static void displayLosePage(Deque<HashMap<int[], int[]>> guesses) {
        clearConsole();
        System.out.println("\u001B[31mYou lose!\u001B[0m\n");
        System.out.println(randomLoseStatement());
        System.out.print("The secret code is: ");
        for (int i = 0; i < game.numDigits; i++) {
            System.out.print(game.secretCode[i] + " ");
        }

        System.out.println("Guesses: ");
        displayGuesses(guesses);
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
        int tries = 1;

        System.out.println("Guess the Passcode!");
        System.out.println("You have " + game.maxTries + " tries to guess the " + game.numDigits + "-digit number.");

        while (tries <= game.maxTries) {
            clearConsole();
            String input;
            String[] inputArr;
            int[] guess;
            HashMap<int[], int[]> guessMap;

            System.out.println("Guess the Secret Code!");
            System.out.println("Round " + tries + "/" + game.maxTries);

            if (tries != 1) {
                System.out.println("\nGuesses: ");
            }
            
            displayGuesses(guesses);

            // Handle win and lose
            if (game.correctCount == game.numDigits) {
                long timeFinished = System.currentTimeMillis() - initialTime;
                displayWinPage(timeFinished, tries, guesses);
                break;
            } else if(tries >= game.maxTries) {
                displayLosePage(guesses);
                break;
            } else {
                System.out.printf("%nEnter a %d space-separated \ninteger that ranges from 1-%d:%n", game.numDigits, game.maxNumber);
                input = sc.nextLine();
                inputArr = input.split(" ");
                guess = new int[game.numDigits];
                for (int i = 0; i < game.numDigits; i++) {
                    guess[i] = Integer.parseInt(inputArr[i]);
                }
                guessMap = new HashMap<int[], int[]>();
                guessMap.put(guess, checkGuess(guess));
                guesses.add(guessMap);
                tries++;
            }

        }
        sc.close();
    }

    public static void main(String[] args) {
        displayMainMenu();
    }
}
