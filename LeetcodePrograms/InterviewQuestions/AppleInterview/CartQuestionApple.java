package LeetcodePrograms.InterviewQuestions.AppleInterview;
import java.util.List;
/*
# TV Cart
#
# The cart calculates a cost of supplied episodes.
# When a user buys 2 episodes from same collection, they get a 25% discount on the second episode.
# When a user buys 3 or more episodes from the same collection, they get a 30% discount on the third and following episodes.
#
# Series:
# Cobra Kai: Episodes 1-10 $1 each
# The Crown: Episodes 1-10 $2 each
# Game of Thrones: Episodes 1-10 $3 each
# --------------------------------------
# We have been asked to add the walking dead as a new series to our offering. It should following the same rules as above
#
# The Walking Dead: Episodes 1-10 $2.50 each
*/

public class CartQuestionApple {
// below is the question.. convert it to oop

    public double calculateCost(List<String> episodes) {
        double costOfCobraKai =0;
        double costOfCrown = 0;
        double costOfGOT =0;

        double costOfEpisode = 0;
        double numberOfCobraKai = 0;
        double numberOfCrown = 0;
        double numberOfGOT = 0;
        for (String episode : episodes) {
            if (episode.startsWith("Cobra Kai")) {
                costOfEpisode = 1;
                numberOfCobraKai++;
                if (numberOfCobraKai == 1) {
                    costOfCobraKai += costOfEpisode;
                } else if (numberOfCobraKai == 2) {
                    costOfCobraKai += (0.75 * costOfEpisode);
                } else {
                    costOfCobraKai += (0.7 * costOfEpisode);
                }
            } else if (episode.startsWith("The Crown")) {
                costOfEpisode = 2;
                numberOfCrown++;
                if (numberOfCrown == 1) {
                    costOfCrown += costOfEpisode;
                } else if (numberOfCrown == 2) {
                    costOfCrown += (0.75 * costOfEpisode);
                } else {
                    costOfCrown += (0.7 * costOfEpisode);
                }
            } else {
                numberOfGOT++;
                if (numberOfGOT == 1) {
                    costOfGOT += 3;
                } else if (numberOfGOT == 2) {
                    costOfGOT += 2.25;
                } else {
                    costOfGOT += 2.1;
                }

            }
        }
        return costOfCobraKai + costOfCrown + costOfGOT ;  
    }

    public static void main(String[] args) {

/*
        double cost = CartQuestionApple.calculateCost(List.of("Cobra Kai: Episode 1", "The Crown: Episode 1", "Game of Thrones: Episode 1");
        Assert.assertEquals(1.00, CartQuestionApple.calculateCost(List.of("Cobra Kai: Episode 1")), 0.00000001);
        Assert.assertEquals(2.00, CartQuestionApple.calculateCost(List.of("The Crown: Episode 1")), 0.00000001);
        Assert.assertEquals(3.00, CartQuestionApple.calculateCost(List.of("Game of Thrones: Episode 1")), 0.00000001);
        Assert.assertEquals(1.75, Cart.calculateCost(List.of("Cobra Kai: Episode 1", "Cobra Kai: Episode 2")), 0.00000001);
        Assert.assertEquals(3.50, Cart.calculateCost(List.of("The Crown: Episode 1", "The Crown: Episode 2")), 0.00000001);
        Assert.assertEquals(5.25, Cart.calculateCost(List.of("Game of Thrones: Episode 1", "Game of Thrones: Episode 2")), 0.00000001);
        Assert.assertEquals(2.45, Cart.calculateCost(List.of("Cobra Kai: Episode 1", "Cobra Kai: Episode 2", "Cobra Kai: Episode 3")), 0.00000001);
        Assert.assertEquals(4.90, Cart.calculateCost(List.of("The Crown: Episode 1", "The Crown: Episode 2", "The Crown: Episode 3")), 0.00000001);
        Assert.assertEquals(7.35, Cart.calculateCost(List.of("Game of Thrones: Episode 1", "Game of Thrones: Episode 2", "Game of Thrones: Episode 3")), 0.00000001);
        Assert.assertEquals(6.00, Cart.calculateCost(List.of("Cobra Kai: Episode 1", "The Crown: Episode 1", "Game of Thrones: Episode 1")), 0.00000001);
*/

    }
}

