package org.firstinspires.ftc.teamcode.libs.templates;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.ArrayList;
import java.util.List;

public abstract class XSystem {

    protected final XOpMode op;

    protected final Scheduler scheduler;

    protected final XDriverStation driverStation;

    public XSystem(XOpMode op) {

        this.op = op;
        this.scheduler = op.getScheduler();
        this.driverStation = op.getDriverStation();

    }

    public void init(){


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

}
