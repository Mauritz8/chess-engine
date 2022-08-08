package com.company.pieces;

import com.company.*;

import java.util.*;

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

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        List<Move> movesUpRight = super.legalMovesOneDirection(currentSquare, player, board.squaresUpRight(currentSquare));
        List<Move> movesDownRight = super.legalMovesOneDirection(currentSquare, player, board.squaresDownRight(currentSquare));
        List<Move> movesUpLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresUpLeft(currentSquare));
        List<Move> movesDownLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresDownLeft(currentSquare));
        moves.addAll(movesUpRight);
        moves.addAll(movesDownRight);
        moves.addAll(movesUpLeft);
        moves.addAll(movesDownLeft);
        return moves;
    }

}
