package LeetcodePrograms.DeckOfCards;

import java.util.Random;


/**
 * @author Rishi Khurana
 */
public class DeckOfCards {

        public static final int SIZE = 52;
        private final Card[] cards = new Card[SIZE];

        DeckOfCards() {
            int currentCardIndex = 0;

            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards[currentCardIndex++] = new Card(suit, rank);
                }
            }
        }

        Card[] getCards() {
            return cards;
        }

        Card getCard(int index) {
            return cards[index];
        }

        void shuffleDeck() {
            Random rand = new Random();

            for (int i = 0; i < SIZE; i++) {
                int j = rand.nextInt(SIZE);
                swapCards(i, j);
            }
        }

        void swapCards(int i, int j) {
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Current Deck:\n");

            for (int i = 0; i < DeckOfCards.SIZE; i++) {
                stringBuilder.append("Card #" + (i + 1) + ": " + getCard(i) + "\n");
            }

            return stringBuilder.toString();
        }
    }

