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

            game.getBoard().print();
            Player playerToMove = game.getPlayerToMove();

            Scanner scanner = new Scanner(System.in);
            Move move = null;
            do {
                System.out.print("Move: ");
                String moveNotation = scanner.nextLine();
                move = notationHelper.getMoveFromNotation(game.getBoard(), moveNotation, playerToMove);
                if (move == null) {
                    System.out.println("The move " + moveNotation + " can't be played!\nChoose another move:\n");
                }
            } while (move == null);

            game.makeMove(playerToMove, move);
        }
    }
}
