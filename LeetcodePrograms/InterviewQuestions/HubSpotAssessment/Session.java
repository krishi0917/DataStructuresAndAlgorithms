package LeetcodePrograms.InterviewQuestions.HubSpotAssessment;
import java.util.List;
public class Session {
    long duration;
    List<String> pages;
    long startTime;
    public Session(long d, List<String> p , long sTime){
        duration = d;
        pages = p;
        startTime = sTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
