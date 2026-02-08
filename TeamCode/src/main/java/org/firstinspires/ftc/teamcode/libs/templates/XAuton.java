package org.firstinspires.ftc.teamcode.libs.templates;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

enum XAutonType {

}

public abstract class XAuton extends LinearOpMode {

    private final XRobotContext context = new XRobotContext(this);

    protected final Scheduler scheduler = new Scheduler();

    protected final XDriverStation driverStation = new XDriverStation(gamepad1, gamepad2);

    protected Follower follower;

    protected Pose startingPose;

    protected Pose actionPose;

    @Override
    public void runOpMode(){

        initialize();

        waitForStart();

        while (opModeIsActive()){

            run();

        }

    }
    public void init_modules(){


    }

    public void initialize(){

        init_modules();

        for(XSystem system : context.getAutonomous_systems()) {

            system.init(this.scheduler, this.driverStation);

        }

    }

    public void run(){



    }
}
