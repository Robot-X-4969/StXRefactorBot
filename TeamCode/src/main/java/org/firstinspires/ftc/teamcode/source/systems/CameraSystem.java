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

    private double lastError;

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
    public void start(){

        lastError = 0.0;

    }

    @Override
    public void loop(double deltaTime){

        super.loop(deltaTime);

        camera.loop();

        if(isAligning && camera.seesAprilTag(20)){

            double xAngle = camera.getTx(camera.getAprilTagIndex(20));

            autoAlign(xAngle, deltaTime);

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

    public void autoAlign(double xAngle, double deltaTime){

        double kP = 0.02;
        double kD = 0.0005;

        double error = xAngle;

        double P = error * kP;
        double D = ((error - lastError)) / deltaTime * kD;

        double power = P + D;

        if(Math.abs(xAngle) > 3.0){

            if(power > 0){

                power += 0.005;

            } else if (power < 0){

                power -= 0.005;

            }

            drive.setAutoR(power);

        } else {

            drive.setAutoR(0);

            drive.setAllowedRotation(true);

            isAligning = false;

        }

        lastError = error;

    }

    public XCamera getCamera(){

        return camera;

    }

}
