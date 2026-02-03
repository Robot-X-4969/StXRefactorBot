package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XRobotContext;
import org.firstinspires.ftc.teamcode.source.systems.CameraSystem;

import javax.crypto.ExemptionMechanism;

@TeleOp (name = "Alignment Test", group = "Tests")
public class AlignmentTest extends XOpMode {

    @Override
    public void init_modules() {

        MecanumOrientationDrive drive = new MecanumOrientationDrive(this, false);

        registerModule(drive, XRobotContext.ModuleType.ACTIVE);
        registerModule(new CameraSystem(this, drive), XRobotContext.ModuleType.ACTIVE);

    }


}
