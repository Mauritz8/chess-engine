package com.company;

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

    public abstract boolean canMove(Board board, Square start, Square end);
}
