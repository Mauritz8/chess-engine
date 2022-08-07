package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('P');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        int deltaX = start.getX() - end.getX();
        int deltaY = start.getY() - end.getY();

        // consider en passant aswell
        boolean canMoveStraightUp;
        boolean canCapture;
        if (this.isWhite()) {
            if (start.getY() == 6) {
                canMoveStraightUp = deltaX == 0 && (deltaY == 1 || deltaY == 2) && end.getPiece() == null;
            } else {
                canMoveStraightUp = deltaX == 0 && deltaY == 1 && end.getPiece() == null;
            }
            canCapture = deltaX == 1 && deltaY == 1 && end.getPiece() != null && end.getPiece().isWhite() != this.isWhite();
        } else {
            if (start.getY() == 1) {
                canMoveStraightUp = deltaX == 0 && (deltaY == -1 || deltaY == -2) && end.getPiece() == null;
            } else {
                canMoveStraightUp = deltaX == 0 && deltaY == -1 && end.getPiece() == null;
            }
            canCapture = deltaX == -1 && deltaY == -1 && end.getPiece().isWhite() != this.isWhite();
        }

        if (canMoveStraightUp || canCapture) {
            return true;
        }

        return false;
    }

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        return moves;
    }
}
