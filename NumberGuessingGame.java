import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalScore = 0;

        System.out.println("=================================================");
        System.out.println("        WELCOME TO THE NUMBER GUESSING GAME");
        System.out.println("=================================================");
        System.out.println("I'm thinking of a number between " + LOWER_BOUND
                + " and " + UPPER_BOUND + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts per round. Good luck!\n");

        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed++;
            int targetNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("----- Round " + roundsPlayed + " -----");

            while (attemptsUsed < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Attempt " + (attemptsUsed + 1) + "/" + MAX_ATTEMPTS
                        + " - Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("That's not a valid number. Please try again.");
                    scanner.next(); // discard invalid token
                    continue;
                }

                int guess = scanner.nextInt();
                attemptsUsed++;

                if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                    System.out.println("Please guess a number within the range "
                            + LOWER_BOUND + " to " + UPPER_BOUND + ".");
                } else if (guess == targetNumber) {
                    guessedCorrectly = true;
                    int pointsEarned = Math.max(MAX_ATTEMPTS - attemptsUsed + 1, 1) * 10;
                    totalScore += pointsEarned;
                    roundsWon++;
                    System.out.println("Correct! You guessed it in " + attemptsUsed
                            + " attempt(s). You earned " + pointsEarned + " points!");
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The number was: " + targetNumber);
            }

            System.out.println("Current Score: " + totalScore
                    + " | Rounds Won: " + roundsWon + "/" + roundsPlayed);

            System.out.print("\nDo you want to play another round? (y/n): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("y") || response.equals("yes");
            System.out.println();
        }

        System.out.println("=================================================");
        System.out.println("                  GAME OVER");
        System.out.println("=================================================");
        System.out.println("Rounds Played : " + roundsPlayed);
        System.out.println("Rounds Won    : " + roundsWon);
        System.out.println("Final Score   : " + totalScore);
        System.out.println("Thanks for playing! Goodbye.");

        scanner.close();
    }
}
