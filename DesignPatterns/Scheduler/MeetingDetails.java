package DesignPatterns.Scheduler;

public class MeetingDetails {
    int id;
    long startTime;
    long duration;

    public MeetingDetails(Integer id, long startTime, long duration) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
    }
}
