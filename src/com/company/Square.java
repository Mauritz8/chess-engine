package com.company;

public class Square {

    private int y;
    private int x;
    private Piece piece;
    private String algebraicNotation;

    public Square(int y, int x, Piece piece, String algebraicNotation) {
        this.y = y;
        this.x = x;
        this.piece = piece;
        this.algebraicNotation = algebraicNotation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getAlgebraicNotation() {
        return algebraicNotation;
    }
}
