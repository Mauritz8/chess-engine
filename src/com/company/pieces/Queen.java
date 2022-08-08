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
        List<Move> movesUp = super.legalMovesOneDirection(currentSquare, player, board.squaresUp(currentSquare));
        List<Move> movesDown = super.legalMovesOneDirection(currentSquare, player, board.squaresDown(currentSquare));
        List<Move> movesRight = super.legalMovesOneDirection(currentSquare, player, board.squaresRight(currentSquare));
        List<Move> movesLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresLeft(currentSquare));
        List<Move> movesUpRight = super.legalMovesOneDirection(currentSquare, player, board.squaresUpRight(currentSquare));
        List<Move> movesDownRight = super.legalMovesOneDirection(currentSquare, player, board.squaresDownRight(currentSquare));
        List<Move> movesUpLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresUpLeft(currentSquare));
        List<Move> movesDownLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresDownLeft(currentSquare));
        moves.addAll(movesUp);
        moves.addAll(movesDown);
        moves.addAll(movesRight);
        moves.addAll(movesLeft);
        moves.addAll(movesUpRight);
        moves.addAll(movesDownRight);
        moves.addAll(movesUpLeft);
        moves.addAll(movesDownLeft);
        return moves;
    }
}
