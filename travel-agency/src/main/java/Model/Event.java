package Model;

import Model.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class Event {
    private String name;
    private String location;
    private Date date;

    // Constructor
    public Event(String eventId, String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public Event(String name, String location, Date date) {
    }

}

