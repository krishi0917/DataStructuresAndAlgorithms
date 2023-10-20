package LeetcodePrograms.InterviewQuestions.HingeHealthAssesment.model;

public class Request {
    public long parentId;
    public String labelName;

    public Request(long parentId, String labelName){
        this.parentId = parentId;
        this.labelName = labelName;
    }
}
