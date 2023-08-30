package LeetcodePrograms.InterviewQuestions.AppleInterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
    public double calculatePriceUtil(Series s, double cost){
        if(s.episodeNumber.size() == 1){
            cost = cost + s.individualPrice;
        }else if(s.episodeNumber.size() == 2){
            cost = cost + (0.75*s.individualPrice * s.episodeNumber.size());
        }else{
            cost = cost + (0.70*s.individualPrice * s.episodeNumber.size());
        }
        return cost;
    }
    public double calculateCost(List<Series> series) {
        double totalCost = 0;
        for(Series s : series ){
            totalCost = totalCost + calculatePriceUtil(s, totalCost);
        }
        return totalCost;
    }

    public static void main(String []args){
        Item item = new Item();
        List<Series> seriesList = new ArrayList<>();
        Series s1 = new Series("Cobra Kai" , Arrays.asList(1,2,3));
        Series s2 = new Series("The Crown" , Arrays.asList(2,3));
        seriesList.add(s1);
        seriesList.add(s2);
        System.out.println(item.calculateCost(seriesList));
    }
}
