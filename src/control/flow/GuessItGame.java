package control.flow;

import java.util.Random;
import java.util.Scanner;

public class GuessItGame {
    // Lightweight Class
    record Person(String firstName, String lastName, int age) {
    }

    public static void main(String[] args) {
        int randomNumber = new Random().nextInt(10) + 1;
        Scanner scanner = new Scanner(System.in);
        boolean isGuessed = false;
        String guessedNumberText = "";
        int wrongGuessCount = 0;

        while (!isGuessed) {
            if (wrongGuessCount >= 4 ) {
                System.out.println(STR."You have guessed the wrong number \{wrongGuessCount}  times.. Game over!");
                break;
            }
            System.out.print("Enter your guess (1 - 10): ");
            guessedNumberText = scanner.nextLine();

            if (guessedNumberText.matches("(?!Quit\\\\b)(?!quit\\\\b)[A-PR-Za-pr-z \\s]+")  || guessedNumberText.isBlank()){
                continue;
            } else if (guessedNumberText.trim().matches("([Qq])?(Quit)?(quit)?")) { break; }

            int guessedNumber = Integer.parseInt(guessedNumberText);
            if (randomNumber == guessedNumber) {
                System.out.println("You guessed it correctly!");
                isGuessed = true;
            } else {
                System.out.println(STR."The number you guessed is incorrect! The generated number is \{randomNumber} \n");
                wrongGuessCount++;
            }
        }
    }
}
