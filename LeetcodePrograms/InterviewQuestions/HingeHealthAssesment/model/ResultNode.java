package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model;

import java.util.List;

public class ResultNode {
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ResultNode> getChildren() {
        return children;
    }

    public void setChildren(List<ResultNode> children) {
        this.children = children;
    }

    String label;
    List<ResultNode> children;

}