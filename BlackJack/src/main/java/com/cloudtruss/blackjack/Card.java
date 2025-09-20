package com.cloudtruss.blackjack;

// shift + cmd + T = Takes you to the Test Class of SUT
public class Card {
    private Suit suit; // shift + cmd + F6 = Type Migration
    private Rank rank;

    // cmd + N, generate constructor and getters and setters
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String suit, String rank) {
         this.suit = Suit.valueOf(suit.toUpperCase());
         this.rank = Rank.valueOf(rank.toUpperCase());
    }


    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public static void main(String[] args) {
        Card card1 = new Card(Suit.CLUBS, Rank.JACK);
        Card card2 = new Card(Suit.DIAMONDS, Rank.TEN);
        Card card3 = new Card("hearts", "queen");
        // int total = card1.plus(card2);
        // Hand myHand = new Hand();
        // myHand.add(card1);
        // myHand.add(card2);
        // int total = myHand.getTotal();
        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
    }

    public int getValue() {
        return this.rank.getValue();
    }

    // Ctrl + O shows you every possible method in the superclass that can be overridden
    @Override
    public String toString() {
        // JACK {Club symbol}
        return this.rank.toString().concat("-") +  this.suit;
    }
}

/*

* */
