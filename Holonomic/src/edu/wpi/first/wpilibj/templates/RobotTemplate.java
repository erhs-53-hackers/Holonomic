/**
 * Copyright (c) FIRST 2008. All Rights Reserved.
 * Copyright (c) FRC Team 53.  All Rights Reserved
 * By FRC Team 53 - The Area 53 Cow Abductors
 * Released under the GNU General Public License v. 3 or later
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * The Java Virtual Machine (VM) is configured to run each function defined
 * below automatically when the driver station switches to each mode
 * The function extends and overwrites the IterativeRobot class template
 * DO NOT CHANGE THE NAMES OF THE CLASSES OR FUNCTIONS OR YOU WILL HAVE TO
 * UPDATE THE MANIFEST FILE IN THE RESOURCE DIRECTORY
 */
public class RobotTemplate extends IterativeRobot {

    //initialize robot variables
    RobotDrive driveTrain;
    Joystick joystick;
    Messager msg;
    Jaguar motor;
    AnalogChannel analog1;
    Controls controls;
    //set up constant variables
    int stage = 0;
    float speed = 0.5f;
    float time = 0.5f;
    float inc = 0.05f;
    float x = 1;
    float y = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */   

    public void robotInit() {
        driveTrain = new RobotDrive(1, 2, 3, 4);
        joystick = new Joystick(1);
        msg = new Messager();
        controls = new Controls(joystick);
        //analog1 = new AnalogChannel(1);
        msg.printLn("Hello C:");
        getWatchdog().setExpiration(10);
        driveTrain.setSafetyEnabled(false);

    }

    private void circle(int stage, float speed, float time, float inc,
            float x, float y) {
        switch (stage) {
            case 0:
                x -= inc;
                y += inc;
                if (y >= 1) {
                    stage++;
                }
                break;
            case 1:
                x -= inc;
                y -= inc;
                if (x <= -1) {
                    stage++;
                }
                break;
            case 2:
                x += inc;
                y -= inc;
                if (y <= -1) {
                    stage++;
                }
                break;
            case 3:
                x += inc;
                y += inc;
                if (x >= 1) {
                    stage = 0;
                }
                break;
        }

        driveTrain.mecanumDrive_Cartesian(x * speed, y * speed, 0, 0);
        Timer.delay(time);
        String s = "Stage: " + stage + " X: " + x + " Y: " + y;
        msg.printLn(s);
    }

    private void square() {
       
            driveTrain.mecanumDrive_Cartesian(speed, 0, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(0, speed, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(-speed, 0, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(0, -speed, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
        

       
            driveTrain.mecanumDrive_Cartesian(speed, 0, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(0, speed, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(-speed, 0, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
            driveTrain.mecanumDrive_Cartesian(0, -speed, 0, 0);
            Timer.delay(1);
            driveTrain.stopMotor();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       // circle(stage, speed, time, inc, x, y);
        square();
    }

    /**
     * This function is called periodically during TeleOp mode
     */
    public void teleopPeriodic() {
        

        driveTrain.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), joystick.getZ(), 0);

        if (controls.leftBumper()) {
            circle(0, 0.5f, 1, .01f, 1, 0);
        }
        
        Timer.delay(0.05f);


/*
        try {
            ColorImage image = AxisCamera.getInstance().getImage();
        } catch (AxisCameraException ex) {
        } catch (NIVisionException ex) {
        }
 * */

    }
}
