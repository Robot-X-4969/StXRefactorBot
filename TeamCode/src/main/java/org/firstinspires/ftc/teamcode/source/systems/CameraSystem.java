package org.firstinspires.ftc.teamcode.source.systems;

import org.firstinspires.ftc.teamcode.libs.components.XCamera;
import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XModule;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public class CameraSystem extends XModule {

    private XCamera camera;

    private final MecanumDrive drive;

    private boolean isAligning;

    public CameraSystem(XOpMode op, MecanumDrive drive) {

        super(op);

        this.drive = drive;

    }

    @Override
    public void init(Scheduler scheduler, XDriverStation driverStation){

        super.init(scheduler, driverStation);

        this.camera = new XCamera(op, "limelight");

        camera.init();

        this.camera.setPipeline(2);

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

        if(camera.seesAprilTag(20) && op.getXDriverStation().getGamepad1().getA().wasPressed() && !isAligning){

            isAligning = true;

            drive.setAllowedRotation(false);

        }

    }

    @Override
    public void displayTelemetry(){

        op.getTelemetry().addData("Camera Sees Tag 20: ", camera.seesAprilTag(20));


        op.getTelemetry().addData("Tag 20 X Angle: ", "N/A");

        op.getTelemetry().addData("Is Aligning: ", isAligning);


    }

    public void autoAlign(double xAngle){

        double kP = 0.03;

        if(xAngle > 1.0){

            double power = Math.max(xAngle * kP, 0.1);

            drive.setAutoR(power);

        } else if(xAngle < -1.0){

            double power = Math.min(xAngle * kP, -0.1);

            drive.setAutoR(power);

        } else {

            drive.setAutoR(0);

            drive.setAllowedRotation(true);

            isAligning = false;

        }

    }

    public XCamera getCamera(){

        return camera;

    }

}
