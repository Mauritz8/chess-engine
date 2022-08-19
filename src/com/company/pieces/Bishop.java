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
    public List<Move> legalMoves(Game game, Square currentSquare) {
        Board board = game.getBoard();
        List<Move> moves = new ArrayList<>();
        List<Move> movesUpRight = legalMovesOneDirection(game, currentSquare, board.squaresUpRight(currentSquare));
        List<Move> movesDownRight = legalMovesOneDirection(game, currentSquare, board.squaresDownRight(currentSquare));
        List<Move> movesUpLeft = legalMovesOneDirection(game, currentSquare, board.squaresUpLeft(currentSquare));
        List<Move> movesDownLeft = legalMovesOneDirection(game, currentSquare, board.squaresDownLeft(currentSquare));
        moves.addAll(movesUpRight);
        moves.addAll(movesDownRight);
        moves.addAll(movesUpLeft);
        moves.addAll(movesDownLeft);
        return moves;
    }

}
