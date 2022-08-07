package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean castlingDone = false;

    public King(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('K');
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        // moves differently when castling
        if (isCastlingMove(start, end)) {
            return true;
        }

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if (deltaX + deltaY == 1) {
            // also check if square is attacked by opponents piece, if so return false
            return true;
        }

        return false;
    }

    private boolean isValidCastling(Board board, Square start, Square end) {
        if (this.isCastlingDone()) {
            return false;
        }

        // also check if rook has moved, or if the opponent has a piece that is disrupting the right to castle
        return true;
    }

    public boolean isCastlingMove(Square start, Square end) {
        // check if the starting and
        // ending position are correct
        return true;
    }

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves  = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    int x = currentSquare.getX() + dx;
                    int y = currentSquare.getY() + dy;
                    Square newSquare = board.getSquare(x, y);
                    if (newSquare != null) {
                        if (newSquare.getPiece() != null) {
                            if (newSquare.getPiece().isWhite() != player.isWhiteSide()) {
                                moves.add(new Move(player, currentSquare, newSquare));
                            }
                        } else {
                            moves.add(new Move(player, currentSquare, newSquare));
                        }
                    }
                }
            }
        }
        return moves;
    }
}
