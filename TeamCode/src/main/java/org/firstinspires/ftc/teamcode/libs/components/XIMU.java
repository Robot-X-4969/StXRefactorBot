package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XIMU {

    private final XOpMode op;
    private final String imuPath;

    private IMU imu;

    public XIMU(XOpMode op, String imuPath) {

        this.op = op;
        this.imuPath = imuPath;

    }

    public void init(){

        imu = op.hardwareMap.get(IMU.class, imuPath);

        RevHubOrientationOnRobot.LogoFacingDirection logo = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usb  = RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(logo, usb)));

    }

    public Orientation getOrientation(){

        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

    }

    public double getYaw(){

        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

    }














}