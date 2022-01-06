package service.impl;

import db.entity.MeetingRoomEntity;
import db.service.BookingDatabase;
import entities.BookingCriteria;
import entities.BookingStatus;
import entities.MeetingRoom;
import service.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private BookingDatabase bookingDatabase;

    public BookingServiceImpl(BookingDatabase bookingDatabase) {
        this.bookingDatabase = bookingDatabase;
    }

    @Override
    public BookingStatus bookMeeting(MeetingRoom meetingRoom, long startTime, long endTime) {
        return bookingDatabase.bookMeeting(meetingRoom.getRoomID(), startTime, endTime);
    }

    @Override
    public List<MeetingRoom> fetchMeetingRooms(BookingCriteria bookingCriteria) {
        List<MeetingRoomEntity> meetingRoomEntities = bookingDatabase.fetchMeetingRooms(bookingCriteria);
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        for(MeetingRoomEntity meetingRoomEntity : meetingRoomEntities) {
            meetingRooms.add(MeetingRoom.convertToMeetingRoom(meetingRoomEntity));
        }
        return meetingRooms.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> searchMeetingRoom(BookingCriteria bookingCriteria, String searchPrefix) {
        return null;
    }
}
