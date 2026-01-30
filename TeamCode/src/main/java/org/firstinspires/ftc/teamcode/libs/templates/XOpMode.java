package org.firstinspires.ftc.teamcode.libs.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XOpMode extends OpMode {

    private final XRobotContext context = new XRobotContext(this);

    // Don't construct XDriverStation at field init time (gamepad1/gamepad2 may be null).
    private XDriverStation driverStation;

    private Scheduler scheduler = new Scheduler();

    public void init_modules(){


    }

    @Override
    public void init() {

        driverStation = new XDriverStation(gamepad1, gamepad2);

        init_modules();

        for(XSystem system : context.getActiveSystems()){

            system.init();

        }

        for(XSystem system : context.getInactiveSystems()){

            system.init();

        }

    }

    @Override
    public void init_loop() {

        scheduler.loop();

        driverStation.update();

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

    }

    @Override
    public void stop() {

        for(XSystem system : context.getActiveSystems()){

            system.stop();

        }

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

}
