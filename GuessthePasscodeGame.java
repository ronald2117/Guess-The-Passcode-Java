import java.util.ArrayList;
import java.util.HashMap;

public class GuessthePasscodeGame {
    class Game {
        Game(int numDigits, int maxTries, int maxNumber)
        private int numDigits;
        private int maxTries;
        private int maxNumber;
        private int passcode[];
        private int correctCount;

        public int getNumDigits()
        public int getMaxTries()
        public int getMaxNumber()
        public int[] getPasscode()
        public int getCorrectCount()
        public void setNumDigits(int numDigits)
    };
    static Game game;

    //utilities
    public static void clearConsole()
    public static String formatTime(long time)
    public static String randomLoseStatement()
    public static String randomPraises()
    public static void setPasscode()

    //displays
    public static void displayMainMenu()
    public static void displayGameMenu()
    public static void displayGame(Game game)
    public static void displayWinPage(long time, int tries, ArrayList<HashMap<int[], int[]>> guesses)
    public static void displayLosePage(ArrayList<HashMap<int[], int[]>> guesses)

    //gameplay
    public static int[] checkGuess(int[] guess)
    public static void displayGuesses(ArrayList<HashMap<int[], int[]>> guesses)
    public static void main(String[] args)
}
