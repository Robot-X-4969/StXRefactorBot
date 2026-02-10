package org.firstinspires.ftc.teamcode.libs.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XTeleOp extends OpMode implements XOpMode {

    private final XModuleManager manager = new XModuleManager(this);

    private XDriverStation driverStation;

    private Scheduler scheduler;

    public void init_modules(){


    }

    @Override
    public void init() {

        driverStation = new XDriverStation(gamepad1, gamepad2);
        scheduler = new Scheduler();

        init_modules();

        for(XModule system : manager.getActiveSystems()){

            system.init(scheduler, driverStation);

        }

        for(XModule system : manager.getInactiveSystems()){

            system.init(scheduler, driverStation);

        }

    }

    @Override
    public void init_loop() {

        scheduler.loop();

        driverStation.update();

        telemetry.update();

        if(driverStation.getGamepad1() != null) {

            telemetry.addData("GP1:", "Connected");

        } else {

            telemetry.addData("GP1:", "Disconnected");

        }

        for (XModule system : manager.getActiveSystems()) {

            system.init_loop();

        }

    }

    @Override
    public void start() {

        for(XModule system : manager.getActiveSystems()){

            system.start();

        }

    }

    @Override
    public void loop() {

        scheduler.loop();

        if (driverStation != null) driverStation.update();

        for(XModule system : manager.getActiveSystems()){

            system.loop();

        }

        displayTelemetry();

    }

    @Override
    public void stop() {

        for(XModule system : manager.getActiveSystems()){

            system.stop();

        }

    }

    public void displayTelemetry(){

        telemetry.update();

    }

    public void registerModule(XModule module, XModuleManager.ModuleType type){

        manager.register_module(module, type);

    }

    @Override
    public Scheduler getScheduler() {

        return scheduler;

    }

    @Override
    public XDriverStation getXDriverStation() {

        return driverStation;

    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public Telemetry getTelemetry() {
        return telemetry;
    }

}
