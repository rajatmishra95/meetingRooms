package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class BookingCriteria {

    public BookingCriteria() {

    }
    private int capacity;

    public BookingCriteria(int capacity, long startTime, long endTime, List<AdditionalFeature> features) {
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.features = features;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<AdditionalFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<AdditionalFeature> features) {
        this.features = features;
    }

    private long startTime;
    private long endTime;
    private List<AdditionalFeature> features;
}
