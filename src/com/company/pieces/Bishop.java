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

    private List<Square> squaresUpRight(Board board, Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            int dy = dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = board.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    private List<Square> squaresDownRight(Board board, Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            int dy = -dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = board.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    private List<Square> squaresUpLeft(Board board, Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = -1; dx > -8; dx--) {
            int dy = dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = board.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    private List<Square> squaresDownLeft(Board board, Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = -1; dx > -8; dx--) {
            int dy = -dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = board.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }


    private List<Move> legalMovesOneDirection(Square currentSquare, Player player, List<Square> squaresInDirection) {
        List<Move> moves = new ArrayList<>();
        for (Square endSquare : squaresInDirection) {
            if (endSquare != null && (endSquare.getPiece() == null || endSquare.getPiece().isWhite() != player.isWhiteSide())) {
                Move move = new Move(player, currentSquare, endSquare);
                moves.add(move);

                // can't go through enemy piece
                if (endSquare.getPiece() != null && endSquare.getPiece().isWhite() != player.isWhiteSide()) {
                    move.setPieceKilled(move.getEnd().getPiece());
                    return moves;
                }
            } else {
                return moves;
            }
        }
        return moves;
    }

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        List<Move> movesUpRight = legalMovesOneDirection(currentSquare, player, squaresUpRight(board, currentSquare));
        List<Move> movesDownRight = legalMovesOneDirection(currentSquare, player, squaresDownRight(board, currentSquare));
        List<Move> movesUpLeft = legalMovesOneDirection(currentSquare, player, squaresUpLeft(board, currentSquare));
        List<Move> movesDownLeft = legalMovesOneDirection(currentSquare, player, squaresDownLeft(board, currentSquare));
        moves.addAll(movesUpRight);
        moves.addAll(movesDownRight);
        moves.addAll(movesUpLeft);
        moves.addAll(movesDownLeft);
        return moves;
    }

}
