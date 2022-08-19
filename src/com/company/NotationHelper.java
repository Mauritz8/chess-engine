package com.company;

import com.company.pieces.King;
import com.company.pieces.Pawn;

import java.util.List;

public class NotationHelper {

    public String getAlgebraicNotation(Move move) {
        String moveNotation = "";
        Piece movedPiece = move.getPieceMoved();

        if (movedPiece instanceof King) {
            King king = (King) movedPiece;
            if (king.isKingSideCastlingMove(move.getStart(), move.getEnd())) {
                return "O-O";
            }
            if (king.isQueenSideCastlingMove(move.getStart(), move.getEnd())) {
                return "O-O-O";
            }
        }

        if (!(movedPiece instanceof Pawn)) {
            moveNotation += movedPiece.getCharRepresentation();
        }
        if (move.getPieceKilled() != null) {
            if (movedPiece instanceof Pawn) {
                moveNotation += move.getStart().getAlgebraicNotation().charAt(0);
            }
            moveNotation += "x";
        }
        moveNotation += move.getEnd().getAlgebraicNotation();

        return moveNotation;
    }

    // create move based on algebraic notation of move
    public Move getMoveFromNotation(Game game, String moveNotation) {
        Board board = game.getBoard();
        Player playerToMove = game.getPlayerToMove();
        for (Piece piece : board.getPiecesForColor(playerToMove.isWhiteSide())) {
            List<Move> legalMoves = piece.legalMoves(game, piece.findSquare(board));
            for (Move move : legalMoves) {
                if (getAlgebraicNotation(move).equals(moveNotation)) {
                    return move;
                }
            }
        }
        return null;
    }

}
