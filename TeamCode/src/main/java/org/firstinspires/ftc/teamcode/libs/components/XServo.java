package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpContext;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XServo {

    private final XOpContext context;
    private final String servoName;
    private ServoImplEx servo;
    private double currentPosition;

    public XServo(XOpContext ctx, String servoName, double position) {

        this.context = ctx;
        this.servoName = servoName;
        this.currentPosition = position;

    }

    public void init() {

        servo = context.getContextHardwareMap().get(ServoImplEx.class, servoName);

        setPosition(currentPosition);

    }

    public void setPosition(double position){

        servo.setPosition(position);

        currentPosition = position;

    }

}