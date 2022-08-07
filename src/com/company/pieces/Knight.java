package com.company.pieces;

import com.company.Board;
import com.company.Piece;
import com.company.Square;

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
}
