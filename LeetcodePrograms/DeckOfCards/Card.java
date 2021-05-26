package LeetcodePrograms.DeckOfCards;

/**
 * @author Rishi Khurana
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    Rank getRank() {
        return rank;
    }

    Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
