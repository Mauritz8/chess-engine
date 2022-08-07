package com.company.pieces;

import com.company.Board;
import com.company.Piece;
import com.company.Square;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('B');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if (deltaX == deltaY) {
            return true;
        }

        return false;
    }
}
