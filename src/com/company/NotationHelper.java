package com.company;

import com.company.pieces.Pawn;

public class NotationHelper {

    public String getAlgebraicNotation(Move move) {
        String moveNotation = "";
        Piece movedPiece = move.getPieceMoved();
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

    // create move based on algebraic notation
    public Move getMoveFromNotation(Board board, String moveNotation, Player playerToMove) throws Exception {

        //moveNotation = moveNotation.replace("x", "");

        String endSquareNotation = moveNotation.substring(moveNotation.length() - 2);
        Square endSquare = board.getSquareWithNotation(endSquareNotation);

        if (moveNotation.charAt(0) > 60 && moveNotation.charAt(0) < 69) {
            // it's a pawn move (e.g e4 or cxd5)

        }

        if (moveNotation.length() == 2) { // e4 for example
            // must be a pawn
            //String endSquare = moveNotation;
        } else if (moveNotation.length() == 3) {

        } else {
            throw new Exception("Move notation is wrong");
        }

        int startX = 5;
        int startY = 5;
        int endX = 6;
        int endY = 7;
        Square start = board.getSquare(startX, startY);
        Square end = board.getSquare(endX, endY);
        return new Move(playerToMove, start, end);
    }
}
