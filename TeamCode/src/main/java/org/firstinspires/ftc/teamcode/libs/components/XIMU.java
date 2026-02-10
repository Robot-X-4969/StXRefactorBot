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

    /**
     * Constructor for the XIMU class.
     *
     * @param op      The opmode that this IMU is being used in
     * @param imuPath The name of the IMU in the hardware map
     */
    public XIMU(XOpMode op, String imuPath) {

        this.op = op;
        this.imuPath = imuPath;

    }

    /**
     * Initializes the IMU by retrieving it from the hardware map and setting the orientation parameters.
     */
    public void init() {

        imu = op.getHardwareMap().get(IMU.class, imuPath);

        RevHubOrientationOnRobot.LogoFacingDirection logo = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usb = RevHubOrientationOnRobot.UsbFacingDirection.RIGHT;

        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(logo, usb)));

    }

    //getters
    public Orientation getOrientation() {

        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

    }

}














