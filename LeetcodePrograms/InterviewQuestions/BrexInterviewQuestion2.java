package LeetcodePrograms.InterviewQuestions;/*

Splendor is a card-based board game where players buy cards in exchange for colored gems. In this game, today, we care about two things, gems and cards.

Players can have any number of gems of five different colors: (B)lue, (W)hite, (G)reen, (R)ed, and (Y)ellow.

Players can exchange gems for cards. A card appears as such:

+----------+
|        G |
|          |
|          |
| 3W       |
| 2G       |
| 1R       |
+----------+

This indicates that the card costs 3 (W)hite gems, 2 (G)reen gems, and 1 (R)ed. The “G” in the upper right indicates the color of the card (this will be useful later)

For this entire problem, we want to keep things simple by assuming that there is only one player.

The data model and structure of the program is up to you.

1. We want to write a function can_purchase() such that, given a particular card and collection of gems for a player, we return true if the player can afford the card, and false if they cannot.

2. We want to write a function purchase() such that, given a particular card and collection of gems for a player, we add the card to the players hand and subtract the cost from the players gems, if they are able to afford the card. The function should return true if the player can afford the card, and false if they cannot.

3. We want to introduce a new concept: for each card in a players hand of a given color, we want to reduce the cost of any new purchase by 1 gem for that held cards color. For example, if the player holds 2 (G)reen cards and 1 (R)ed, and we are considering a card that lists its cost as 3 (G)reen, 2 (R)ed, and 1 (B)lue, then the player should be able to purchase it for 1 G, 1 R, and 1 B.

*/

import java.util.*;
class BrexInterviewQuestion2 {

    static class Card{
        String color;
        int redRequired;
        int greenRequired;
        int whiteRequired;

        public Card(String c, int r, int g , int w){
            color = c;
            redRequired = r;
            greenRequired = g;
            whiteRequired = w;
        }
    }
    static class Player{
        int redQuantity;
        int greenQuantity;
        int whiteQuantity;
        List<Card> cardOwned;
        public Player(int r, int g , int w){
            redQuantity = r;
            greenQuantity = g;
            whiteQuantity = w;
            cardOwned = new ArrayList<>();
        }
    }

    public boolean canPurchase(Card card, Player p){
        if(card == null || p == null){
            return false;
        }
        boolean purchasePossible = false;
        List<Card> cardOwned = p.cardOwned;
        for(Card c : cardOwned){
            if(c.color.equals("red")){
                p.redQuantity = p.redQuantity + 1;
            }else if(c.color.equals("green")){
                p.greenQuantity = p.greenQuantity + 1;
            }else if(c.color.equals("white")){
                p.whiteQuantity = p.whiteQuantity + 1;
            }
        }
        if((p.redQuantity >= card.redRequired) && (p.greenQuantity >= card.greenRequired) && (p.whiteQuantity >=card.whiteRequired)){
            purchasePossible = true;
        }
        return purchasePossible;
    }

    public boolean purchase(Card card , Player p){
        if(canPurchase(card,p)){
            p.redQuantity = p.redQuantity - card.redRequired;
            p.greenQuantity = p.greenQuantity - card.greenRequired;
            p.whiteQuantity = p.whiteQuantity - card.whiteRequired;
            p.cardOwned.add(card);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Card card = new Card("green" , 1,2,3);
        Player player = new Player(3, 4, 4); //-> 2,2,1
        BrexInterviewQuestion2 s = new BrexInterviewQuestion2();
        // System.out.println(s.canPurchase(card,player));
        System.out.println(s.purchase(card,player));
        System.out.println(s.canPurchase(card, player));
    }
}

// NPE for both methods 
/*
    card null player null or both null
// positive scenario ->  true
            ---> largers number int.max
            ---> player has quantity 0
// negative scenario -> false
// values as player negative redquantity = -5     
            card -> negative whitequantity
*/
