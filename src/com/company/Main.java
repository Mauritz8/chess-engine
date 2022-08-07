package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        Player player1 = new Player("Mauritz", true);
        Player player2 = new Player("Martin", false);
        game.initialize(player1, player2);

        while (!game.gameEnded()) {
            printBoard(game.getBoard());

            Scanner scanner = new Scanner(System.in);
            boolean whiteToPlay = game.getPlayerToMove().isWhiteSide();
            /*if (whiteToPlay) {
                System.out.println("White to move: ");
            }
            else {
                System.out.println("Black to move: ");
            }*/

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
            game.makeMove(playerToMove, new Move(playerToMove, start, end));
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
