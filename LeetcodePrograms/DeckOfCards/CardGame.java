package LeetcodePrograms.DeckOfCards;

import java.util.Scanner;


/**
 * @author Rishi Khurana
 */
public class CardGame {
    private static final int NO_OF_PLAYERS = 4;
    private final Player[] players = new Player[NO_OF_PLAYERS];
    private final DeckOfCards deckOfCards = new DeckOfCards();

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();

        System.out.println("WELCOME TO THE CARD GAME\n");
        System.out.println("Enter the four players' name below");

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < NO_OF_PLAYERS; i++) {
            cardGame.players[i] = new Player(scan.next());
        }

        cardGame.deckOfCards.shuffleDeck();

        System.out.println(cardGame.deckOfCards);

        cardGame.dealCards();

        cardGame.displayCardsForAllPlayers();
    }


    private void dealCards() {
        for (int i = 0; i < DeckOfCards.SIZE; i++) {
            players[i % NO_OF_PLAYERS].giveCard(deckOfCards.getCard(i));
        }
    }

    private void displayCardsForAllPlayers() {
        for (int i = 0; i < NO_OF_PLAYERS; i++) {
            System.out.println(players[i].printPlayerCards());
        }
    }
}
