package com.cloudtruss.blackjack;

public enum Rank {
    // The Enums are actually constructors of the enum class.
    // They are constructors because each is capable of taking in values to create an instance of the enum class
    // E.g to create an instance of RANK with an enum object named ACE, then you would have:
    // Rank ACE = new RANK("ACE")
    // The ACE object is like a constant; that is why it is all caps. The Object is inherently initialized with a Sting param "ACE"
    // The position of occupied by each enum on this list is the ordinal value (OV). The OV starts from 0. Think of index value.
    ACE(1),
    TWO(2), // Rank TWO= new Rank("TWO");
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10); // Rank KING = new Rank("KING");

    private int value;

    // custom constructor to allow us set data
    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
        // The commented code below was the code used when there was no custom constructor
//        switch (this) {
//            case JACK:
//            case QUEEN:
//            case KING: return 10;
//            default: return this.ordinal() + 1;
//        }
    }
}