package com.cloudtruss.chessboard.game;

public class ChessPieceTest {
    protected boolean isMoveFoundInArray(Coordinates[] moves, Coordinates expectedMove) {
        for (Coordinates move : moves) {
            if (move.equals(expectedMove)) return true;
        }
        return false;
    }
}
