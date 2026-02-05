package org.firstinspires.ftc.teamcode.libs.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XOpMode extends OpMode {

    private final XRobotContext context = new XRobotContext(this);

    private XDriverStation driverStation;

    private Scheduler scheduler;

    public void init_modules(){


    }

    @Override
    public void init() {

        driverStation = new XDriverStation(gamepad1, gamepad2);
        scheduler = new Scheduler();

        init_modules();

        for(XSystem system : context.getActiveSystems()){

            system.init(scheduler, driverStation);

        }

        for(XSystem system : context.getInactiveSystems()){

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

        for (XSystem system : context.getActiveSystems()) {

            system.init_loop();

        }

    }

    @Override
    public void start() {

        for(XSystem system : context.getActiveSystems()){

            system.start();

        }

    }

    @Override
    public void loop() {

        scheduler.loop();

        if (driverStation != null) driverStation.update();

        for(XSystem system : context.getActiveSystems()){

            system.loop();

        }

        displayTelemetry();

    }

    @Override
    public void stop() {

        for(XSystem system : context.getActiveSystems()){

            system.stop();

        }

    }

    public void displayTelemetry(){

        telemetry.update();


    }

    public void registerModule(XSystem module, XRobotContext.ModuleType type){

        context.register_module(module, type);

    }

    public XDriverStation getDriverStation(){

        return driverStation;

    }

    public Scheduler getScheduler() {

        return scheduler;

    }

    public XDriverStation getXDriverStation() {

        return driverStation;

    }

}
