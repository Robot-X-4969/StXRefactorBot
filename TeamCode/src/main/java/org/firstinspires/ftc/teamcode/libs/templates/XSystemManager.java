package org.firstinspires.ftc.teamcode.libs.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XSystemManager {

    public enum ModuleType {
        ACTIVE,
        INACTIVE
    }

    private final ArrayList<XSystem> active_systems = new ArrayList<>();
    private final ArrayList<XSystem> inactive_systems = new ArrayList<>();

    private final XOpContext ctx;

    public XSystemManager(XOpMode op){

        this.ctx = op;

    }

    public XSystemManager(XAuton auton){

        this.ctx = auton;

    }

    public void register_module(XSystem module, ModuleType type){

        switch(type){

            case ACTIVE:

                active_systems.add(module);
                break;

            case INACTIVE:

                inactive_systems.add(module);
                break;

        }

    }

    public ArrayList<XSystem> getInactiveSystems() {

        return inactive_systems;

    }

    public ArrayList<XSystem> getActiveSystems() {

        return active_systems;

    }

    public XOpContext getCtx(){

        return this.ctx;

    }

}

