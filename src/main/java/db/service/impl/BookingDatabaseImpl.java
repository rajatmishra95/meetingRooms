package db.service.impl;

import db.entity.MeetingRoomEntity;
import db.service.BookingDatabase;
import entities.AdditionalFeature;
import entities.BookingCriteria;
import entities.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingDatabaseImpl implements BookingDatabase {

    private Map<String, List<Slot>> bookedIntervals;
    private Map<String, MeetingRoomEntity> meetingRoomsMap;
    private Map<AdditionalFeature, List<String>> featureListMap;

    public BookingDatabaseImpl(Map<String, List<Slot>> bookedIntervals, Map<String, MeetingRoomEntity> meetingRoomsMap, Map<AdditionalFeature, List<String>> featureListMap) {
        this.bookedIntervals = bookedIntervals;
        this.meetingRoomsMap = meetingRoomsMap;
        this.featureListMap = featureListMap;
    }

    @Override
    public synchronized BookingStatus bookMeeting(String roomId, long startTime, long endTime) {
        if(!isRoomFree(roomId, startTime, endTime)) {
            return new BookingStatus(false, "meeting room already booked", "meeting room already booked");
        }
        bookedIntervals.putIfAbsent(roomId, new ArrayList<>());
        bookedIntervals.get(roomId).add(new Slot(startTime, endTime));
        return new BookingStatus(true, null, null);
    }

    @Override
    public List<MeetingRoomEntity> fetchMeetingRooms(BookingCriteria bookingCriteria) {
        List<String> candidateMeetingRooms = new ArrayList<>();
        for(AdditionalFeature additionalFeature : bookingCriteria.getFeatures()) {
            if(featureListMap.get(additionalFeature) != null) {
                candidateMeetingRooms.addAll(featureListMap.get(additionalFeature));
            }
        }
        candidateMeetingRooms = filterRoomsByTime(bookingCriteria.getStartTime(), bookingCriteria.getEndTime(), candidateMeetingRooms);
        List<MeetingRoomEntity> meetingRoomEntities = convertToEntity(candidateMeetingRooms);
        return filterRoomsByCapacity(meetingRoomEntities, bookingCriteria.getCapacity());
    }

    private List<MeetingRoomEntity> convertToEntity(List<String> candidateMeetingRooms) {
        List<MeetingRoomEntity> meetingRoomEntities = new ArrayList<>();
        for(String id : candidateMeetingRooms) {
            meetingRoomEntities.add(meetingRoomsMap.get(id));
        }
        return meetingRoomEntities;
    }

    private List<String> filterRoomsByTime(long startTime, long endTime, List<String> availableMeetingRooms) {
        List<String> candidateMeetingRooms = new ArrayList<>();
        for(String id : availableMeetingRooms) {
            if(isRoomFree(id, startTime, endTime)) {
                candidateMeetingRooms.add(id);
            }
        }
        return candidateMeetingRooms;
    }
    
    private List<MeetingRoomEntity> filterRoomsByCapacity(List<MeetingRoomEntity> meetingRoomEntities, int capacity) {
        List<MeetingRoomEntity> filteredRooms = new ArrayList<>();
        for(MeetingRoomEntity meetingRoomEntity : meetingRoomEntities) {
            if(meetingRoomEntity.getCapacity() >= capacity) {
                filteredRooms.add(meetingRoomEntity);
            }
        }
        return filteredRooms;
    }
    
    private boolean isRoomFree(String roomId, long startTime, long endTime) {
        List<Slot> bookedSlots = bookedIntervals.get(roomId);
        if(bookedSlots == null) {
            return true;
        }
        for(Slot s : bookedSlots) {
            if(s.overlap(startTime, endTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MeetingRoomEntity getMeetingRoom(String meetingRoomId) {
        return meetingRoomsMap.get(meetingRoomId);
    }

    private static class Slot {
        private long startTime;
        private long endTime;

        public Slot(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean overlap(long s, long e) {
            long x1 = startTime;
            long x2 = endTime;
            long y1 = s;
            long y2 = e;
            return x1 <= y2 && y1 <= x2;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public long getEndTime() {
            return this.endTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Slot)) return false;
            final Slot other = (Slot) o;
            if (!other.canEqual((Object) this)) return false;
            if (this.getStartTime() != other.getStartTime()) return false;
            if (this.getEndTime() != other.getEndTime()) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Slot;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final long $startTime = this.getStartTime();
            result = result * PRIME + (int) ($startTime >>> 32 ^ $startTime);
            final long $endTime = this.getEndTime();
            result = result * PRIME + (int) ($endTime >>> 32 ^ $endTime);
            return result;
        }

        public String toString() {
            return "BookingDatabaseImpl.Slot(startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ")";
        }
    }
}
