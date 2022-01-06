package entities;

import db.entity.MeetingRoomEntity;

public class MeetingRoom {
    private String roomID;
    private int capacity;
    private String roomName;

    public MeetingRoom(String roomID, int capacity, String roomName) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.roomName = roomName;
    }

    public static MeetingRoom convertToMeetingRoom(MeetingRoomEntity  meetingRoomEntity) {
        MeetingRoom meetingRoom = new MeetingRoom(meetingRoomEntity.getRoomID(), meetingRoomEntity.getCapacity(), meetingRoomEntity.getRoomName());
        return meetingRoom;
    }

    public String getRoomID() {
        return this.roomID;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MeetingRoom)) return false;
        final MeetingRoom other = (MeetingRoom) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$roomID = this.getRoomID();
        final Object other$roomID = other.getRoomID();
        if (this$roomID == null ? other$roomID != null : !this$roomID.equals(other$roomID)) return false;
        if (this.getCapacity() != other.getCapacity()) return false;
        final Object this$roomName = this.getRoomName();
        final Object other$roomName = other.getRoomName();
        if (this$roomName == null ? other$roomName != null : !this$roomName.equals(other$roomName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MeetingRoom;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $roomID = this.getRoomID();
        result = result * PRIME + ($roomID == null ? 43 : $roomID.hashCode());
        result = result * PRIME + this.getCapacity();
        final Object $roomName = this.getRoomName();
        result = result * PRIME + ($roomName == null ? 43 : $roomName.hashCode());
        return result;
    }

    public String toString() {
        return "MeetingRoom(roomID=" + this.getRoomID() + ", capacity=" + this.getCapacity() + ", roomName=" + this.getRoomName() + ")";
    }
}
