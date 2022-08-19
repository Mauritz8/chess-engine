package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private boolean killed = false;
    private boolean isWhite;
    private char charRepresentation;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public char getCharRepresentation() {
        return charRepresentation;
    }

    public void setCharRepresentation(char charRepresentation) {
        this.charRepresentation = charRepresentation;
    }

    public Square findSquare(Board board) {
        for (int y = 0; y < board.getSquares().length; y++)  {
            for (int x = 0; x < board.getSquares().length; x++) {
                Square square = board.getSquare(x, y);
                if (square.getPiece() == this) {
                    return square;
                }
            }
        }
        return null;
    }

    public abstract boolean canMove(Board board, Square start, Square end);
    public abstract List<Move> legalMoves(Game game, Square currentSquare);


    // legal moves in a specific direction for the pieces: bishop, rook, queen.
    public List<Move> legalMovesOneDirection(Game game, Square currentSquare, List<Square> squaresInDirection) {
        Player player = game.getPlayerToMove();
        List<Move> moves = new ArrayList<>();
        for (Square endSquare : squaresInDirection) {
            Move move = new Move(player, currentSquare, endSquare);
            if (move.isLegal(game)) {
                moves.add(move);
            } else {
                return moves;
            }
        }
        return moves;
    }

}
