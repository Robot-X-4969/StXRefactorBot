package org.firstinspires.ftc.teamcode.libs.components;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import com.qualcomm.robotcore.hardware.CRServoImplEx;

public class XCRServo {

    private final XOpMode op;

    private final String servoName;

    private CRServoImplEx crServo;

    private double power;

    /**
     * Constructor for the XCRServo class.
     * @param op The opmode that this servo is being used in
     * @param servoName The name of the servo in the hardware map
     */
    public XCRServo(XOpMode op, String servoName) {

        this.op = op;

        this.servoName = servoName;

    }

    /**
     * Initializes the servo by retrieving it from the hardware map and setting the power to 0.
     */
    public void init(){

        this.crServo = op.getHardwareMap().get(CRServoImplEx.class, servoName);

        power = 0.0;

    }

    /**
     * Sets the power of the servo and updates the current power variable.
     *
     * @param power The desired power of the servo (between -1 and 1)
     */
    public void rotate(double power){

        this.power = power;

        crServo.setPower(power);

    }

    /**
     * Stops the servo by setting the power to 0 and updating the current power variable.
     */
    public void stop(){

        this.power = 0.0;

        crServo.setPower(0.0);

    }

}
