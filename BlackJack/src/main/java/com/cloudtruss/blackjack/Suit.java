package com.cloudtruss.blackjack;

public enum Suit {
    CLUBS('\u2663'),
    DIAMONDS('\u2666'),
    HEARTS('\u2665'),
    SPADES('\u2666');

    private char symbol;

    Suit (char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}

/**
 * Enums are a special type of class
 * Used to rep a finite set of values that are related e.g days of the week
 * They can have constructors, fields, properties
 * The properties can be associated with the consts of the enum
 * Constants are just instances of the enum class
 * To create an Enum you use the keyword "enum"
 * We can use enums in if and switch statements
 * Enums has an index called by the ordinals()
 * We can move from a string to one of the constants of an Enum by using the valueOf().
 *
 * */