package db.service;

import db.entity.MeetingRoomEntity;
import entities.BookingCriteria;
import entities.BookingStatus;

import java.util.List;

public interface BookingDatabase {
    BookingStatus bookMeeting(String roomId, long startTime, long endTime);
    List<MeetingRoomEntity> fetchMeetingRooms(BookingCriteria bookingCriteria);
    MeetingRoomEntity getMeetingRoom(String meetingRoomId);
}
