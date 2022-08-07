package com.company;

public class Move {
    private Player player;
    private Square start;
    private Square end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Player player, Square start, Square end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public Player getPlayer() {
        return player;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public void setPieceKilled(Piece pieceKilled) {
        this.pieceKilled = pieceKilled;
    }
}
