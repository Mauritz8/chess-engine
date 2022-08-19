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

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = start.getY() - end.getY();

        // consider en passant aswell
        boolean canMoveStraightUp;
        boolean canCapture;
        if (this.isWhite()) {
            if (start.getY() == 6) {
                canMoveStraightUp = deltaX == 0 && ( (deltaY == 1 && end.getPiece() == null) ||
                        (deltaY == 2 && end.getPiece() == null && board.getSquare(end.getX(), end.getY() + 1).getPiece() == null) );
            } else {
                canMoveStraightUp = deltaX == 0 && deltaY == 1 && end.getPiece() == null;
            }
            canCapture = deltaX == 1 && deltaY == 1 && end.getPiece() != null && end.getPiece().isWhite() != this.isWhite();
        } else {
            if (start.getY() == 1) {
                canMoveStraightUp = deltaX == 0 && ( (deltaY == -1 && end.getPiece() == null) ||
                        (deltaY == -2 && end.getPiece() == null && board.getSquare(end.getX(), end.getY() - 1).getPiece() == null) );
            } else {
                canMoveStraightUp = deltaX == 0 && deltaY == -1 && end.getPiece() == null;
            }
            canCapture = deltaX == 1 && deltaY == -1 && end.getPiece() != null && end.getPiece().isWhite() != this.isWhite();
        }

        if (canMoveStraightUp || canCapture) {
            return true;
        }

        return false;
    }

    @Override
    public List<Move> legalMoves(Game game, Square currentSquare) {
        Board board = game.getBoard();
        Player player = game.getPlayerToMove();

        List<Move> moves = new ArrayList<>();

        Square[] newSquares = new Square[4];
        if (player.isWhiteSide()) {
            newSquares[0] = board.getSquare(currentSquare.getX(), currentSquare.getY() - 1);
            newSquares[1] = board.getSquare(currentSquare.getX(), currentSquare.getY() - 2);
            newSquares[2] = board.getSquare(currentSquare.getX() - 1, currentSquare.getY() - 1);
            newSquares[3] = board.getSquare(currentSquare.getX() + 1, currentSquare.getY() - 1);
        } else {
            newSquares[0] = board.getSquare(currentSquare.getX(), currentSquare.getY() + 1);
            newSquares[1] = board.getSquare(currentSquare.getX(), currentSquare.getY() + 2);
            newSquares[2] = board.getSquare(currentSquare.getX() - 1, currentSquare.getY() + 1);
            newSquares[3] = board.getSquare(currentSquare.getX() + 1, currentSquare.getY() + 1);
        }



        for (Square newSquare : newSquares) {
            if (newSquare != null) {
                Move move = new Move(player, currentSquare, newSquare);
                if (move.isLegal(game)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }
}
