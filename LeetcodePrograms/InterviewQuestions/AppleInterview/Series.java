package LeetcodePrograms.InterviewQuestions.AppleInterview;

import java.util.List;

public class Series {
    List<Integer> episodeNumber ;
    String productName;
    int individualPrice;
    public Series(String pName , List<Integer> epNumber ){
        productName = pName;
        episodeNumber = epNumber;
        if(productName == "Cobra Kai")
            individualPrice = 1;
        else if(productName == "The Crown")
            individualPrice = 2;
        else if(productName == "GOT")
            individualPrice = 3;
    }
}
