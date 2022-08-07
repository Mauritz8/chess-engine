package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player[] players = new Player[2];
    private Board board;
    private Player playerToMove;
    private GameStatus status = GameStatus.ACTIVE;
    private List<Move> movesPlayed = new ArrayList<>();

    public void initialize(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;

        board = new Board();

        if (player1.isWhiteSide()) {
            playerToMove = player1;
        } else {
            playerToMove = player2;
        }

        movesPlayed.clear();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerToMove() {
        return playerToMove;
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean gameEnded() {
        return status != GameStatus.ACTIVE;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean makeMove(Player player, Move move) {
        Piece piece = move.getStart().getPiece();

        if (piece == null) {
            return false;
        }

        if (player != playerToMove) {
            return false;
        }

        if (piece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!piece.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        Piece pieceAtEndSquare = move.getEnd().getPiece();
        if (pieceAtEndSquare != null) {
            pieceAtEndSquare.setKilled(true);
            move.setPieceKilled(pieceAtEndSquare);
        }

        movesPlayed.add(move);
        move.getEnd().setPiece(piece);
        move.getStart().setPiece(null);

        if (playerToMove == players[0]) {
            playerToMove = players[1];
        } else {
            playerToMove = players[0];
        }

        return true;
    }

    public List<Move> getAllLegalMoves() {
        return board.getLegalMoves(board.getAllSquaresWithPieces(), playerToMove);
    }
}
