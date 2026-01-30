package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

public class XDriverStation{

    private  final Gamepad gamepad1;
    private final Gamepad gamepad2;

    private final XGamepad xgamepad1;
    private final XGamepad xgamepad2;

    private XGamepad master_gamepad;


    private boolean dual_player = false;

    public XDriverStation(Gamepad gamepad1, Gamepad gamepad2){

        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;

        xgamepad1 = new XGamepad(gamepad1);
        xgamepad2 = new XGamepad(gamepad2);

        xgamepad1.updateGamepad();

        if(dual_player) {

            xgamepad2.updateGamepad();

        }


    }

    public void update(){

        xgamepad1.updateGamepad();

        if (dual_player){

            xgamepad2.updateGamepad();

        }


        if(xgamepad1.getBack().isPressed()){

            dual_player = !dual_player;

        }

    }

    public XGamepad getGamepad1(){

        return xgamepad1;

    }

    public XGamepad getGamepad2(){

        return xgamepad2;

    }
    
    public void update_master_gamepad() {

        master_gamepad.getDpadUp().updateState(gamepad1.dpad_up || gamepad2.dpad_up && dual_player);
        master_gamepad.getDpadDown().updateState(gamepad1.dpad_down || gamepad2.dpad_down && dual_player);
        master_gamepad.getDpadLeft().updateState(gamepad1.dpad_left || gamepad2.dpad_left && dual_player);
        master_gamepad.getDpadRight().updateState(gamepad1.dpad_right || gamepad2.dpad_right && dual_player);
        master_gamepad.getA().updateState(gamepad1.a && !gamepad1.start || gamepad2.a && !gamepad2.start && dual_player);
        master_gamepad.getB().updateState(gamepad1.b && !gamepad1.start || gamepad2.b && !gamepad2.start && dual_player);
        master_gamepad.getX().updateState(gamepad1.x || gamepad2.x && dual_player);
        master_gamepad.getY().updateState(gamepad1.y || gamepad2.y && dual_player);
        master_gamepad.getGuide().updateState(gamepad1.guide || gamepad2.guide && dual_player);
        master_gamepad.getStart().updateState(gamepad1.start || gamepad2.start && dual_player);
        master_gamepad.getStart().updateState(gamepad1.back || gamepad2.back && dual_player);
        master_gamepad.getLeft_bumper().updateState(gamepad1.left_bumper || gamepad2.left_bumper && dual_player);
        master_gamepad.getRight_bumper().updateState(gamepad1.right_bumper || gamepad2.right_bumper && dual_player);
        master_gamepad.getLeft_stick_button().updateState(gamepad1.left_stick_button || gamepad2.left_stick_button && dual_player);
        master_gamepad.getRight_stick_button().updateState(gamepad1.right_stick_button || gamepad2.right_stick_button && dual_player);

    }
}
