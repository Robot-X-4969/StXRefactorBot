package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

public class XDriverStation{

    private  final Gamepad gamepad1;
    private final Gamepad gamepad2;

    private final XGamepad xgamepad1;
    private final XGamepad xgamepad2;


    private boolean dual_player = false;

    /**
     * Initializes the XDriverStation with the provided Gamepad objects
     * @param gamepad1 FTC Gamepad 1 object
     * @param gamepad2 FTC Gamepad 2 object
     */
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

    /**
     * Updates the button states of the XGamepad objects
     */
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

}
