package org.firstinspires.ftc.teamcode.source.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XRobotContext;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;
import org.firstinspires.ftc.teamcode.source.systems.Flywheel;
import org.firstinspires.ftc.teamcode.source.systems.IntakeSystem;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

@TeleOp (name = "Driver Test", group = "Teleop")
public class Teleop2026 extends XOpMode {

    MecanumOrientationDrive drive = new MecanumOrientationDrive(this, false);
    CameraSystem cameraSystem = new CameraSystem(this, drive);
    Flywheel flywheel = new Flywheel(this, cameraSystem);
    Spindexer spindexer = new Spindexer(this);
    IntakeSystem intakeSystem = new IntakeSystem(this);

    @Override
    public void init_modules() {

        registerModule(drive, XRobotContext.ModuleType.ACTIVE);
        registerModule(cameraSystem, XRobotContext.ModuleType.ACTIVE);
        registerModule(flywheel, XRobotContext.ModuleType.ACTIVE);
        registerModule(spindexer, XRobotContext.ModuleType.ACTIVE);
        registerModule(intakeSystem, XRobotContext.ModuleType.ACTIVE);

    }

    @Override
    public void displayTelemetry() {

        super.displayTelemetry();

        drive.displayTelemetry();
        cameraSystem.displayTelemetry();
        flywheel.displayTelemetry();

    }



}
