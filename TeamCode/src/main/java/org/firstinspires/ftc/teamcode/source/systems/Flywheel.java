package org.firstinspires.ftc.teamcode.source.systems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XModule;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public class Flywheel extends XModule {

    XMotor motor1, motor2;
    XServo servo1, servo2;
    CameraSystem cameraSystem;

    private double lastError;
    private double normalizedTheta;
    private double RPM;
    private double F;
    private double I;
    private double P;
    private double D;
    private double power;
    private double lastTime;


    public Flywheel(XOpMode op, CameraSystem cameraSystem) {

        super(op);

        this.cameraSystem = cameraSystem;

    }

    @Override
    public void init(Scheduler scheduler, XDriverStation driverStation){

        super.init(scheduler, driverStation);

        motor1 = new XMotor(op, "flywheel1", 103.8, 1400, 12.0);
        motor2 = new XMotor(op, "flywheel2", 103.8, 1400, 12.0);

        motor1.init();
        motor2.init();

        motor1.setBrakes(false);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor1.setReverse(false);

        motor2.setBrakes(false);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setReverse(false);


        servo1 = new XServo(op, "hood1", 0.0);
        servo1.init();

        servo2 = new XServo(op, "hood2", 0.0);
        servo2.init();


    }

    @Override
    public void loop(){

        double currentTime = System.currentTimeMillis() / 1000.0;
        double deltaTime = currentTime - lastTime;

        calculateAngleAndRPM();

        servo1.setPosition(normalizedTheta);
        servo2.setPosition(0.99 - normalizedTheta);

        powerMotors(deltaTime);

        super.loop();

        lastTime = currentTime;

    }

    @Override
    public void start(){

        lastTime = System.currentTimeMillis() / 1000.0;

    }

    @Override
    public void stop(){

        motor1.setPower(0.0);
        motor2.setPower(0.0);

        servo1.setPosition(0.0);
        servo2.setPosition(0.0);

    }

    @Override
    public void displayTelemetry(){

        op.getTelemetry().addData("Flywheel Target RPM: ", this.RPM);
        op.getTelemetry().addData("Flywheel 1 Current RPM: ", motor1.getCurrentRPM());
        op.getTelemetry().addData("Flywheel 2 Current RPM: ", motor2.getCurrentRPM());
        op.getTelemetry().addData("F", F);
        op.getTelemetry().addData("P", P);
        op.getTelemetry().addData("I", I);
        op.getTelemetry().addData("D", D);
        op.getTelemetry().addData("Power", power);

    }

    public void powerMotors(double deltaTime) {

        double ratio = 1.0 / 3.0;

        double error = (this.RPM * ratio) - motor2.getCurrentRPM();

        F = (1.0 / motor2.getMaxRPM()) * this.RPM;

        P = 0.0055 * error;
        I += 0.0 * error * deltaTime;
        D = 0.0 * ((error - lastError) / deltaTime);

        power = (P + I + D + F);

        motor1.setPower(power);
        motor2.setPower(power);

        lastError = error;

    }

    public void calculateAngleAndRPM(){

        int index = cameraSystem.getCamera().getAprilTagIndex(20);

        if(index != -1){

            final double velocityScalar = 1.0;
            final double distanceScalar = 1.0;

            final double distance = 1.5915 * Math.pow(cameraSystem.getCamera().getTa(index) * 100.0, -0.601) * distanceScalar;
            final double angleOffset = 17.0;
            final double ratio = 2.0;
            final double g = 9.8;
            final double h = 0.99;
            final double phi = -45.0;
            final double pi = 3.1415926535;

            final double wheelRadius = 0.05;

            final double theta = (Math.toDegrees(Math.atan((2.0 * h / distance) - Math.tan(Math.toRadians(phi)))));

            this.normalizedTheta = (theta - angleOffset) * (1.0 / 130.0) * ratio;

            final double velocity = Math.sqrt((g * Math.pow(distance, 2)) / (2.0 * (Math.tan(Math.toRadians(theta)) * distance - h) * Math.pow(Math.cos(Math.toRadians(theta)), 2)));

            final double adjustedVelocity = velocity * velocityScalar;

            this.RPM = (adjustedVelocity * 60.0) / (2 * pi * wheelRadius);

        }

    }

}