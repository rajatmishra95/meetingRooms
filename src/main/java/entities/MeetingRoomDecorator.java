package entities;

public abstract class MeetingRoomDecorator {
    private MeetingRoom meetingRoom;
    public MeetingRoomDecorator(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
