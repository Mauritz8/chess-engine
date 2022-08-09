package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean hasKingsideCastlingRight = true;
    private boolean hasQueensideCastlingRight = true;

    public King(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('K');
    }

    public boolean hasKingsideCastlingRight() {
        return hasKingsideCastlingRight;
    }

    public boolean hasQueensideCastlingRight() {
        return hasQueensideCastlingRight;
    }

    public void setHasKingsideCastlingRight(boolean hasKingsideCastlingRight) {
        this.hasKingsideCastlingRight = hasKingsideCastlingRight;
    }

    public void setHasQueensideCastlingRight(boolean hasQueensideCastlingRight) {
        this.hasQueensideCastlingRight = hasQueensideCastlingRight;
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        if (isValidCastlingKingside(board, start, end)) {
            return true;
        }

        if (isValidCastlingQueenside(board, start, end)) {
            return true;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if (deltaX + deltaY == 1) {
            // also check if square is attacked by opponents piece, if so return false
            return true;
        }

        return false;
    }

    public boolean isValidCastlingKingside(Board board, Square start, Square end) {
        if (isKingSideCastlingMove(start, end) && canCastleKingside(board, start)) {
            return true;
        }

        return false;
    }

    public boolean isValidCastlingQueenside(Board board, Square start, Square end) {
        if (isQueenSideCastlingMove(start, end) && canCastleQueenside(board, start)) {
            return true;
        }

        return false;
    }

    // todo: also check if the opponent has a piece that is disrupting the right to castle
    private boolean canCastleKingside(Board board, Square king) {
        if (hasKingsideCastlingRight) {
            Square oneRightFromKing = board.getSquare(king.getX() + 1, king.getY());
            Square twoRightFromKing = board.getSquare(king.getX() + 2, king.getY());
            if (oneRightFromKing.getPiece() == null && twoRightFromKing.getPiece() == null) {
                return true;
            }
        }
        return false;
    }

    // todo: also check if the opponent has a piece that is disrupting the right to castle
    private boolean canCastleQueenside(Board board, Square king) {
        if (hasQueensideCastlingRight) {
            Square oneLeftFromKing = board.getSquare(king.getX() - 1, king.getY());
            Square twoLeftFromKing = board.getSquare(king.getX() - 2, king.getY());
            if (oneLeftFromKing.getPiece() == null && twoLeftFromKing.getPiece() == null) {
                return true;
            }
        }
        return false;
    }

    public boolean isKingSideCastlingMove(Square start, Square end) {
        if (isWhite()) {
            if (start.getX() == 4 && end.getX() == 6 && start.getY() == 7 && end.getY() == 7) {
                return true;
            }
        } else {
            if (start.getX() == 4 && end.getX() == 6 && start.getY() == 0 && end.getY() == 0) {
                return true;
            }
        }

        return false;
    }

    public boolean isQueenSideCastlingMove(Square start, Square end) {
        if (isWhite()) {
            if (start.getX() == 4 && end.getX() == 2 && start.getY() == 7 && end.getY() == 7) {
                return true;
            }
        } else {
            if (start.getX() == 4 && end.getX() == 2 && start.getY() == 0 && end.getY() == 0) {
                return true;
            }
        }

        return false;
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

        // castling moves
        Square newSquareKingsideCastle = board.getSquare(currentSquare.getX() + 2, currentSquare.getY());
        if (isValidCastlingKingside(board, currentSquare, newSquareKingsideCastle)) {
            moves.add(new Move(player, currentSquare, newSquareKingsideCastle));
        }
        Square newSquareQueensideCastle = board.getSquare(currentSquare.getX() - 2, currentSquare.getY());
        if (isValidCastlingQueenside(board, currentSquare, newSquareQueensideCastle)) {
            moves.add(new Move(player, currentSquare, newSquareQueensideCastle));
        }

        return moves;
    }
}
