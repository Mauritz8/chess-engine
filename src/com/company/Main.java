package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
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
            System.out.print("Move: ");
            String moveNotation = scanner.nextLine();
            Move move = notationHelper.getMoveFromNotation(game.getBoard(), moveNotation, playerToMove);

            game.makeMove(playerToMove, move);
        }
    }
}
