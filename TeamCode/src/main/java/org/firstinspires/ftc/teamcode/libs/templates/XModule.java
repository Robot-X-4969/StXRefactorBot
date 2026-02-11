package org.firstinspires.ftc.teamcode.libs.templates;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XModule {

    protected final XOpMode op;

    protected Scheduler scheduler;

    protected XDriverStation driverStation;


    public XModule(XOpMode op){

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

    public void loop(double deltaTime){

        control_loop();


    }

    public void control_loop(){



    }

    public void stop(){



    }

    public void displayTelemetry(){



    }

}
