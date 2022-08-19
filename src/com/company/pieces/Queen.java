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
    public List<Move> legalMoves(Game game, Square currentSquare) {
        Board board = game.getBoard();
        List<Move> moves = new ArrayList<>();
        List<Move> movesUp = legalMovesOneDirection(game, currentSquare, board.squaresUp(currentSquare));
        List<Move> movesDown = legalMovesOneDirection(game, currentSquare, board.squaresDown(currentSquare));
        List<Move> movesRight = legalMovesOneDirection(game, currentSquare, board.squaresRight(currentSquare));
        List<Move> movesLeft = legalMovesOneDirection(game, currentSquare, board.squaresLeft(currentSquare));
        List<Move> movesUpRight = legalMovesOneDirection(game, currentSquare, board.squaresUpRight(currentSquare));
        List<Move> movesDownRight = legalMovesOneDirection(game, currentSquare, board.squaresDownRight(currentSquare));
        List<Move> movesUpLeft = legalMovesOneDirection(game, currentSquare, board.squaresUpLeft(currentSquare));
        List<Move> movesDownLeft = legalMovesOneDirection(game, currentSquare, board.squaresDownLeft(currentSquare));
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
