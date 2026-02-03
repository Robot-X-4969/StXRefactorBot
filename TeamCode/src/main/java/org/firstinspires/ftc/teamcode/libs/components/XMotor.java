package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;


public class XMotor {

    private final XOpMode op;
    private final String motorName;
    private final double ticksPerRev;
    private final double maxTicksPerSecond;
    private final double nominalVoltage;

    private DcMotorEx motor;

    private VoltageSensor voltageSensor;

    private boolean useEncoder;
    private int position;

    public XMotor(XOpMode op, String motorName, double ticksPerRev, double maxTicksPerSecond, double nominalVoltage) {

        this.op = op;

        this.motorName = motorName;

        this.ticksPerRev = ticksPerRev;

        this.maxTicksPerSecond = maxTicksPerSecond;

        this.nominalVoltage = nominalVoltage;

        this.position = 0;

    }

    public XMotor(XOpMode op, String motorName) {

        this.op = op;

        this.motorName = motorName;

        this.ticksPerRev = 0.0;
        this.maxTicksPerSecond = 0.0;
        this.nominalVoltage = 0.0;

    }

    public void init() {

        this.motor = op.hardwareMap.get(DcMotorEx.class, motorName);
        this.voltageSensor = op.hardwareMap.voltageSensor.iterator().next();

        useEncoder = false;

        reset();

    }

    public void loop(){

    }

    public void setRpm(double targetRpm){

        motor.setVelocity(calculateVelocity(targetRpm));

    }

    public void setPidfCoefficients(double kP, double kI, double kD, double kF){

        motor.setVelocityPIDFCoefficients(kP, kI, kD, kF);

    }

    public double calculateVelocity(double rpm){

        return rpm * ticksPerRev / 60.0;

    }

    public void setBrakes(boolean useBrakes){

        motor.setZeroPowerBehavior(useBrakes ? DcMotorEx.ZeroPowerBehavior.BRAKE : DcMotorEx.ZeroPowerBehavior.FLOAT);

    }

    public void setPower(double power){

        if(power > 1.0){

            power = 1.0;

        } else if(power < -1.0){

            power = -1.0;

        }

        motor.setPower(power);

    }

    public void setFixedRotation(double power, int position){

        this.position = position;

        motor.setTargetPosition(position);

        setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        setPower(power);

    }

    public void setTimedRotation(double power, long millis){

        setPower(power);

        op.getScheduler().scheduleEvent(millis, "stop_motor_" + motorName, this::stop);

    }

    public void setReverse(boolean reverse){

        motor.setDirection(reverse ? DcMotorEx.Direction.REVERSE : DcMotorEx.Direction.FORWARD);

    }

    public void setMode(DcMotorEx.RunMode mode) {

        motor.setMode(mode);

        useEncoder = mode == DcMotorEx.RunMode.RUN_USING_ENCODER;

    }

    public void reset(){

        setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        setMode(useEncoder ? DcMotorEx.RunMode.RUN_USING_ENCODER : DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void stop(){

        setPower(0.0);

    }

    public double getMaxTicksPerSecond(){

        return maxTicksPerSecond;

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

}