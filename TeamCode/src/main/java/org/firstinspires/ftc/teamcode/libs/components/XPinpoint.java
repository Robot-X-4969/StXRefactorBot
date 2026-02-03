package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XPinpoint {

    private final XOpMode op;
    private GoBildaPinpointDriver device;

    //offset of the odometry wheels from the center of the robot
    private final double xOffset;
    private final double yOffset;

    public XPinpoint(XOpMode op, double xOffset, double  yOffset){

        this.op = op;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }

    public void init(){

        device = op.hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        device.setOffsets(xOffset, yOffset, DistanceUnit.MM);

        device.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        device.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);

        device.resetPosAndIMU();

    }

    public void getData(){

        op.telemetry.addData("X coord: ", getX());
        op.telemetry.addData("Y coord: ", getY());
        op.telemetry.addData("direction: ", getHeading());

    }

    public void update(){

        device.update();

    }

    public Pose2D getPose() {

        return device.getPosition();

    }

    public double getX() {

        return getPose().getX(DistanceUnit.INCH);

    }

    public double getY() {

        return getPose().getY(DistanceUnit.INCH);

    }

    public double getHeading() {

        return getPose().getHeading(AngleUnit.DEGREES);

    }

    public void getTelemetryData() {

        op.telemetry.addData("X coordinate: ", getX());
        op.telemetry.addData("Y coordinate: ", getY());
        op.telemetry.addData("direction: ", getHeading());

    }

    public void reset() {

        device.resetPosAndIMU();

    }

}
