package com.company;

import com.company.pieces.King;
import com.company.pieces.Pawn;

import java.util.List;

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
        if (pieceWasKilled()) {
            setPieceKilled(end.getPiece());
        }
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

    public boolean isLegal(Game game) {

        if (end == null) {
            return false;
        }

        if (pieceMoved == null) {
            return false;
        }

        if (player != game.getPlayerToMove()) {
            return false;
        }

        if (pieceMoved.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!pieceMoved.canMove(game.getBoard(), start, end)) {
            return false;
        }

        if (end.getPiece() != null && end.getPiece().isWhite() == player.isWhiteSide()) {
            return false;
        }

        if (start.getPiece() instanceof Pawn && !pawnMoveIsLegal(game)) {
            return false;
        }


        /*game.makeMove(this);

        // checks if you're king is threatened after the move
        King king = (King) game.getBoard().getPiecesForColor(player.isWhiteSide()).stream().filter(p -> p instanceof King).findFirst().get();
        List<Move> legalMoves = game.getAllLegalMoves();
        for (Move move : legalMoves) {
            if (move.getEnd() == king.findSquare(game.getBoard())) {
                System.out.println("not a legal move!");
                game.undoLastMove();
                return false;
            }
        }

        game.undoLastMove();*/
        return true;
    }

    // specific rules for pawn moves
    private boolean pawnMoveIsLegal(Game game) {
            int deltaX = Math.abs(start.getX() - end.getX());
            int deltaY = end.getY() - start.getY();
            switch (deltaX) {
                case 0:
                    switch (deltaY) {
                        case -1:
                            if (!player.isWhiteSide()) {
                                return false;
                            }
                            break;
                        case 1:
                            if (player.isWhiteSide()) {
                                return false;
                            }
                            break;
                        case -2:
                            if (!player.isWhiteSide()) {
                                return false;
                            }
                            if (start.getY() != 6) {
                                return false;
                            }
                            if (end.getPiece() != null || game.getBoard().getSquare(end.getX(), end.getY() + 1).getPiece() != null) {
                                return false;
                            }
                            break;
                        case 2:
                            if (player.isWhiteSide()) {
                                return false;
                            }
                            if (start.getY() != 1) {
                                return false;
                            }
                            if (end.getPiece() != null || game.getBoard().getSquare(end.getX(), end.getY() - 1).getPiece() != null) {
                                return false;
                            }
                            break;
                        default:
                            return false;
                    }
                    break;
                case 1:
                    if (end.getPiece() == null) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
            return true;
    }

    public boolean pieceWasKilled() {
        if (end != null && end.getPiece() != null && end.getPiece().isWhite() != player.isWhiteSide()) {
            return true;
        }
        return false;
    }
}
