package com.company;

import com.company.pieces.King;
import com.company.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void makeMove(Move move) {
        Player player = move.getPlayer();
        Piece piece = move.getStart().getPiece();

        if (move.pieceWasKilled()) {
            move.getEnd().getPiece().setKilled(true);
        }

        // check if rook moved from starting square,
        // if it did set the corresponding castling right to false (kingside or queenside)
        if (piece instanceof Rook) {
            if (piece.isWhite()) {
                King whiteKing = (King) getBoard().getPiecesForColor(true).stream()
                        .filter(p -> p.getClass().isAssignableFrom(King.class))
                        .collect(Collectors.toList()).get(0);
                if (move.getStart().getX() == 7 && move.getStart().getY() == 7) {
                    whiteKing.setHasKingsideCastlingRight(false);
                } else if (move.getStart().getX() == 0 && move.getStart().getY() == 7) {
                    whiteKing.setHasQueensideCastlingRight(false);
                }
            } else {
                King blackKing = (King) getBoard().getPiecesForColor(false).stream()
                        .filter(p -> p.getClass().isAssignableFrom(King.class))
                        .collect(Collectors.toList()).get(0);
                if (move.getStart().getX() == 7 && move.getStart().getY() == 0) {
                    blackKing.setHasKingsideCastlingRight(false);
                } else if (move.getStart().getX() == 0 && move.getStart().getY() == 0) {
                    blackKing.setHasQueensideCastlingRight(false);
                }
            }
        }

        // checks if it's a castling move, if it is it also moves the rook
        if (piece instanceof King) {
            King king = (King) piece;
            if (king.isValidCastlingKingside(board, move.getStart(), move.getEnd())) {
                makeKingsideCastleRookMove(king, player);
            } else if (king.isValidCastlingQueenside(board, move.getStart(), move.getEnd())) {
                makeQueensideCastleRookMove(king, player);
            }
            king.setHasKingsideCastlingRight(false);
            king.setHasQueensideCastlingRight(false);
        }
        movesPlayed.add(move);
        move.getEnd().setPiece(piece);
        move.getStart().setPiece(null);


        if (playerToMove == players[0]) {
            playerToMove = players[1];
        } else {
            playerToMove = players[0];
        }
    }

    public void makeKingsideCastleRookMove(King king, Player player) {
        Square kingStart = king.findSquare(board);
        Square rookStart = board.getSquare(kingStart.getX() + 3, kingStart.getY());
        Square rookEnd =  board.getSquare(rookStart.getX() - 2, rookStart.getY());
        Rook rook = (Rook) rookStart.getPiece();
        Move rookMove = new Move(player, rookStart, rookEnd);

        movesPlayed.add(rookMove);
        rookMove.getEnd().setPiece(rook);
        rookMove.getStart().setPiece(null);
    }

    /*public void undoKingsideCastleRookMove(King king, Player player) {
        Square kingStart = king.findSquare(board);
        Square rookStart = board.getSquare(kingStart.getX() + 3, kingStart.getY());
        Square rookEnd =  board.getSquare(rookStart.getX() - 2, rookStart.getY());
        Rook rook = (Rook) rookStart.getPiece();
        Move rookMove = new Move(player, rookStart, rookEnd);

        movesPlayed.add(rookMove);
        rookMove.getEnd().setPiece(rook);
        rookMove.getStart().setPiece(null);
    }*/

    public void makeQueensideCastleRookMove(King king, Player player) {
        Square kingStart = king.findSquare(board);
        Square rookStart = board.getSquare(kingStart.getX() - 4, kingStart.getY());
        Square rookEnd =  board.getSquare(rookStart.getX() + 3, rookStart.getY());
        Rook rook = (Rook) rookStart.getPiece();
        Move rookMove = new Move(player, rookStart, rookEnd);

        movesPlayed.add(rookMove);
        rookMove.getEnd().setPiece(rook);
        rookMove.getStart().setPiece(null);
    }

    public List<Move> getAllLegalMoves() {
        return board.getLegalMoves(this, board.getAllSquaresWithPieces());
    }

    /*public void undoLastMove() {
        Move move = movesPlayed.get(movesPlayed.size() - 1);
        Player player = move.getPlayer();
        Piece piece = move.getPieceMoved();

        if (move.getPieceKilled() != null) {
            move.getEnd().getPiece().setKilled(true);
        }

        // check if rook moved from starting square,
        // if it did set the corresponding castling right to false (kingside or queenside)
        if (piece instanceof Rook) {
            if (piece.isWhite()) {
                King whiteKing = (King) getBoard().getPiecesForColor(true).stream()
                        .filter(p -> p.getClass().isAssignableFrom(King.class))
                        .collect(Collectors.toList()).get(0);
                if (move.getStart().getX() == 7 && move.getStart().getY() == 7) {
                    whiteKing.setHasKingsideCastlingRight(true);
                } else if (move.getStart().getX() == 0 && move.getStart().getY() == 7) {
                    whiteKing.setHasQueensideCastlingRight(true);
                }
            } else {
                King blackKing = (King) getBoard().getPiecesForColor(false).stream()
                        .filter(p -> p.getClass().isAssignableFrom(King.class))
                        .collect(Collectors.toList()).get(0);
                if (move.getStart().getX() == 7 && move.getStart().getY() == 0) {
                    blackKing.setHasKingsideCastlingRight(true);
                } else if (move.getStart().getX() == 0 && move.getStart().getY() == 0) {
                    blackKing.setHasQueensideCastlingRight(true);
                }
            }
        }

        // checks if it's a castling move, if it is it also moves the rook
        if (piece instanceof King) {
            King king = (King) piece;
            if (king.isValidCastlingKingside(board, move.getStart(), move.getEnd())) {
                makeKingsideCastleRookMove(king, player);
            } else if (king.isValidCastlingQueenside(board, move.getStart(), move.getEnd())) {
                makeQueensideCastleRookMove(king, player);
            }
            king.setHasKingsideCastlingRight(true);
            king.setHasQueensideCastlingRight(true);
        }
        movesPlayed.remove(move);
        move.getEnd().setPiece(move.getPieceKilled());
        move.getStart().setPiece(piece);


        if (playerToMove == players[0]) {
            playerToMove = players[1];
        } else {
            playerToMove = players[0];
        }
    }*/
}
