package org.firstinspires.ftc.teamcode.source.systems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;

public class Flywheel extends XSystem {

    XMotor motor1, motor2;
    XServo servo1, servo2;

    private int index;

    private final double[] MOTOR_SPEEDS = new double[]{
            0.0,
            750.0,
            825.0,
            1025.0,
            2000.0
    };

    private final double[] SERVO_POSITIONS = new double[]{
            0.0,
            0.20,
            0.125,
            0.16,
            0.16
    };

    public Flywheel(XOpMode op) {

        super(op);

    }

    @Override
    public void init(){

        motor1 = new XMotor(op, "flywheel1");
        motor2 = new XMotor(op, "flywheel2");

        motor1.setBrakes(false);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setReverse(false);

        motor2.setBrakes(false);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setReverse(true);


        servo1 = new XServo(op, "hood1");
        servo1.init();

        servo2 = new XServo(op, "hood2");
        servo2.init();
        servo2.setReverse(true);

        increment(0);

    }

    @Override
    public void loop(){

        super.loop();

        setPIDFCoefficients();

    }

    @Override
    public void control_loop(){


        if (driverStation.getGamepad1().getDpadLeft().wasPressed()) {

            increment(-1);

        } else if (driverStation.getGamepad1().getDpadLeft().wasPressed()) {

            increment(1);

        }

    }

    public void increment(int increment) {

        index = Math.max(0, Math.min(index + increment, MOTOR_SPEEDS.length - 1));

        motor1.setRpm(MOTOR_SPEEDS[index]);
        motor2.setRpm(MOTOR_SPEEDS[index]);

        servo1.setPosition(SERVO_POSITIONS[index]);
        servo2.setPosition(SERVO_POSITIONS[index]);
    }

    public void setPIDFCoefficients() {

        double F = 32767 / motor1.getMaxTicksPerSecond() * (motor1.getNominalVoltage() / motor1.getVoltage());
        double P = 0.2 * F;
        double I = 0.0 * P;
        double D = 0.0;

        motor1.setPidfCoefficients(P, I, D, F);
        motor2.setPidfCoefficients(P, I, D, F);

    }

}