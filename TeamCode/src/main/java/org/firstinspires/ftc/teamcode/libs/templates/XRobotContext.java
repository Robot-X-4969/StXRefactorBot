package org.firstinspires.ftc.teamcode.libs.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XRobotContext {

    public enum ModuleType {
        ACTIVE,
        INACTIVE,
        AUTONOMOUS
    }

    private final Map<Class<?>, XSystem> modules = new HashMap<>();
    private final ArrayList<XSystem> active_systems = new ArrayList<>();
    private final ArrayList<XSystem> inactive_systems = new ArrayList<>();
    private final ArrayList<XSystem> autonomous_systems = new ArrayList<>();

    public final XOpMode op;

    public final XAuton auton;

    public XRobotContext(XOpMode op){

        this.op = op;
        this.auton = null;

    }

    public XRobotContext(XAuton auton){

        this.op = null;
        this.auton = auton;

    }

    public void register_module(XSystem module, ModuleType type){

        modules.put(module.getClass(), module);

        switch(type){

            case ACTIVE:

                active_systems.add(module);
                break;

            case INACTIVE:

                inactive_systems.add(module);
                break;

            case AUTONOMOUS:

                autonomous_systems.add(module);
                break;

        }

    }

    public ArrayList<XSystem> getAutonomous_systems() {

        return autonomous_systems;

    }

    public ArrayList<XSystem> getInactiveSystems() {

        return inactive_systems;

    }

    public ArrayList<XSystem> getActiveSystems() {

        return active_systems;

    }

    public XSystem getModule(Class<?> type){

        return modules.get(type);

    }

}

