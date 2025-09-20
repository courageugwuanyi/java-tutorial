package com.cloudtruss.game;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private int randomNumber;
    private int guessCount;

    public GuessingGame() {
        this.randomNumber = new Random().nextInt(10) + 1;
    }

    public String guess( int guessedNumber) {
        this.guessCount++;
        return this.getRandomNumber() == guessedNumber ? "You guessed it!" : this.guessCount >= 4
                ? STR."You did not guess it and you have \{this.guessCount} tries!" :"You did not guess it!";
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Enter your guess: ");
            userInput = scanner.nextLine();
            if ("q".equals(userInput)) break;
            System.out.println(game.guess(Integer.parseInt(userInput)));
            System.out.println(game.getRandomNumber());
        } while (!"q".equals(userInput));


    }
}
