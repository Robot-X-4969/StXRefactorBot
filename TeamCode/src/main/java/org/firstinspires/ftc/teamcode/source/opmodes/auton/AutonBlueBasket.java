package org.firstinspires.ftc.teamcode.source.opmodes.auton;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XAuton;
import org.firstinspires.ftc.teamcode.libs.templates.XModuleManager;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;
import org.firstinspires.ftc.teamcode.source.systems.Flywheel;
import org.firstinspires.ftc.teamcode.source.systems.IntakeSystem;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

public class AutonBlueBasket extends XAuton {

    enum StateMachine {

        DRIVE_START,
        SHOOT_1,
        COLLECT_BALLS_1,
        RESET_1,
        SHOOT_2,
        COLLECT_BALLS_2,
        RESET_2,
        SHOOT_3,
        COLLECT_BALLS_3,
        RESET_3,
        SHOOT_4

    }

    private StateMachine currentState;
    private PathChain driveStart;
    private PathChain collectBalls1;
    private PathChain reset1;
    private PathChain collectBalls2;
    private PathChain reset2;

    private PathChain collectBalls3;
    private PathChain reset3;




    MecanumDrive drive = new MecanumDrive(this);
    CameraSystem cameraSystem = new CameraSystem(this, drive);
    Flywheel flywheel = new Flywheel(this, cameraSystem);
    Spindexer spindexer = new Spindexer(this);
    IntakeSystem intakeSystem = new IntakeSystem(this);

    public void buildPaths(){

        Pose startPosition = new Pose(20.551020408163268, 122.24489795918367, Math.toRadians(144.0));
        Pose shootPosition = new Pose(60.04081632653061, 83.7755102040816, Math.toRadians(136.0));
        Pose row1 = new Pose (39.67346938775509, 83.7755102040816, Math.toRadians(180.0));
        Pose row2 = new Pose(39.67346938775509, 59.755102040816325, Math.toRadians(180.0));
        Pose row3 = new Pose(39.67346938775509, 35.61224489795918, Math.toRadians(180.0));
        Pose collect1 = new Pose(15.73469387755102, 83.7755102040816, Math.toRadians(180.0));
        Pose collect2 = new Pose(15.73469387755102, 59.755102040816325, Math.toRadians(180.0));
        Pose collect3 = new Pose(15.73469387755102, 35.61224489795918, Math.toRadians(180.0));

        driveStart = follower
                .pathBuilder()
                .addPath(new BezierLine(startPosition, shootPosition))
                .setLinearHeadingInterpolation(startPosition.getHeading(), shootPosition.getHeading())
                .build();

        collectBalls1 = follower
                .pathBuilder()
                .addPath(new BezierLine(shootPosition, row1))
                .setLinearHeadingInterpolation(shootPosition.getHeading(), row1.getHeading())
                .addPath(new BezierLine(row1, collect1))
                .setLinearHeadingInterpolation(row1.getHeading(), collect1.getHeading())
                .build();

        reset1 = follower
                .pathBuilder()
                .addPath(new BezierLine(collect1, shootPosition))
                .setLinearHeadingInterpolation(collect1.getHeading(), shootPosition.getHeading())
                .build();

        collectBalls2 = follower
                .pathBuilder()
                .addPath(new BezierLine(shootPosition, row2))
                .setLinearHeadingInterpolation(shootPosition.getHeading(), row2.getHeading())
                .addPath(new BezierLine(row2, collect2))
                .setLinearHeadingInterpolation(row2.getHeading(), collect2.getHeading())
                .build();

        reset2 = follower
                .pathBuilder()
                .addPath(new BezierLine(collect2, shootPosition))
                .setLinearHeadingInterpolation(collect1.getHeading(), shootPosition.getHeading())
                .build();

        collectBalls3 = follower
                .pathBuilder()
                .addPath(new BezierLine(shootPosition, row3))
                .setLinearHeadingInterpolation(shootPosition.getHeading(), row3.getHeading())
                .addPath(new BezierLine(row3, collect3))
                .setLinearHeadingInterpolation(row3.getHeading(), collect3.getHeading())
                .build();

        reset3 = follower
                .pathBuilder()
                .addPath(new BezierLine(collect3, shootPosition))
                .setLinearHeadingInterpolation(collect1.getHeading(), shootPosition.getHeading())
                .build();

    }

    @Override
    public void init_modules(){

        registerModule(drive, XModuleManager.ModuleType.ACTIVE);
        registerModule(cameraSystem, XModuleManager.ModuleType.ACTIVE);
        registerModule(flywheel, XModuleManager.ModuleType.ACTIVE);
        registerModule(spindexer, XModuleManager.ModuleType.ACTIVE);
        registerModule(intakeSystem, XModuleManager.ModuleType.ACTIVE);

    }

    @Override
    public void initialize(){

        super.init();

        buildPaths();

    }

    @Override
    public void run(){
        switch(currentState){

            case DRIVE_START:

                follower.followPath(driveStart, true);

                currentState = StateMachine.SHOOT_1;

                break;

            case SHOOT_1:

                if(!follower.isBusy()){

                    telemetry.addLine("shot once");
                    currentState = StateMachine.COLLECT_BALLS_1;

                }

                break;

            case COLLECT_BALLS_1:

                if(!follower.isBusy()){

                    telemetry.addLine("collecting");
                    follower.followPath(collectBalls1, true);
                    currentState = StateMachine.RESET_1;

                }

                break;

            case RESET_1:

                if(!follower.isBusy()){

                    telemetry.addLine("resetting");
                    follower.followPath(reset1, true);
                    currentState = StateMachine.SHOOT_2;

                }

                break;

            case SHOOT_2:

                if(!follower.isBusy()){

                    telemetry.addLine("shot twice");
                    currentState = StateMachine.COLLECT_BALLS_2;

                }

                break;

            case COLLECT_BALLS_2:

                if(!follower.isBusy()){

                    telemetry.addLine("collecting");
                    follower.followPath(collectBalls2, true);
                    currentState = StateMachine.RESET_2;

                }

                break;

            case RESET_2:

                if(!follower.isBusy()){

                    telemetry.addLine("resetting");
                    follower.followPath(reset2, true);
                    currentState = StateMachine.SHOOT_3;

                }

                break;

            case SHOOT_3:

                if(!follower.isBusy()){

                    telemetry.addLine("shot thrice");
                    currentState = StateMachine.COLLECT_BALLS_3;

                }

                break;

            case COLLECT_BALLS_3:

                if(!follower.isBusy()){

                    telemetry.addLine("collecting");
                    follower.followPath(collectBalls3, true);
                    currentState = StateMachine.RESET_3;

                }

                break;

            case RESET_3:

                if(!follower.isBusy()){

                    telemetry.addLine("resetting");
                    follower.followPath(reset3, true);
                    currentState = StateMachine.SHOOT_4;

                }

                break;

            case SHOOT_4:

                if(!follower.isBusy()){

                    telemetry.addLine("shot final");

                }

                break;

        }

    }


}
