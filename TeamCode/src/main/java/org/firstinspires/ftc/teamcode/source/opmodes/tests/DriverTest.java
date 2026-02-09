package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XTeleOp;

import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;

@TeleOp (name = "Driver Test", group = "Tests")
public class DriverTest extends XTeleOp {

    @Override
    public void init_modules() {

        registerModule(new MecanumDrive(this, false), XSystemManager.ModuleType.ACTIVE);

    }


}
