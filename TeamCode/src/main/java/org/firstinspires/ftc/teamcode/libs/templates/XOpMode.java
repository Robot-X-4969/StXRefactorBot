package org.firstinspires.ftc.teamcode.libs.templates;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public interface XOpMode {
    HardwareMap getHardwareMap();
    Telemetry getTelemetry();
    Scheduler getScheduler();
    XDriverStation getXDriverStation();

}
