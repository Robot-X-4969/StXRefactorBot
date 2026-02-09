package org.firstinspires.ftc.teamcode.source.opmodes.auton;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XAuton;
import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;
import org.firstinspires.ftc.teamcode.source.systems.Flywheel;
import org.firstinspires.ftc.teamcode.source.systems.IntakeSystem;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

public class AutonBlueBasket extends XAuton {

    enum StateMachine {

        DRIVE_START,
        SHOOT

    }

    private StateMachine currentState;
    private PathChain driveStart;

    MecanumDrive drive = new MecanumDrive(this);
    CameraSystem cameraSystem = new CameraSystem(this, drive);
    Flywheel flywheel = new Flywheel(this, cameraSystem);
    Spindexer spindexer = new Spindexer(this);
    IntakeSystem intakeSystem = new IntakeSystem(this);

    @Override
    public void init_modules(){

        registerModule(drive, XSystemManager.ModuleType.ACTIVE);
        registerModule(cameraSystem, XSystemManager.ModuleType.ACTIVE);
        registerModule(flywheel, XSystemManager.ModuleType.ACTIVE);
        registerModule(spindexer, XSystemManager.ModuleType.ACTIVE);
        registerModule(intakeSystem, XSystemManager.ModuleType.ACTIVE);

    }

    @Override
    public void initialize(){

        super.init();

        this.startingPose = new Pose(20.75510204081633, 122.6938775510204, Math.toRadians(144.0));

        this.actionPose = new Pose(66.10204081632654, 77.51020408163265, Math.toRadians(144.0));

        driveStart = follower.pathBuilder()
                .addPath(new BezierLine(startingPose, actionPose))
                .setLinearHeadingInterpolation(startingPose.getHeading(), actionPose.getHeading())
                .build();

    }

    @Override
    public void run(){


    }

    public void pathUpdater(){
        switch(currentState){

            case DRIVE_START:

                follower.followPath(driveStart, true);

                currentState = StateMachine.SHOOT;

                break;

            case SHOOT:

                if(!follower.isBusy()){



                }
        }


    }


}
