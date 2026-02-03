package org.firstinspires.ftc.teamcode.source.systems;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;
import org.firstinspires.ftc.teamcode.libs.components.XCRServo;

public class IntakeSystem extends XSystem {

    XCRServo crServo1;
    XCRServo crServo2;

    public IntakeSystem(XOpMode op) {

        super(op);

    }

    @Override
    public void init() {

        crServo1 = new XCRServo(op, "intakeServo1");
        crServo2 = new XCRServo(op, "intakeServo2");
        crServo1.init();
        crServo2.init();

    }

    @Override
    public void loop(){

        super.loop();
        crServo1.rotate(1.0);
        crServo2.rotate(-1.0);

    }

    @Override
    public void stop(){

        crServo1.rotate(0.0);
        crServo2.rotate(0.0);

    }

}
