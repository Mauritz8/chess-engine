package com.company.pieces;

import com.company.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        super.setCharRepresentation('B');
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int deltaX = Math.abs(start.getX() - end.getX());
        int deltaY = Math.abs(start.getY() - end.getY());
        if (deltaX == deltaY) {
            return true;
        }

        return false;
    }

    private Map<Integer, List<Integer>> squaresInGivenDirection(int dxInitial, int dyInitial, int dxIncrease, int dyIncrease, int dxFinal, int dyFinal) {
        Map<Integer, List<Integer>> squaresInGivenDirection = new HashMap<>();
        int i = 0;
        for (int dx = dxInitial; dx < 8; dx += dxIncrease) {
            for (int dy = dyInitial; dy < 8; dy += dyIncrease) {
                List<Integer> values = new ArrayList<>();
                values.add(dx);
                values.add(dy);
                squaresInGivenDirection.put(i, values);
            }
        }
        return squaresInGivenDirection;
    }

    private List<Move> legalMovesOneDirectionTest(Board board, Square currentSquare, Player player, Map<Integer, List<Integer>> squaresInDirection) {
        List<Move> moves = new ArrayList<>();
        for (Integer key : squaresInDirection.keySet()) {
            List values = squaresInDirection.get(key);
            int dx = (int) values.get(0);
            int dy = (int) values.get(1);
            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square newSquare = board.getSquare(x, y);
            if (newSquare != null && (newSquare.getPiece() == null || newSquare.getPiece().isWhite() != player.isWhiteSide())) {
                moves.add(new Move(player, currentSquare, newSquare));
            } else {
                return moves;
            }
        }
        return moves;
    }

    private List<Move> legalMovesOneDirection(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            for (int dy = 1; dy < 8; dy++) {
                int x = currentSquare.getX() + dx;
                int y = currentSquare.getY() + dy;
                Square newSquare = board.getSquare(x, y);
                if (newSquare != null && (newSquare.getPiece() == null || newSquare.getPiece().isWhite() != player.isWhiteSide())) {
                    moves.add(new Move(player, currentSquare, newSquare));
                } else {
                    return moves;
                }
            }
        }
        return moves;
    }

    @Override
    public List<Move> legalMoves(Board board, Square currentSquare, Player player) {
        List<Move> moves = new ArrayList<>();
        List<Move> movesUpRight = legalMovesOneDirectionTest(board, currentSquare, player, squaresInGivenDirection(1, 1, 1, 1, 8, 8));
        List<Move> movesDownRight = legalMovesOneDirectionTest(board, currentSquare, player, squaresInGivenDirection(1, -1, 1, -1, 8, -8));
        List<Move> movesDownLeft = legalMovesOneDirectionTest(board, currentSquare, player, squaresInGivenDirection(-1, -1, -1, -1, -8, -8));
        List<Move> movesUpLeft = legalMovesOneDirectionTest(board, currentSquare, player, squaresInGivenDirection(-1, 1, -1, 1, -8, 8));
        moves.addAll(movesUpRight);
        moves.addAll(movesDownRight);
        moves.addAll(movesDownLeft);
        moves.addAll(movesUpLeft);
        return moves;
    }

}
