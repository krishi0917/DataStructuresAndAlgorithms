package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model;

import java.util.HashMap;
import java.util.Map;

public class NewResult {
    Map<Long, NewObject> map = new HashMap<>();

    public Map<Long, NewObject> getMap() {
        return map;
    }

    public void setMap(Map<Long, NewObject> map) {
        this.map = map;
    }
}