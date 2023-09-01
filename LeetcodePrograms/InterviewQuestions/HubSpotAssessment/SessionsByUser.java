package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;
import java.util.ArrayList;
import java.util.List;
public class SessionsByUser {
    List<Visitor> visitorList;

    public SessionsByUser(){
        visitorList = new ArrayList<>();
    }
    public void Add(Visitor v){
        visitorList.add(v);
    }
}
