package LeetcodePrograms.DeckOfCards;

import java.util.*;


/**
 * @author Rishi Khurana
 */
public class Player {
    private String name;
    private List<Card> cards = new ArrayList<>();

    Player(String name) {
        this.name = name;
    }

    void giveCard(Card card) {
        cards.add(card);
    }

    List<Card> getCards() {
        return cards;
    }

    String printPlayerCards() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + " has the following cards:\n");

        for (Card card : cards) {
            stringBuilder.append(card + "\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return name;
    }
}
