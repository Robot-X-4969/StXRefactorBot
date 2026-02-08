package org.firstinspires.ftc.teamcode.libs.components;

import org.firstinspires.ftc.teamcode.libs.templates.XOpContext;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import com.qualcomm.robotcore.hardware.CRServoImplEx;

public class XCRServo {

    private final XOpContext context;

    private final String servoName;

    private CRServoImplEx crServo;

    private double power;

    public XCRServo(XOpContext ctx, String servo_name) {

        this.context = ctx;

        this.servoName = servo_name;

    }

    public void init(){

        this.crServo = context.getContextHardwareMap().get(CRServoImplEx.class, servoName);

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
