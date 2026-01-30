package org.firstinspires.ftc.teamcode.libs.tuning;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PIDFTuner {

    private FileWriter file_writer;
    private final double kP;
    private final double kI;
    private final double kD;
    private final double kF;

    private double P;
    private double I;
    private double D;
    private double F;

    private double last_error;

    private final double delta_time;
    private double total_time = 0.0;

    public PIDFTuner(double kP, double kI, double kD, double kF, double delta_time, Context context, String file_name){

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;

        this.delta_time = delta_time;
        this.total_time = 0.0;

        this.P = 0.0;
        this.I = 0.0;
        this.D = 0.0;

        try {

            File file = new File(context.getFilesDir(), file_name + Math.random() * 100 + ".csv");

            file_writer = new FileWriter(file);

            file_writer.append("time,targetVel,actualVel,P,I,D,F\n");

        } catch (IOException ignored){

        }

    }

    public void graph_PIDF(double current_velocity, double target_velocity){

        double error = target_velocity - current_velocity;

        P = kP * error;

        I += kI * error * delta_time;

        D = kD * (error - last_error) / delta_time;

        F = kF * target_velocity;

        total_time += delta_time;

        last_error = error;

        try {

            file_writer.append(String.valueOf(total_time)).append(",").append(String.valueOf(target_velocity)).append(",").append(String.valueOf(current_velocity)).append(",").append(String.valueOf(P)).append(",").append(String.valueOf(I)).append(",").append(String.valueOf(D)).append(",").append(String.valueOf(F)).append("\n");

        } catch (IOException ignored){

        }

    }

    public void close(){

        try{
            file_writer.flush();
            file_writer.close();

        } catch (IOException ignored){

        }

    }

}

