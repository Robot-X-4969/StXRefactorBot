package org.firstinspires.ftc.teamcode.libs.templates;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XAuton extends LinearOpMode implements XOpMode {

    private final XModuleManager manager = new XModuleManager(this);

    protected Scheduler scheduler;

    protected XDriverStation driverStation;

    protected Follower follower;

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

        driverStation = new XDriverStation(gamepad1, gamepad2);
        scheduler = new Scheduler();

        init_modules();

        for(XModule system : manager.getActiveModules()) {

            system.init(this.scheduler, this.driverStation);

        }

        for(XModule system : manager.getInactiveModules()){

            system.init(this.scheduler, this.driverStation);

        }

    }

    public void run(){



    }

    public void registerModule(XModule module, XModuleManager.ModuleType type){

        manager.register_module(module, type);

    }

    @Override
    public HardwareMap getHardwareMap() {

        return hardwareMap;

    }

    @Override
    public Telemetry getTelemetry() {

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
