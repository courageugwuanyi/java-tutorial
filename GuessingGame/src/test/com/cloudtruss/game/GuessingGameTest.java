package com.cloudtruss.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GuessingGameTest {

    public static final int GAME_RANDOMNESS_RETRIES = 100;
    public static final int RANDOM_NUMBER_COUNT = 11;
    private GuessingGame game;

    @BeforeEach
    public void setup() {
        game = new GuessingGame();
    }

    @Test
    public void testSimpleWinSituation() {
        int randomNumber = game.getRandomNumber();
        String message = game.guess(randomNumber);
        assertEquals("You guessed it!", message);
    }

    @Test
    public void testOneWrongNegativeGuessSituation() {
        String message = game.guess(-5);
        assertEquals("You did not guess it!", message);
    }

    @Test
    public void testOneWrongGuessPositiveSituation() {
        int randomNumber = game.getRandomNumber();
        String message = game.guess(randomNumber + 1);
        assertEquals("You did not guess it!", message);
    }

    @Test
    public void testFourWrongNegativeGuessSituation() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        String message = game.guess(-3);
        assertEquals("You did not guess it and you have 4 tries. Game Over!", message);
    }

    @Test
    public void testThreeWrongNegativeGuessAndOneCorrectGuessSituation() {
        game.guess(-3);
        game.guess(-3);
        game.guess(-3);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You guessed it!", message);
    }

    // @RepeatedTest(100)
    @Test
    public void testRandomNumberGeneration() {
        int[] randNumbCount = new int[11];
        for (int counter = 0; counter < GAME_RANDOMNESS_RETRIES; counter++) {
            GuessingGame game = new GuessingGame();
            int randomNumber = game.getRandomNumber();
            randNumbCount[randomNumber] = 1;
        }
        System.out.println(Arrays.toString(randNumbCount));

        int sum = 0;
        for (int counter = 0; counter < RANDOM_NUMBER_COUNT; counter++) {
            sum += randNumbCount[counter];
        }
        assertEquals(10, sum);
    }

    @Test
    public void junkTest() {
        // without an Assertion that is no test
    }
}

