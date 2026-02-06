package org.firstinspires.ftc.teamcode.source.systems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public class Flywheel extends XSystem {

    XMotor motor1, motor2;
    XServo servo1, servo2;

    CameraSystem cameraSystem;

    private double angleOffset = 17.0;

    private double ratio = 2.0;

    private int index;

    private double lastError;

    private double theta;

    private double velocity;

    private double distance;
    private double phi = -45;

    private double ta = 0.0;

    private double F;
    private double I;

    private double P;

    private double D;

    private double power;

    private double lastTime;


    private final double[] MOTOR_SPEEDS = new double[]{
            0.0,
            3000.0 ,
            825.0,
            1025.0,
            10000.0
    };

    private final double[] SERVO_POSITIONS = new double[]{
            0.0,
            0.20,
            0.125,
            0.16,
            0.16
    };

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

        increment(0);

        //setPIDFCoefficients();

    }

    @Override
    public void loop(){

        double currentTime = System.currentTimeMillis() / 1000.0;
        double deltaTime = currentTime - lastTime;

        PIDF(deltaTime);

        super.loop();

        lastTime = currentTime;

        calculateAngle();
        calculateAngle();


        //setPIDFCoefficients();

    }

    @Override
    public void start(){

        lastTime = System.currentTimeMillis() / 1000.0;

    }

    @Override
    public void control_loop(){

        if (driverStation.getGamepad1().getDpadLeft().wasPressed()) {

            increment(-1);

        } else if (driverStation.getGamepad1().getDpadRight().wasPressed()) {

            increment(1);

        }

    }

    @Override
    public void stop(){

        motor1.setPower(MOTOR_SPEEDS[0]);
        motor2.setPower(MOTOR_SPEEDS[0]);

        servo1.setPosition(SERVO_POSITIONS[0]);
        servo2.setPosition(SERVO_POSITIONS[0]);

    }

    @Override
    public void displayTelemetry(){

        op.telemetry.addData("Flywheel Target RPM: ", MOTOR_SPEEDS[index]);
        op.telemetry.addData("Flywheel 1 Current RPM: ", motor1.getCurrentRPM());
        op.telemetry.addData("Flywheel 2 Current RPM: ", motor2.getCurrentRPM());
        op.telemetry.addData("F", F);
        op.telemetry.addData("P", P);
        op.telemetry.addData("I", I);
        op.telemetry.addData("D", D);
        op.telemetry.addData("Power", power);
        op.telemetry.addData("theta", theta);
        op.telemetry.addData("distance", distance);
        op.telemetry.addData("ta", ta);


    }

    public void increment(int increment) {

        index = Math.max(0, Math.min(index + increment, MOTOR_SPEEDS.length - 1));

        //motor1.setRpm(MOTOR_SPEEDS[index] * ratio);
        //motor2.setRpm(MOTOR_SPEEDS[index] * ratio);

        servo1.setPosition(SERVO_POSITIONS[index]);
        servo2.setPosition(0.99 - SERVO_POSITIONS[index]);
    }

    public void setPIDFCoefficients() {

        /*
        double F = (32767 / motor1.getMaxTicksPerSecond()) * (motor1.getNominalVoltage() / motor1.getVoltage());
        double P = 0.00 * F;
        double I = 0.0 * P;
        double D = 0.0;

        motor1.setPidfCoefficients(P, I, D, F);
        motor2.setPidfCoefficients(P, I, D, F);

         */

    }

    public void PIDF(double deltaTime) {

        double ratio = 1.0 / 3.0;
        double targetRPM = MOTOR_SPEEDS[index] * ratio;

        double error = targetRPM - motor2.getCurrentRPM();

        F = (1.0 / motor2.getMaxRPM()) * targetRPM;

        P = 0.0055 * error;
        I += 0.0 * error * deltaTime;
        D = 0.0 * ((error - lastError) / deltaTime);

        power = (P + I + D + F);
        motor1.setPower(power);
        motor2.setPower(power);

        lastError = error;

    }

    public void calculateAngle(){

        double g = 9.8;

        double h = 0.99;

        int index = cameraSystem.getCamera().getAprilTagIndex(20);

        if(index != -1){

            distance = 1.5915 * Math.pow(cameraSystem.getCamera().getTa(index) * 100.0, -0.601);

            ta = cameraSystem.getCamera().getTa(index);

            theta = (Math.toDegrees(Math.atan((2.0 * h / distance) - Math.tan(Math.toRadians(phi)))) - angleOffset) / 130.0 * 2.0 ;



        } else {

            theta = 0.0;

            distance = 0.0;

            ta = 0.0;

        }

        servo1.setPosition(theta);
        servo2.setPosition(0.99 - theta);

    }

}