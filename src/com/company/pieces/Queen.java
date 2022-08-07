package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('Q');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());

        boolean canMoveStraight = (deltaX > 0 && deltaY == 0) || (deltaX == 0 && deltaY > 0);
        boolean canMoveDiagonally = deltaX == deltaY;

        if (canMoveStraight || canMoveDiagonally) {
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
