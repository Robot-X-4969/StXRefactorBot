package org.firstinspires.ftc.teamcode.libs.util;

public class Stopwatch {

    private long startTime;

    private long targetTime;

    public Stopwatch(long millis){

        startTimer(millis);

    }

    public void reset(){

        startTime = System.nanoTime();

    }

    public void clear(){

        targetTime = 0;

    }

    public long elapsedNanoTime(){

        return System.nanoTime() - startTime;

    }

    public long remainingNanoTime(){

        return Math.max(0, targetTime - elapsedNanoTime());

    }

    public void startTimer(long millis){

        reset();

        targetTime = millis * 1_000_000L;

    }

    public boolean isTimerDone(){

        return isTimerRunning() && remainingNanoTime() == 0;

    }

    public boolean isTimerRunning(){

        return targetTime != 0 && remainingNanoTime() > 0;

    }

}
