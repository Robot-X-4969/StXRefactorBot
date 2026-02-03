import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;

public class Flywheel extends XSystem {

    XMotor motor1, motor2;
    XServo servo1, servo2;

    private final double[] MOTOR_SPEEDS = new double[]{
            0.0, 750.0, 825.0, 1025.0, 2000.0
    };

    private final double[] SERVO_POSITIONS = new double[]{
            0.0, 0.20, 0.125, 0.16, 0.16
    };

    private int index = 0;

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
        motor2.setReverse(false);


        servo1 = new XServo(op, "hood1", SERVO_POSITIONS);
        servo1.init();

        servo2 = new XServo(op, "hood2", SERVO_POSITIONS);
        servo2.init();



        increment(0);
    }
    @Override
    public void loop(){
        super.loop();
        opMode.telemetry.addData("CurrentRPM",motor1.getCurrentRPM(motor1.getCurrentVelocity()));
        opMode.telemetry.addData("TargetRPM", MOTOR_SPEEDS[index]);
        opMode.telemetry.update();

        setPIDFCoefficients();

        //motor1.pidfTuner.graph_PIDF(motor1.getCurrentVelocity()/2800, motor1.calculateVelocity(MOTOR_SPEEDS[index])/2800);
    }

    @Override
    public void control_loop(){
        super.control_loop();

        if (driverStation.getGamepad1().getDpadLeft().wasPressed()) {

            increment(-1);

        } else if (xDS.xGamepad1.dpad_right.wasPressed()) {
            increment(1);
        }
    }

    public void increment(int increment) {
        index = Math.max(0, Math.min(index + increment, MOTOR_SPEEDS.length - 1));

        motor1.setMotorRPM(MOTOR_SPEEDS[index]);
        motor2.setMotorRPM(MOTOR_SPEEDS[index]);

        servo1.setPosition(SERVO_POSITIONS[index]);
        servo2.setPosition(0.99 - SERVO_POSITIONS[index]);
    }

    public void setPIDFCoefficients() {
        double F = 32767 / motor1.getMaxVelocity() * (12.00 / motor1.voltageSensor.getVoltage());
        double P = 0.2 * F;
        double I = 0.0 * P;
        double D = 0.0;

        motor1.setPIDFValues(P, I, D, F);
        motor2.setPIDFValues(P, I, D, F);
    }



}