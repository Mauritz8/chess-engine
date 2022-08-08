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
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        List<Move> movesUp = super.legalMovesOneDirection(currentSquare, player, board.squaresUp(currentSquare));
        List<Move> movesDown = super.legalMovesOneDirection(currentSquare, player, board.squaresDown(currentSquare));
        List<Move> movesRight = super.legalMovesOneDirection(currentSquare, player, board.squaresRight(currentSquare));
        List<Move> movesLeft = super.legalMovesOneDirection(currentSquare, player, board.squaresLeft(currentSquare));
        moves.addAll(movesUp);
        moves.addAll(movesDown);
        moves.addAll(movesRight);
        moves.addAll(movesLeft);
        return moves;
    }
}
