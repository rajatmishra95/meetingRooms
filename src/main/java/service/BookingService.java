package service;

import entities.BookingCriteria;
import entities.BookingStatus;
import entities.MeetingRoom;

import java.util.List;

public interface BookingService {
    BookingStatus bookMeeting(MeetingRoom meetingRoom, long startTime, long endTime);

    List<MeetingRoom> fetchMeetingRooms(BookingCriteria bookingCriteria);

    List<MeetingRoom> searchMeetingRoom(BookingCriteria bookingCriteria, String searchPrefix);

}
