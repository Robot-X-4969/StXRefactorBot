package org.firstinspires.ftc.teamcode.libs.templates;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class XSystemManager {

    public enum ModuleType {
        ACTIVE,
        INACTIVE
    }

    private final ArrayList<XSystem> active_systems = new ArrayList<>();
    private final ArrayList<XSystem> inactive_systems = new ArrayList<>();

    private final XOpMode op;

    public XSystemManager(XTeleOp op){

        this.op = op;

    }

    public XSystemManager(XAuton auton){

        this.op = auton;

    }

    public void register_module(XSystem module, @NonNull ModuleType type){

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

    public XOpMode getOp(){

        return this.op;

    }

}

