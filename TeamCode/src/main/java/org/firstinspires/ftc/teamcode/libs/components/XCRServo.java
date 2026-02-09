package org.firstinspires.ftc.teamcode.libs.components;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import com.qualcomm.robotcore.hardware.CRServoImplEx;

public class XCRServo {

    private final XOpMode op;

    private final String servoName;

    private CRServoImplEx crServo;

    private double power;

    public XCRServo(XOpMode op, String servo_name) {

        this.op = op;

        this.servoName = servo_name;

    }

    public void init(){

        this.crServo = op.getHardwareMap().get(CRServoImplEx.class, servoName);

        power = 0.0;

    }

    public void rotate(double power){

        this.power = power;

        crServo.setPower(power);

    }

    public void stop(){

        this.power = 0.0;

        crServo.setPower(0.0);

    }

}
