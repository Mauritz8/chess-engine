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
        Square newSquare1;
        Square newSquare2;
        Square newSquare3;
        Square newSquare4;

        if (player.isWhiteSide()) {
            newSquare1 = board.getSquare(currentSquare.getX(), currentSquare.getY() - 1);
            newSquare2 = board.getSquare(currentSquare.getX(), currentSquare.getY() - 2);
            newSquare3 = board.getSquare(currentSquare.getX() - 1, currentSquare.getY() - 1);
            newSquare4 = board.getSquare(currentSquare.getX() + 1, currentSquare.getY() - 1);
        } else {
            newSquare1 = board.getSquare(currentSquare.getX(), currentSquare.getY() + 1);
            newSquare2 = board.getSquare(currentSquare.getX(), currentSquare.getY() + 2);
            newSquare3 = board.getSquare(currentSquare.getX() - 1, currentSquare.getY() + 1);
            newSquare4 = board.getSquare(currentSquare.getX() + 1, currentSquare.getY() + 1);
        }


        if (newSquare1 != null) {
            if (newSquare1.getPiece() == null) {
                moves.add(new Move(player, currentSquare, newSquare1));
            }
        }

        if (newSquare2 != null) {
            if ( (currentSquare.getY() == 6 && player.isWhiteSide()) || (currentSquare.getY() == 1 && !player.isWhiteSide()) ) {
                if (newSquare2.getPiece() == null) {
                    moves.add(new Move(player, currentSquare, newSquare2));
                }
            }
        }

        if (newSquare3 != null) {
            if (newSquare3.getPiece() != null && newSquare3.getPiece().isWhite() != player.isWhiteSide()) {
                Move move = new Move(player, currentSquare, newSquare3);
                newSquare3.getPiece().setKilled(true);
                move.setPieceKilled(newSquare3.getPiece());
                moves.add(move);
            }
        }

        if (newSquare4 != null) {
            if (newSquare4.getPiece() != null && newSquare4.getPiece().isWhite() != player.isWhiteSide()) {
                Move move = new Move(player, currentSquare, newSquare4);
                newSquare4.getPiece().setKilled(true);
                move.setPieceKilled(newSquare4.getPiece());
                moves.add(move);
            }
        }

        return moves;
    }
}
