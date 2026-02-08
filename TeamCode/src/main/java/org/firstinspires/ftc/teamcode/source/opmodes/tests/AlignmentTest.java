package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;

@TeleOp (name = "Alignment Test", group = "Tests")
public class AlignmentTest extends XOpMode {

    MecanumOrientationDrive drive = new MecanumOrientationDrive(this, false);
    CameraSystem camera = new CameraSystem(this, drive);

    @Override
    public void init_modules() {

        registerModule(drive, XSystemManager.ModuleType.ACTIVE);
        registerModule(camera, XSystemManager.ModuleType.ACTIVE);

    }

    @Override
    public void displayTelemetry() {

        super.displayTelemetry();

        drive.displayTelemetry();
        camera.displayTelemetry();

    }


}
