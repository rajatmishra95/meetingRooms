package entities;

public class BookingStatus {
    private boolean isRoomBooked;
    private String failureMessage;
    private String displayMessage;

    public BookingStatus(boolean isRoomBooked, String failureMessage, String displayMessage) {
        this.isRoomBooked = isRoomBooked;
        this.failureMessage = failureMessage;
        this.displayMessage = displayMessage;
    }

    public boolean isRoomBooked() {
        return this.isRoomBooked;
    }

    public String getFailureMessage() {
        return this.failureMessage;
    }

    public String getDisplayMessage() {
        return this.displayMessage;
    }

    public void setRoomBooked(boolean isRoomBooked) {
        this.isRoomBooked = isRoomBooked;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BookingStatus)) return false;
        final BookingStatus other = (BookingStatus) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.isRoomBooked() != other.isRoomBooked()) return false;
        final Object this$failureMessage = this.getFailureMessage();
        final Object other$failureMessage = other.getFailureMessage();
        if (this$failureMessage == null ? other$failureMessage != null : !this$failureMessage.equals(other$failureMessage))
            return false;
        final Object this$displayMessage = this.getDisplayMessage();
        final Object other$displayMessage = other.getDisplayMessage();
        if (this$displayMessage == null ? other$displayMessage != null : !this$displayMessage.equals(other$displayMessage))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BookingStatus;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isRoomBooked() ? 79 : 97);
        final Object $failureMessage = this.getFailureMessage();
        result = result * PRIME + ($failureMessage == null ? 43 : $failureMessage.hashCode());
        final Object $displayMessage = this.getDisplayMessage();
        result = result * PRIME + ($displayMessage == null ? 43 : $displayMessage.hashCode());
        return result;
    }

    public String toString() {
        return "BookingStatus(isRoomBooked=" + this.isRoomBooked() + ", failureMessage=" + this.getFailureMessage() + ", displayMessage=" + this.getDisplayMessage() + ")";
    }
}
