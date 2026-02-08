package org.firstinspires.ftc.teamcode.source.opmodes.autonops;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.libs.templates.XAuton;

public class AutonBlueBasket extends XAuton {

    enum StateMachine {

        DRIVE_START,
        SHOOT

    }

    private StateMachine currentState;
    private PathChain driveStart;

    @Override
    public void init_modules(){

    }

    @Override
    public void initialize(){

        super.init();

        this.startingPose = new Pose(20.75510204081633, 122.6938775510204, Math.toRadians(136.0));

        this.actionPose = new Pose(66.10204081632654, 77.51020408163265, Math.toRadians(136.0));

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
