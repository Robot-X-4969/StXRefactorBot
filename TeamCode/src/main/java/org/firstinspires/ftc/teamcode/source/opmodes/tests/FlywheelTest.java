package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumOrientationDrive;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XRobotContext;

@TeleOp (name = "Driver Test", group = "Tests")
public class FlywheelTest extends XOpMode {

    @Override
    public void init_modules() {

        registerModule(new Flywheel(), XRobotContext.ModuleType.ACTIVE);

    }


}
