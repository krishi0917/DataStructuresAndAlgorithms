package DesignPatterns.Scheduler;

public class Room {
    int id;
    String name;
    RoomStatus status;
    MeetingDetails currentMeetingInfo;

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = RoomStatus.FREE;
    }
}
