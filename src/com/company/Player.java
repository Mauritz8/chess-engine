package com.company;

import com.company.pieces.King;

import java.util.List;

public class Player {
    private String name;
    private boolean whiteSide;

    public Player(String name, boolean whiteSide) {
        this.name = name;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() {
        return whiteSide;
    }

    /*public boolean isInCheck(Board board, Player opponent) {
        King king = (King) board.getPiecesForColor(isWhiteSide()).stream().filter(p -> p instanceof King).findFirst().get();
        List<Piece> opponentsPieces = board.getPiecesForColor(opponent.isWhiteSide());
        for (Piece piece : opponentsPieces) {
            for (Move move : piece.legalMoves(board, piece.findSquare(board), opponent)) {
                if (move.getEnd() == king.findSquare(board)) {
                    return true;
                }
            }
        }
        return false;
    }*/
}
