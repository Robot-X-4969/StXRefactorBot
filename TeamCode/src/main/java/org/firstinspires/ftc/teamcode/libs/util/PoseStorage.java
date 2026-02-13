package org.firstinspires.ftc.teamcode.libs.util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public abstract class PoseStorage {

    static Pose2D currentPose = new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.RADIANS, 0);

    public static Pose2D getCurrentPose() {

        return currentPose;

    }

    public static void setCurrentPose(Pose2D newPose) {

        currentPose = newPose;

    }

}
