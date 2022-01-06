import db.entity.MeetingRoomEntity;
import db.service.BookingDatabase;
import db.service.impl.BookingDatabaseImpl;
import entities.AdditionalFeature;
import entities.BookingCriteria;
import entities.BookingStatus;
import entities.MeetingRoom;
import service.BookingService;
import service.impl.BookingServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, MeetingRoomEntity> meetingRoomEntityMap = new HashMap<>();
        meetingRoomEntityMap.put("1", new MeetingRoomEntity("1", 2, "1"));
        meetingRoomEntityMap.put("2", new MeetingRoomEntity("2", 5, "2"));
        meetingRoomEntityMap.put("3", new MeetingRoomEntity("3", 10, "3"));
        meetingRoomEntityMap.put("4", new MeetingRoomEntity("4", 20, "4"));

        Map<AdditionalFeature, List<String>> featureListMap = new HashMap<>();

        featureListMap.put(AdditionalFeature.PROJECTOR, Arrays.asList(new String[]{"3", "4"}));
        featureListMap.put(AdditionalFeature.TELEVISION, Arrays.asList(new String[]{"3", "4"}));
        featureListMap.put(AdditionalFeature.WHITEBOARD, Arrays.asList(new String[]{"1", "2", "3"}));

        BookingDatabase bookingDatabase = new BookingDatabaseImpl(new HashMap<>(), meetingRoomEntityMap, featureListMap);

        BookingService bookingService = new BookingServiceImpl(bookingDatabase);

        BookingCriteria bookingCriteria = new BookingCriteria();
        bookingCriteria.setCapacity(4);
        bookingCriteria.setEndTime(10);
        bookingCriteria.setStartTime(1);
        bookingCriteria.setFeatures(Arrays.asList(new AdditionalFeature[]{AdditionalFeature.PROJECTOR, AdditionalFeature.TELEVISION, AdditionalFeature.WHITEBOARD }));
        List<MeetingRoom> meetingRooms = bookingService.fetchMeetingRooms(bookingCriteria);
        for(MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getRoomID());
        }

        bookingCriteria.setCapacity(18);
        bookingCriteria.setEndTime(10);
        bookingCriteria.setStartTime(1);
        bookingCriteria.setFeatures(Arrays.asList(new AdditionalFeature[]{AdditionalFeature.PROJECTOR, AdditionalFeature.TELEVISION, AdditionalFeature.WHITEBOARD }));
        meetingRooms = bookingService.fetchMeetingRooms(bookingCriteria);
        for(MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getRoomID());
        }
        MeetingRoomEntity meetingRoomEntity = meetingRoomEntityMap.get("1");
        MeetingRoom meetingRoom = MeetingRoom.convertToMeetingRoom(meetingRoomEntity);
        BookingStatus bookingStatus = bookingService.bookMeeting(meetingRoom, 1, 10);
        BookingStatus bookingStatus1 = bookingService.bookMeeting(meetingRoom, 1, 10);

    }
}
