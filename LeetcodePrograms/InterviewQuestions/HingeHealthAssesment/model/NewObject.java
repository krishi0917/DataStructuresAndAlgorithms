package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model;

import java.util.ArrayList;
import java.util.List;

public class NewObject {
    String label;
    List<NewResult> children = new ArrayList<>();

    public List<NewResult> getChildren() {
        return children;
    }

    public void setChildren(List<NewResult> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
