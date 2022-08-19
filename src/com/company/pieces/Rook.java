package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('R');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if ( (deltaX > 0 && deltaY == 0) || (deltaX == 0 && deltaY > 0) ) {
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
        moves.addAll(movesUp);
        moves.addAll(movesDown);
        moves.addAll(movesRight);
        moves.addAll(movesLeft);
        return moves;
    }
}
