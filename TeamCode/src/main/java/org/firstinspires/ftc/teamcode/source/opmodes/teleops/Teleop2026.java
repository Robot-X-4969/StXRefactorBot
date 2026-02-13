package org.firstinspires.ftc.teamcode.source.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.components.XPinpoint;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XTeleOp;

import org.firstinspires.ftc.teamcode.libs.templates.XModuleManager;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;
import org.firstinspires.ftc.teamcode.source.systems.Flywheel;
import org.firstinspires.ftc.teamcode.source.systems.Gate;
import org.firstinspires.ftc.teamcode.source.systems.IntakeSystem;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

@TeleOp (name = "Teleop26", group = "Teleop")
public class Teleop2026 extends XTeleOp {

    XPinpoint pinpoint = new XPinpoint(this, 1.125, 4.625);
    MecanumDrive drive = new MecanumDrive(this);
    CameraSystem cameraSystem = new CameraSystem(this, drive);
    Flywheel flywheel = new Flywheel(this, cameraSystem, pinpoint);
    Spindexer spindexer = new Spindexer(this);
    IntakeSystem intakeSystem = new IntakeSystem(this);
    Gate gate = new Gate(this);

    @Override
    public void init_modules() {

        pinpoint.init();


        registerModule(drive, XModuleManager.ModuleType.ACTIVE);
        registerModule(cameraSystem, XModuleManager.ModuleType.ACTIVE);
        registerModule(flywheel, XModuleManager.ModuleType.ACTIVE);
        registerModule(spindexer, XModuleManager.ModuleType.ACTIVE);
        registerModule(intakeSystem, XModuleManager.ModuleType.ACTIVE);
        registerModule(gate, XModuleManager.ModuleType.ACTIVE);

    }

    @Override
    public void displayTelemetry() {

        super.displayTelemetry();

        drive.displayTelemetry();
        cameraSystem.displayTelemetry();
        flywheel.displayTelemetry();

    }

    @Override
    public void start(){

        pinpoint.setStartPose(125.0, -20.0, 51.0);
        super.start();

    }

}
