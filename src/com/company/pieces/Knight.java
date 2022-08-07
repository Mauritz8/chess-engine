package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('N');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        return deltaX * deltaY == 2;
    }

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        // All possible moves of a knight
        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < dx.length; i++) {
            int x = currentSquare.getX() + dx[i];
            int y = currentSquare.getY() + dy[i];

            Square newSquare = board.getSquare(x, y);
            if (newSquare != null) { // square is inside board
                if (newSquare.getPiece() != null) { // is there already a piece on the new square
                    if (newSquare.getPiece().isWhite() != player.isWhiteSide()) { // you can't move to a square with a piece of you're color already on it
                        moves.add(new Move(player, currentSquare, newSquare));
                    }
                } else {
                    moves.add(new Move(player, currentSquare, newSquare));
                }
            }
        }

        return moves;
    }
}
