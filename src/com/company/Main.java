package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        NotationHelper notationHelper = new NotationHelper();

        Player player1 = new Player("Mauritz", true);
        Player player2 = new Player("Martin", false);
        game.initialize(player1, player2);

        while (!game.gameEnded()) {

            List<Move> legalMoves = game.getAllLegalMoves();
            for (Move move : legalMoves) {
                System.out.println("move: " + notationHelper.getAlgebraicNotation(move));
            }

            printBoard(game.getBoard());

            Scanner scanner = new Scanner(System.in);

            //String moveNotation = scanner.nextLine();
            System.out.print("startX: ");
            int startX = scanner.nextInt();
            System.out.print("startY: ");
            int startY = scanner.nextInt();
            System.out.print("endX: ");
            int endX = scanner.nextInt();
            System.out.print("endY: ");
            int endY = scanner.nextInt();


            Player playerToMove = game.getPlayerToMove();
            Square start = game.getBoard().getSquare(startX, startY);
            Square end = game.getBoard().getSquare(endX, endY);
            Move move = new Move(playerToMove, start, end);
            game.makeMove(playerToMove, move);
            System.out.println("move notation: " + notationHelper.getAlgebraicNotation(move));
        }
    }

    private static void printBoard(Board board) {
        Square[][] squares = board.getSquares();
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
}