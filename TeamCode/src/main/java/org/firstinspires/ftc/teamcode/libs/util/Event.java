package org.firstinspires.ftc.teamcode.libs.util;

import java.util.Random;

public class Event {

    private String ID;

    private final Runnable event;

    private final Stopwatch stopwatch;

    public Event(long millis, String ID, Runnable event){

        this.ID = ID;
        this.event = event;
        this.stopwatch = new Stopwatch(millis);

    }

    public void run(){

        event.run();

    }

    public static String randomID(int length){

        final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < length; i++){
            final int randomInt = random.nextInt(62);
            builder.append(chars.charAt(randomInt));
        }

        return builder.toString();

    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public Stopwatch getStopwatch(){

        return stopwatch;
    }


}
