package org.firstinspires.ftc.teamcode.source.systems;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.components.XServo;
import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.templates.XModule;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;


public class Spindexer extends XModule {

    XMotor motor;

    XServo gate1, gate2;

    private static final int INCREMENT = 475;

    private static final double START_ANGLE = 40.0 / 360.0 / 5.0;
    
    private boolean isFiring;

    private boolean burstFire;


    public Spindexer(XOpMode op) {

        super(op);

    }

    @Override
    public void init(Scheduler scheduler, XDriverStation driverStation) {

        super.init(scheduler, driverStation);

        motor = new XMotor(op, "spindexer");
        motor.init();

        gate1 = new XServo(op, "gate1", 1-START_ANGLE);
        gate1.init();
        gate2 = new XServo(op, "gate2", START_ANGLE);
        gate2.init();

        isFiring = false;

    }

    @Override
    public void loop(double deltaTime) {

        super.loop(deltaTime);

    }

    @Override
    public void control_loop() {


        if (driverStation.getGamepad1().getLeft_bumper().wasPressed() && !motor.checkBusy() && !isFiring) {

            incrementSpindexer(false);

        } else if (driverStation.getGamepad1().getRight_bumper().wasPressed() && !motor.checkBusy() && !isFiring) {

             incrementSpindexer(true);

        }

        if(driverStation.getGamepad1().getB().wasPressed()){

            burstFire = !burstFire;

        }

        if (driverStation.getGamepad1().getRightTriggerPressure() >= 0.5 && !isFiring) {

            if(burstFire){

                burstFire();

            } else {

                manualFire();

            }

        }

    }

    public void burstFire(){

        fire();

        isFiring = true;

        scheduler.setEvent(1000L, "reset1" , this::resetGate);

        scheduler.setEvent(1500L, "increment1" , () -> incrementSpindexer(true));

        scheduler.setEvent(2000L, "fire2" , this::fire);

        scheduler.setEvent(3000L, "reset2" , this::resetGate);

        scheduler.setEvent(3500L, "increment2" , () -> incrementSpindexer(true));

        scheduler.setEvent(4000L, "fire3" , this::fire);

        scheduler.setEvent(5000L, "reset3" , () -> {

            resetGate();
            isFiring = false;

        });

    }

    public void manualFire(){

        fire();
        isFiring = true;

        scheduler.setEvent(1000L, "resetGate", () -> {

            resetGate();

            isFiring = false;

        });

    }

    public void fire(){

        gate1.setPosition(1 - START_ANGLE + (-2.0 / 15.0));
        gate2.setPosition(START_ANGLE + (2.0 / 15.0));

    }

    public void resetGate(){

        gate1.setPosition(1-START_ANGLE);
        gate2.setPosition(START_ANGLE);

    }

    public void incrementSpindexer(boolean forward){

        int direction = forward ? INCREMENT : -INCREMENT;

        motor.setFixedRotation(0.6 , motor.getPosition() + direction);

    }

    public boolean getIsFiring(){

        return isFiring;

    }

}

