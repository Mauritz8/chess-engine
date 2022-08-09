package com.company;

import com.company.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Square[][] squares = new Square[8][8];

    public Board() {
        this.resetBoard();
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return squares[y][x];
    }

    public void resetBoard() {
        // set up black pieces
        squares[0][0] = new Square(0, 0, new Rook(false));
        squares[0][1] = new Square(0, 1, new Knight(false));
        squares[0][2] = new Square(0, 2, new Bishop(false));
        squares[0][3] = new Square(0, 3, new Queen(false));
        squares[0][4] = new Square(0, 4, new King(false));
        squares[0][5] = new Square(0, 5, new Bishop(false));
        squares[0][6] = new Square(0, 6, new Knight(false));
        squares[0][7] = new Square(0, 7, new Rook(false));
        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Square(1, i, new Pawn(false));
        }

        // set up white pieces
        squares[7][0] = new Square(7, 0, new Rook(true));
        squares[7][1] = new Square(7, 1, new Knight(true));
        squares[7][2] = new Square(7, 2, new Bishop(true));
        squares[7][3] = new Square(7, 3, new Queen(true));
        squares[7][4] = new Square(7, 4, new King(true));
        squares[7][5] = new Square(7, 5, new Bishop(true));
        squares[7][6] = new Square(7, 6, new Knight(true));
        squares[7][7] = new Square(7, 7, new Rook(true));
        for (int i = 0; i < 8; i++) {
            squares[6][i] = new Square(6, i, new Pawn(true));
        }

        // set rest of squares empty
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j, null);
            }
        }
    }

    public void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j].getPiece();
                if (piece != null) {
                    System.out.print(piece.getCharRepresentation() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("\n");
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Piece> getAllPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j].getPiece();
                if (piece != null) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    public List<Square> getAllSquaresWithPieces() {
        List<Square> squaresWithPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j].getPiece();
                if (piece != null) {
                    squaresWithPieces.add(squares[i][j]);
                }
            }
        }
        return squaresWithPieces;
    }

    public List<Square> squaresUp(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dy = 1; dy < 8; dy++) {
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(currentSquare.getX(), y);
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresDown(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dy = -1; dy > -8; dy--) {
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(currentSquare.getX(), y);
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresRight(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            int x = currentSquare.getX() + dx;
            Square square = this.getSquare(x, currentSquare.getY());
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresLeft(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = -1; dx > -8; dx--) {
            int x = currentSquare.getX() + dx;
            Square square = this.getSquare(x, currentSquare.getY());
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresUpRight(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            int dy = dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresDownRight(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = 1; dx < 8; dx++) {
            int dy = -dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresUpLeft(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = -1; dx > -8; dx--) {
            int dy = dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }

    public List<Square> squaresDownLeft(Square currentSquare) {
        List<Square> squares = new ArrayList<>();
        for (int dx = -1; dx > -8; dx--) {
            int dy = -dx;

            int x = currentSquare.getX() + dx;
            int y = currentSquare.getY() + dy;
            Square square = this.getSquare(x, y);
            squares.add(square);
        }
        return squares;
    }


    public List<Move> getLegalMoves(List<Square> squaresWithPieces, Player player) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Square square : squaresWithPieces) {
            Piece piece = square.getPiece();
            if (piece.isWhite() == player.isWhiteSide()) {
                possibleMoves.addAll(piece.legalMoves(this, square, player));
            }
        }
        return possibleMoves;
    }

    public Square getSquareWithNotation(String notation) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].getAlgebraicNotation().equals(notation)) {
                    return squares[i][j];
                }
            }
        }
        throw new Exception("No square with the notation " + notation);
    }
}
