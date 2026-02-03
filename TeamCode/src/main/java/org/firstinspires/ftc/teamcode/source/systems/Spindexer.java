package org.firstinspires.ftc.teamcode.source.systems;

import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XSystem;


public class Spindexer extends XSystem {

    XMotor motor;

    XServo gate1, gate2;

    private static final int INCREMENT = 475;

    private static final double START_ANGLE = 40.0 / 360.0 / 5.0;

    private final double[] gate1Positions = {1 - START_ANGLE, 1 - START_ANGLE + (-2.0 / 15.0)};
    private final double[] gate2Positions = {START_ANGLE, START_ANGLE + (2.0 / 15.0)};


    public Spindexer(XOpMode op) {

        super(op);
    }

    @Override
    public void init() {

        motor = new XMotor(op, "spindexer");
        motor.init();

        gate1 = new XServo(op, "gate1", gate1Positions);
        gate1.init();
        gate2 = new XServo(op, "gate2", gate2Positions);
        gate2.init();

        super.init();

    }

    @Override
    public void loop(){

        super.loop();

    }

    @Override
    public void control_loop() {


        if (driverStation.getGamepad1().getLeft_bumper().wasPressed() && !motor.checkBusy()) {

            motor.setFixedRotation(0.3 , motor.getPosition() + -INCREMENT );

        } else if (driverStation.getGamepad1().getRight_bumper().wasPressed() && !motor.checkBusy()) {

            motor.setFixedRotation(0.3 , motor.getPosition() + INCREMENT );

        }

        if (driverStation.getGamepad1().getB().wasPressed()) {

            gate1.setIndex(1);
            gate2.setIndex(1);

            scheduler.setEvent(1000L, "resetGate", () -> {

                gate1.setIndex(0);
                gate2.setIndex(0);

            });

        }

    }

}

