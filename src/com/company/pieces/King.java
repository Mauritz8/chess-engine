package com.company.pieces;

import com.company.Board;
import com.company.Piece;
import com.company.Square;

public class King extends Piece {

    private boolean castlingDone = false;

    public King(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('K');
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        // moves differently when castling
        if (isCastlingMove(start, end)) {
            return true;
        }

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if (deltaX + deltaY == 1) {
            // also check if square is attacked by opponents piece, if so return false
            return true;
        }

        return false;
    }

    private boolean isValidCastling(Board board, Square start, Square end) {
        if (this.isCastlingDone()) {
            return false;
        }

        // also check if rook has moved, or if the opponent has a piece that is disrupting the right to castle
        return true;
    }

    public boolean isCastlingMove(Square start, Square end) {
        // check if the starting and
        // ending position are correct
        return true;
    }
}
