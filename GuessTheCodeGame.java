import java.util.Scanner;
import java.util.Random;

class Game {
    static int numDigits;
    static int maxTries;
    static int maxNumber;
    static int secretCode[] = new int[numDigits];

    //put random numbers in secretCode ranging from 1 to maxNumber
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

public class GuessTheCodeGame {
    public static void main(String[] args) {
        
    }
}
