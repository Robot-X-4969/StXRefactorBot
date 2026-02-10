package org.firstinspires.ftc.teamcode.libs.templates;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class XModuleManager {

    public enum ModuleType {
        ACTIVE,
        INACTIVE
    }

    private final ArrayList<XModule> active_modules = new ArrayList<>();
    private final ArrayList<XModule> inactive_modules = new ArrayList<>();

    private final XOpMode op;

    public XModuleManager(XTeleOp op){

        this.op = op;

    }

    public XModuleManager(XAuton auton){

        this.op = auton;

    }

    public void register_module(XModule module, @NonNull ModuleType type){

        switch(type){

            case ACTIVE:

                active_modules.add(module);
                break;

            case INACTIVE:

                inactive_modules.add(module);
                break;

        }

    }

    public ArrayList<XModule> getInactiveSystems() {

        return inactive_modules;

    }

    public ArrayList<XModule> getActiveSystems() {

        return active_modules;

    }

    public XOpMode getOp(){

        return this.op;

    }

}

