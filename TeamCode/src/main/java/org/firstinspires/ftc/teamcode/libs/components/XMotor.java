package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

/**
 * Wrapper class for DcMotorEx to simplify motor control: Allows indefinite, fixed, and timed rotation.
 * <p>
 * Created by John Daniher
 */
public class XMotor {

    private final XOpMode op;
    private final String motorName;
    private final double ticksPerRev;
    private final double maxRPM;
    private final double nominalVoltage;

    private DcMotorEx motor;

    private VoltageSensor voltageSensor;

    private boolean useEncoder;
    private int position;

    /**
     * XMotor Constructor
     *
     * @param op                The OpMode in which this motor runs.
     * @param motorName         The name the motor is configured to through the RevHub.
     * @param ticksPerRev       The number of ticks per revolution for this motor.
     * @param maxRPM            The maximum rotations per minute for this motor.
     * @param nominalVoltage    The nominal voltage for this motor.
     */
    public XMotor(XOpMode op, String motorName, double ticksPerRev, double maxRPM, double nominalVoltage) {

        this.op = op;

        this.motorName = motorName;

        this.ticksPerRev = ticksPerRev;

        this.maxRPM = maxRPM;

        this.nominalVoltage = nominalVoltage;

        this.position = 0;

    }

    /**
     * XMotor Constructor
     *
     * @param op        The OpMode in which this motor runs.
     * @param motorName The name the motor is configured to through the RevHub.
     */
    public XMotor(XOpMode op, String motorName) {

        this.op = op;

        this.motorName = motorName;

        this.ticksPerRev = 0.0;
        this.maxRPM = 0.0;
        this.nominalVoltage = 0.0;

    }

    /**
     * Initialization method for XMotor.
     */
    public void init() {

        this.motor = op.hardwareMap.get(DcMotorEx.class, motorName);
        this.voltageSensor = op.hardwareMap.voltageSensor.iterator().next();

        useEncoder = false;

        reset();

    }

    /**
     * Loop method for XMotor.
     */
    public void loop(){

    }

    /**
     * Sets the target RPM for the motor.
     *
     * @param targetRpm The target RPM for the motor.
     */
    public void setRpm(double targetRpm){

        motor.setVelocity(calculateVelocity(targetRpm));

    }

    /**
     * Sets the PIDF coefficients for velocity control.
     *
     * @param kP The proportional coefficient.
     * @param kI The integral coefficient.
     * @param kD The derivative coefficient.
     * @param kF The feedforward coefficient.
     */
    public void setPidfCoefficients(double kP, double kI, double kD, double kF){

        motor.setVelocityPIDFCoefficients(kP, kI, kD, kF);

    }

    /**
     * Calculates the velocity in ticks per second from RPM.
     *
     * @param rpm The RPM to convert.
     * @return The velocity in ticks per second.
     */
    public double calculateVelocity(double rpm){

        return rpm * ticksPerRev / 60.0;

    }

    /**
     * Sets the zero power behavior of the motor.
     *
     * @param useBrakes If true, sets the motor to brake when power is zero; otherwise, it floats.
     */
    public void setBrakes(boolean useBrakes){

        motor.setZeroPowerBehavior(useBrakes ? DcMotorEx.ZeroPowerBehavior.BRAKE : DcMotorEx.ZeroPowerBehavior.FLOAT);

    }

    /**
     * Sets the power of the motor.
     *
     * @param power The power to set the motor to, ranging from -1.0 to 1.0.
     */
    public void setPower(double power){

        if(power > 1.0){

            power = 1.0;

        } else if(power < -1.0){

            power = -1.0;

        }

        motor.setPower(power);

    }

    /**
     * Sets the motor to rotate to a fixed position with specified power.
     *
     * @param power    The power to set the motor to, ranging from -1.0 to 1.0.
     * @param position The target position in ticks.
     */
    public void setFixedRotation(double power, int position){

        this.position = position;

        motor.setTargetPosition(position);

        setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        setPower(power);

    }

    /**
     * Sets the motor to rotate for a specified duration with given power.
     *
     * @param power  The power to set the motor to, ranging from -1.0 to 1.0.
     * @param millis The duration in milliseconds for which the motor should rotate.
     */
    public void setTimedRotation(double power, long millis){

        setPower(power);

        op.getScheduler().scheduleEvent(millis, "stop_motor_" + motorName, this::stop);

    }

    /**
     * Sets the direction of the motor.
     *
     * @param reverse If true, sets the motor direction to REVERSE; otherwise, FORWARD.
     */
    public void setReverse(boolean reverse){

        motor.setDirection(reverse ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);

    }

    /**
     * Sets the run mode of the motor.
     *
     * @param mode The run mode to set the motor to.
     */
    public void setMode(DcMotorEx.RunMode mode) {

        motor.setMode(mode);

        useEncoder = mode == DcMotorEx.RunMode.RUN_USING_ENCODER;

    }

    /**
     * Resets the motor encoder and sets the run mode based on whether encoders are used.
     */
    public void reset(){

        setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        setMode(useEncoder ? DcMotorEx.RunMode.RUN_USING_ENCODER : DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

    }

    /**
     * Stops the motor.
     */
    public void stop(){

        setPower(0.0);

    }

    //GETTERS
    public double getMaxRPM(){

        return this.maxRPM;

    }

    public double getVoltage(){

        return voltageSensor.getVoltage();

    }

    public double getNominalVoltage(){

        return nominalVoltage;

    }

    public int getPosition(){

        return position;

    }

    public boolean checkBusy(){

        return motor.isBusy();

    }

    public double getCurrentRPM(){

        return (motor.getVelocity() * 60) / ticksPerRev;

    }

}