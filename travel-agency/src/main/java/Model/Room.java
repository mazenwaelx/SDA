package Model;

import lombok.Getter;

@Getter
public class Room {
    @Getter
    private final String roomId;
    @Getter
    private final Room.RoomType roomType; // Enum: SINGLE, DOUBLE, FAMILY
    private final double price;

    // Constructor
    public Room(String roomId, Room.RoomType roomType, double price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
    }

    public enum RoomType {
        SINGLE,
        DOUBLE,
        FAMILY
    }
}
