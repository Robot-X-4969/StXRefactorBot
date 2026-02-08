package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;

@TeleOp (name = "Driver Test", group = "Tests")
public class DriverTest extends XOpMode {

    @Override
    public void init_modules() {

        registerModule(new MecanumOrientationDrive(this, false), XSystemManager.ModuleType.ACTIVE);

    }


}
