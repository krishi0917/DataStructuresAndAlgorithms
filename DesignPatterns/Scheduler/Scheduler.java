package DesignPatterns.Scheduler;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Scheduler {
    private RoomManager roomManager;
    private static Scheduler instance;
    Map<Room, MeetingDetails> mapOfMeetingRoomVsMeeting ;

    public static Scheduler getInstance(RoomManager roomManager) {
        if (instance == null) {
            synchronized (Scheduler.class) {
                if (instance == null) {
                    instance = new Scheduler(roomManager);
                }
            }
        }
        return instance;
    }

    private Scheduler(RoomManager roomManager) {
        this.roomManager = roomManager;
        this.mapOfMeetingRoomVsMeeting = new ConcurrentHashMap<>();
    }

    public void scheduleMeeting(MeetingDetails meetingDetails) {
        Room room = getFreeMeetingRoom();
        if (room != null) {
            mapOfMeetingRoomVsMeeting.put(room, meetingDetails);
            roomManager.allocateRoom(room, meetingDetails);
            System.out.println("Meeting Room Allocated for Meeting : " + meetingDetails.id);
        } else {
            System.out.println("Meeting Room Could Not Be Allocated for Meeting : " + meetingDetails.id + " .. Discarding.. ");
        }

    }

    public void checkForTheMeetingCompletion() {
        synchronized (this) {
            for (Room meetingRoom : mapOfMeetingRoomVsMeeting.keySet()) {
                MeetingDetails meetingDetails = mapOfMeetingRoomVsMeeting.get(meetingRoom);
                if (System.currentTimeMillis() > (meetingDetails.startTime + meetingDetails.duration)) {
                    System.out.println("Meeting Room : " + meetingRoom.name + " is free now as the meeting : " + meetingDetails.id + " has ended.. ");
                    mapOfMeetingRoomVsMeeting.remove(meetingRoom);
                    roomManager.freeTheRoom(meetingRoom, meetingDetails);
                }
            }
        }
    }

    public Room getFreeMeetingRoom() {
        for (Room room : roomManager.meetingRoomList) {
            if (room.status == RoomStatus.FREE) {
                return room;
            }
        }
        return null;
    }
}
