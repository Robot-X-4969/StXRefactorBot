package org.firstinspires.ftc.teamcode.source.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XTeleOp;

import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;
import org.firstinspires.ftc.teamcode.source.systems.Flywheel;
import org.firstinspires.ftc.teamcode.source.systems.IntakeSystem;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

@TeleOp (name = "Driver Test", group = "Teleop")
public class Teleop2026 extends XTeleOp {

    MecanumDrive drive = new MecanumDrive(this);
    CameraSystem cameraSystem = new CameraSystem(this, drive);
    Flywheel flywheel = new Flywheel(this, cameraSystem);
    Spindexer spindexer = new Spindexer(this);
    IntakeSystem intakeSystem = new IntakeSystem(this);

    @Override
    public void init_modules() {

        registerModule(drive, XSystemManager.ModuleType.ACTIVE);
        registerModule(cameraSystem, XSystemManager.ModuleType.ACTIVE);
        registerModule(flywheel, XSystemManager.ModuleType.ACTIVE);
        registerModule(spindexer, XSystemManager.ModuleType.ACTIVE);
        registerModule(intakeSystem, XSystemManager.ModuleType.ACTIVE);

    }

    @Override
    public void displayTelemetry() {

        super.displayTelemetry();

        drive.displayTelemetry();
        cameraSystem.displayTelemetry();
        flywheel.displayTelemetry();

    }

}
