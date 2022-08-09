package com.company;

public class Square {

    private int y;
    private int x;
    private Piece piece;
    private String algebraicNotation;

    public Square(int y, int x, Piece piece) {
        this.y = y;
        this.x = x;
        this.piece = piece;
        setAlgebraicNotation(y, x);
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

    public void setAlgebraicNotation(int y, int x) {
        int[] yNotations = {8, 7, 6, 5, 4, 3, 2, 1};
        char[] xNotations = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        algebraicNotation = new StringBuilder().append(xNotations[x]).append(yNotations[y]).toString();
    }
}
