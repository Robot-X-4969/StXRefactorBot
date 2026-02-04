package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XServo {

    private final XOpMode op;
    private final String servoName;
    private ServoImplEx servo;
    private double currentPosition;

    public XServo(XOpMode op, String servoName, double position) {

        this.op = op;
        this.servoName = servoName;
        this.currentPosition = position;

    }

    public void init() {

        servo = op.hardwareMap.get(ServoImplEx.class, servoName);

        setPosition(currentPosition);

    }

    public void setPosition(double position){

        servo.setPosition(position);

        currentPosition = position;

    }

}