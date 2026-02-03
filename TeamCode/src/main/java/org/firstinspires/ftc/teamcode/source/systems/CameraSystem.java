package org.firstinspires.ftc.teamcode.source.systems;

import org.firstinspires.ftc.teamcode.libs.components.XCamera;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;

public class CameraSystem extends XSystem {

    private XCamera camera;

    private MecanumOrientationDrive drive;

    private boolean isAligning;

    public CameraSystem(XOpMode op, MecanumOrientationDrive drive) {

        super(op);

        this.drive = drive;

    }

    @Override
    public void init(){

        this.camera = new XCamera(op, "limelight");

        camera.init();

        isAligning = false;

    }

    @Override
    public void loop(){

        super.loop();

        camera.loop();

        if(isAligning && camera.seesAprilTag(20)){

            double xAngle = camera.getTx(camera.getAprilTagIndex(20));

            autoAlign(xAngle);

        } else if (isAligning && !camera.seesAprilTag(20)) {

            isAligning = false;

            drive.setAllowedRotation(true);

        }

    }

    @Override
    public void control_loop()  {

        if(camera.seesAprilTag(20) && driverStation.getGamepad1().getA().wasPressed() && !isAligning){

            isAligning = true;

            drive.setAllowedRotation(false);

        }


    }

    public void autoAlign(double xAngle){

        double kP = 0.03;

        if(xAngle > 1.0){

            double power = Math.max(xAngle * kP, 0.1);

            drive.setR(power);

        } else if(xAngle < -1.0){

            double power = Math.min(xAngle * kP, -0.1);

            drive.setR(power);

        } else {

            drive.setR(0);

            drive.setAllowedRotation(true);

            isAligning = false;

        }

    }

}
