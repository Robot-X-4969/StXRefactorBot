package org.firstinspires.ftc.teamcode.source.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import org.firstinspires.ftc.teamcode.libs.templates.XSystemManager;
import org.firstinspires.ftc.teamcode.source.systems.Spindexer;

@TeleOp (name = "Spindexer Test", group = "Tests")
public class SpindexerTest extends XOpMode {

    @Override
    public void init_modules() {

        registerModule(new Spindexer(this), XSystemManager.ModuleType.ACTIVE);

    }


}
