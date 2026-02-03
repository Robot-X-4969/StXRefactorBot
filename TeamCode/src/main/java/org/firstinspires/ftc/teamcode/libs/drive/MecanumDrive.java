package org.firstinspires.ftc.teamcode.libs.drive;

import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;


public class MecanumDrive extends XSystem {

    private XMotor frontLeft;
    private XMotor frontRight;
    private XMotor backRight;
    private XMotor backLeft;

    private double x;
    private double y;
    private double r;
    private boolean autonomous;
    private double power;
    private boolean slowMode = false;
    private boolean superSlowMode = false;

    public MecanumDrive(XOpMode op, boolean autonomous) {

        super(op);

        this.autonomous = autonomous;

    }

    @Override
    public void init() {

        frontLeft = new XMotor(op, "frontLeft");
        frontRight = new XMotor(op, "frontRight");
        backRight = new XMotor(op, "backRight");
        backLeft = new XMotor(op, "backLeft");

        frontRight.init();
        backRight.init();
        backLeft.init();
        frontLeft.init();

        frontRight.setReverse(true);
        backRight.setReverse(true);
        backLeft.setReverse(true);

    }

    @Override
    public void loop() {

        super.loop();

        powerMotors(0.75);

    }

    @Override
    public void control_loop() {

        updateSticks();

    }

    public void updateSticks() {

        if (!autonomous) {

            x = op.getDriverStation().getGamepad1().getLeftStickX();
            y = op.getDriverStation().getGamepad1().getLeftStickY();
            r = op.getDriverStation().getGamepad1().getRightStickX();

        }

    }

    public void powerMotors(double power) {

        final double s = Math.pow(Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(r))), 2) / ((x * x) + (y * y) + (r * r));

        final double flPow = (y - x - r) * (s) * power;
        final double frPow = (y + x + r) * (s) * power;
        final double brPow = (y - x + r) * (s) * power;
        final double blPow = (y + x - r) * (s) * power;

        frontLeft.setPower(flPow);
        frontRight.setPower(frPow);
        backLeft.setPower(blPow);
        backRight.setPower(brPow);

    }

}