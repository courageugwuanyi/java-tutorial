package com.cloudtruss.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void getValueOfKing() {
        // Rank KING = new Rank("KING");
        assertEquals(10, Rank.KING.getValue());
    }
}