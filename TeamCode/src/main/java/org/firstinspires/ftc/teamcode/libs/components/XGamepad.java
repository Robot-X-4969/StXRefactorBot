package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.libs.util.Button;

public class XGamepad{

    // Joystick position values
    private float left_stick_x;
    private float left_stick_y;
    private float right_stick_x;
    private float right_stick_y;

    //Trigger pressure values
    private float left_trigger_pressure;
    private float right_trigger_pressure;

    // Buttons
    private final Button dpad_up = new Button();
    private final Button dpad_down = new Button();
    private final Button dpad_left = new Button();
    private final Button dpad_right = new Button();

    private final Button a = new Button();
    private final Button b = new Button();
    private final Button x = new Button();
    private final Button y = new Button();

    private final Button left_bumper = new Button();
    private final Button right_bumper = new Button();

    private final Button left_stick_button = new Button();
    private final Button right_stick_button = new Button();

    private final Button guide = new Button();
    private final Button start = new Button();
    private final Button back = new Button();

    private Gamepad gamepad;

    /**
     * Default constructor
     */
    public XGamepad() {

    }

    /**
     * Initializes the XGamepad with an initial Gamepad object
     * @param gamepad FTC Gamepad object
     */
    public XGamepad(Gamepad gamepad) {

        this.gamepad = gamepad;

    }

    /**
     * Updates the state of the XGamepad by reading the current values from the Gamepad object
     */
    public void updateGamepad() {

        //updateStates the values of all joysticks and triggers
        left_stick_x = gamepad.left_stick_x;
        left_stick_y = gamepad.left_stick_y;
        right_stick_x = gamepad.right_stick_x;
        right_stick_y = gamepad.right_stick_y;

        left_trigger_pressure = gamepad.left_trigger;
        right_trigger_pressure = gamepad.right_trigger;

        //updateStates the values of all buttons
        dpad_up.updateState(gamepad.dpad_up);
        dpad_down.updateState(gamepad.dpad_down);
        dpad_left.updateState(gamepad.dpad_left);
        dpad_right.updateState(gamepad.dpad_right);

        a.updateState(gamepad.a);
        b.updateState(gamepad.b);
        x.updateState(gamepad.x);
        y.updateState(gamepad.y);

        left_bumper.updateState(gamepad.left_bumper);
        right_bumper.updateState(gamepad.right_bumper);

        left_stick_button.updateState(gamepad.left_stick_button);
        right_stick_button.updateState(gamepad.right_stick_button);

        guide.updateState(gamepad.guide);
        start.updateState(gamepad.start);
        back.updateState(gamepad.back);

        if (start.isPressed()) {

            a.updateState(false);

        }

    }

    //getters
    public float getLeftStickX() {

        return left_stick_x;

    }

    public float getLeftStickY() {

        return left_stick_y;

    }

    public float getRightStickX() {

        return right_stick_x;

    }

    public float getRightStickY() {

        return right_stick_y;

    }

    public float getLeftTriggerPressure() {

        return left_trigger_pressure;

    }

    public float getRightTriggerPressure() {

        return right_trigger_pressure;

    }

    public Button getDpadUp() {

        return dpad_up;

    }

    public Button getDpadDown() {

        return dpad_down;

    }

    public Button getDpadLeft() {

        return dpad_left;

    }

    public Button getDpadRight() {

        return dpad_right;

    }

    public Button getA() {

        return a;

    }

    public Button getB() {

        return b;

    }

    public Button getX() {

        return x;

    }


    public Button getY() {

        return y;

    }

    public Button getLeft_bumper() {

        return left_bumper;

    }

    public Button getRight_bumper() {

        return right_bumper;

    }

    public Button getLeft_stick_button() {

        return left_stick_button;

    }

    public Button getRight_stick_button() {

        return right_stick_button;

    }

    public Button getGuide() {

        return guide;

    }

    public Button getStart() {

        return start;

    }

    public Button getBack() {

        return back;

    }


}
