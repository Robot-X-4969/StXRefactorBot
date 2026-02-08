package org.firstinspires.ftc.teamcode.libs.templates;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

enum XAutonType {

}

public abstract class XAuton extends LinearOpMode implements XOpContext {

    private final XSystemManager manager = new XSystemManager(this);

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

        for(XSystem system : manager.getActiveSystems()) {

            system.init(this.scheduler, this.driverStation);

        }

        for(XSystem system : manager.getActiveSystems()){

            system.init(this.scheduler, this.driverStation);

        }

    }

    public void run(){



    }

    public void registerModule(XSystem module, XSystemManager.ModuleType type){

        manager.register_module(module, type);

    }

    @Override
    public HardwareMap getContextHardwareMap() {

        return hardwareMap;

    }

    @Override
    public Telemetry getContextTelemetry() {

        return telemetry;

    }

    @Override
    public XDriverStation getXDriverStation(){

        return this.driverStation;

    }

    @Override
    public Scheduler getScheduler(){

        return this.scheduler;

    }

}
