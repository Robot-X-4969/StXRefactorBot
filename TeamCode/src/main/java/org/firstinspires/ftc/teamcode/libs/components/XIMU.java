package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.libs.templates.XOpContext;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XIMU {

    private final XOpContext context;
    private final String imuPath;

    private IMU imu;

    public XIMU(XOpContext ctx, String imuPath) {

        this.context = ctx;
        this.imuPath = imuPath;

    }

    public void init(){

        imu = context.getContextHardwareMap().get(IMU.class, imuPath);

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