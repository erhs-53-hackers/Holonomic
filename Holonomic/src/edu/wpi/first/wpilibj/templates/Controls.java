/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Michael
 */
public class Controls {


    private Joystick joystick;

    public Controls(Joystick _joystick) {
        joystick = _joystick;
    }

    public boolean buttonA() {
        return joystick.getRawButton(1);
    }

    public boolean buttonB() {
        return joystick.getRawButton(2);
    }

    public boolean buttonX() {
        return joystick.getRawButton(3);
    }

    public boolean buttonY() {
        return joystick.getRawButton(4);
    }

    public boolean leftBumper() {
        return joystick.getRawButton(5);
    }

    public boolean rightBumper() {
        return joystick.getRawButton(6);
    }
}
