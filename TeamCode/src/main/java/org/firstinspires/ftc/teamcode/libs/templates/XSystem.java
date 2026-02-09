package org.firstinspires.ftc.teamcode.libs.templates;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XSystem {

    protected final XOpMode op;

    protected Scheduler scheduler;

    protected XDriverStation driverStation;

    protected boolean isAutonomousSystem;

    public XSystem(XOpMode op){

        this.op = op;

    }

    public void init(Scheduler scheduler, XDriverStation driverStation){

        this.scheduler = scheduler;
        this.driverStation = driverStation;

    }


    public void init_loop(){



    }

    public void start(){



    }

    public void loop(){

        control_loop();


    }

    public void control_loop(){



    }

    public void stop(){



    }

    public void displayTelemetry(){



    }

}
