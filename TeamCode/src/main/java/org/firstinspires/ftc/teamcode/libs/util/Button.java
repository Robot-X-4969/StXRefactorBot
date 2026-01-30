package org.firstinspires.ftc.teamcode.libs.util;

/**
 * A class for managing button states and timings
 */
public class Button {

    private boolean last_state = false;
    private boolean current_state = false;

    private long time = 0;

    /**
     * Default constructor
     */
    public Button() {

    }

    /**
     * Constructor that initializes the button with a current state
     * 
     * @param current_state  The current value of the button (pressed or not pressed)
     */
    public Button(boolean current_state){

        this.current_state = current_state;

    }

    /**
     * @param new_state The new value of the button (pressed or not pressed)
     */
    public void updateState(boolean new_state){

        if(new_state != current_state){

            // resets the timer if the state has changed
            time = System.currentTimeMillis();

        }
        
        last_state = current_state;
        current_state = new_state;

    }

    /**
     * @return true if the button is currently pressed
     */
    public boolean isPressed(){

        return current_state;

    }

    /**
     * @return true if the button is not currently pressed
     */
    public boolean isReleased(){

        return !current_state;

    }

    /**
     * @return true if the button was just pressed this update
     */
    public boolean wasPressed(){

        return !last_state && current_state;

    }

    /**
     * @return true if the button was just released this update
     */
    public boolean wasReleased(){

        return last_state && !current_state;

    }
    
    /**
     * @return time since the button was last updated if the button is currently pressed
     */
    public long getTimePressed(){

        if(current_state){

            return System.currentTimeMillis() - time;

        }
       
        return 0;
        
    }

    /**
     * @return time since the button was last updated if the button is currently released
     */
    public long getTimeReleased(){

        if(!current_state){

            return System.currentTimeMillis() - time;

        }
        
        return 0;
        
    }

}
