package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XServo {

    private final XOpMode op;
    private final String servoName;
    private ServoImplEx servo;
    private double currentPosition;

    /**
     * Constructor for the XServo class.
     * @param op The opmode that this servo is being used in
     * @param servoName The name of the servo in the hardware map
     * @param position The initial position of the servo (between 0 and 1)
     */
    public XServo(XOpMode op, String servoName, double position) {

        this.op = op;
        this.servoName = servoName;
        this.currentPosition = position;

    }

    /**
     * Initializes the servo by retrieving it from the hardware map and setting it to the initial position.
     */
    public void init() {

        servo = op.getHardwareMap().get(ServoImplEx.class, servoName);

        setPosition(currentPosition);

    }

    /**
     * Sets the position of the servo and updates the current position variable.
     *
     * @param position The desired position of the servo (between 0 and 1)
     */
    public void setPosition(double position){

        servo.setPosition(position);

        currentPosition = position;

    }

}