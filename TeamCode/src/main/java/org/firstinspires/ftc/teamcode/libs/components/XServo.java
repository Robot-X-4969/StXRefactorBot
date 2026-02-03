package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XServo {

    private final XOpMode op;
    private final String servoName;
    private final double[] positions;

    private ServoImplEx servo;
    private int index;

    public XServo(XOpMode op, String servoName, double[] positions) {

        this.op = op;
        this.servoName = servoName;
        this.positions = positions;

    }

    public void init() {

        servo = op.hardwareMap.get(ServoImplEx.class, servoName);

        index = 0;

        servo.setPosition(positions[index]);

    }

    public void setIndex(int index) {

        this.index = index;

        servo.setPosition(positions[index]);

    }

}