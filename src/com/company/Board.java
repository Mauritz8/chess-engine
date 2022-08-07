package com.company;

import com.company.pieces.*;

public class Board {

    private Square[][] squares = new Square[8][8];
    public final String[][] SQUARES_NOTATIONS = {
            {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
            {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
            {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
            {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
            {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
            {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
            {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
            {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"}
    };

    public Board() {
        this.resetBoard();
    }

    public Square getSquare(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Square does not exist");
        }
        return squares[y][x];
    }

    public void resetBoard() {
        // set up black pieces
        squares[0][0] = new Square(0, 0, new Rook(false), SQUARES_NOTATIONS[0][0]);
        squares[0][1] = new Square(0, 1, new Knight(false), SQUARES_NOTATIONS[0][1]);
        squares[0][2] = new Square(0, 2, new Bishop(false), SQUARES_NOTATIONS[0][2]);
        squares[0][3] = new Square(0, 3, new Queen(false), SQUARES_NOTATIONS[0][3]);
        squares[0][4] = new Square(0, 4, new King(false), SQUARES_NOTATIONS[0][4]);
        squares[0][5] = new Square(0, 5, new Bishop(false), SQUARES_NOTATIONS[0][5]);
        squares[0][6] = new Square(0, 6, new Knight(false), SQUARES_NOTATIONS[0][6]);
        squares[0][7] = new Square(0, 7, new Rook(false), SQUARES_NOTATIONS[0][7]);
        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Square(1, i, new Pawn(false), SQUARES_NOTATIONS[1][i]);
        }

        // set up white pieces
        squares[7][0] = new Square(7, 0, new Rook(true), SQUARES_NOTATIONS[7][0]);
        squares[7][1] = new Square(7, 1, new Knight(true), SQUARES_NOTATIONS[7][1]);
        squares[7][2] = new Square(7, 2, new Bishop(true), SQUARES_NOTATIONS[7][2]);
        squares[7][3] = new Square(7, 3, new Queen(true), SQUARES_NOTATIONS[7][3]);
        squares[7][4] = new Square(7, 4, new King(true), SQUARES_NOTATIONS[7][4]);
        squares[7][5] = new Square(7, 5, new Bishop(true), SQUARES_NOTATIONS[7][5]);
        squares[7][6] = new Square(7, 6, new Knight(true), SQUARES_NOTATIONS[7][6]);
        squares[7][7] = new Square(7, 7, new Rook(true), SQUARES_NOTATIONS[7][7]);
        for (int i = 0; i < 8; i++) {
            squares[6][i] = new Square(6, i, new Pawn(true), SQUARES_NOTATIONS[6][i]);
        }

        // set rest of squares empty
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j, null, SQUARES_NOTATIONS[i][j]);
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }
}
