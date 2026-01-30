package org.firstinspires.ftc.teamcode.libs.util;

import java.util.HashMap;
import java.util.Iterator;

public class Scheduler {

    private final HashMap<String, Event> eventMap = new HashMap<>();

    public Scheduler(){

    }

    public Event scheduleEvent(long millis, Runnable runnable){

        //creates new event based on parameter

        String ID = Event.randomID(5);

        while(eventMap.containsKey(ID)){

            ID = Event.randomID(5);

        }

        Event event = new Event(millis, ID, runnable);

        eventMap.put(ID, event);

        return event;

    }

    public Event scheduleEvent(long millis, String ID, Runnable runnable) {

        if (!eventMap.containsKey(ID)) {

            Event event = new Event(millis, ID, runnable);

            eventMap.put(ID, event);

            return event;

        }

        return null;

    }

    public Event setEvent(Long millis, String ID, Runnable runnable) {

        if(ID == null){

            return scheduleEvent(millis, runnable);

        }

        Event event = get_event(ID);

        if (event != null) {

            cancel(ID);

            long new_millis = millis == null ? event.getStopwatch().remainingNanoTime() / 1_000_000 : millis;
            Runnable new_runnable = runnable == null ? event.getEvent() : runnable;

            return scheduleEvent(new_millis, ID, new_runnable);

        }

        return scheduleEvent(millis, ID, runnable);

    }

    public Event get_event(String ID) {

        return eventMap.get(ID);

    }

    public void cancel(String ID) {
        
        eventMap.remove(ID);
        
    }

    public void loop() {

        Iterator<Event> iterator = eventMap.values().iterator();

        while(iterator.hasNext()){

            Event event = iterator.next();

            if (event.getStopwatch().isTimerDone()){

                iterator.remove();
                event.run();


            }
        }

    }

}
