package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XServo {

    private final XOpMode op;
    private final String servoName;
    private ServoImplEx servo;
    private double[] positions;
    private double currentPosition;
    private int index;

    public XServo(XOpMode op, String servoName, double[] positions) {

        this.op = op;
        this.servoName = servoName;
        this.positions = positions;

    }

    public XServo(XOpMode op, String servoName){

        this.op = op;

        this.servoName = servoName;

    }

    public void init() {

        servo = op.hardwareMap.get(ServoImplEx.class, servoName);

        index = 0;

        setIndex(index);

    }

    public void setIndex(int index) {

        this.index = index;

        servo.setPosition(positions[index]);

        currentPosition = positions[index];

    }

    public double getCurrentPosition(){

        return currentPosition;

    }

    public void setPosition(double position){

        servo.setPosition(position);

        currentPosition = position;

    }

    public void setReverse(boolean reverse){

        servo.setDirection(reverse ? ServoImplEx.Direction.REVERSE : ServoImplEx.Direction.FORWARD);

    }

}