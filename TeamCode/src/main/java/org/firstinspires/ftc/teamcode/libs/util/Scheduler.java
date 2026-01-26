package org.firstinspires.ftc.teamcode.libs.util;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private final List<Event> events = new ArrayList<>();
    private final List<String> IDs = new ArrayList<>();

    public Scheduler(){

    }

    public Event schedule_event(long millis, Runnable runnable){

        //creates new event based on parameter

        String ID = Event.randomID(5);

        while(IDs.contains(ID)){

            ID = Event.randomID(5);

        }

        Event event = new Event(millis, ID, runnable);

        events.add(event);

        IDs.add(event.getID());

        return event;

    }

    public Event set_event(Long millis, String id, Runnable runnable) {

        Event event = get_event(id);

        if (event != null) {
            cancel(id);
            event = new Event(millis == null ? event.getStopwatch().remainingMillis() : millis,
                    id, runnable == null ? event.event : runnable);
            return schedule_event(event);
        }
        return schedule(millis, id, runnable);
    }

    public Event get_event(String ID) {

        if (IDs.contains(ID)) {

            return events.get(IDs.indexOf(ID));

        }

        return null;

    }

    public void cancel(String ID) {

        if (IDs.contains(ID)) {

            events.remove(events.get(IDs.indexOf(ID)));
            IDs.remove(ID);

        }

    }

}
