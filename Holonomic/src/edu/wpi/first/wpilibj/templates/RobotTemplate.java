/**
/* Copyright (c) FIRST 2008. All Rights Reserved.                             
/* Open Source Software - may be modified and shared by FRC teams. The code   
/* must be accompanied by the FIRST BSD license file in the root directory of 
/* the project.                                                               
*/
package edu.wpi.first.wpilibj.templates;

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
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    //initialize variables
    RobotDrive driveTrain;
    Joystick joystick;
    Messager msg;
    Jaguar motor;

    //set up constant variables
    int stage = 0;
    float speed = 0.5f;
    float time = 1;
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
        msg.printLn("Welcome to the Area 53 Holonomic Experience");

        getWatchdog().setExpiration(10);
        driveTrain.setSafetyEnabled(false);

    }


    private void circle() {
        switch (stage) {
            case 0:
                x -= inc;
                y += inc;
                if(x == 0) stage++;
                break;
            case 1:
                x -= inc;
                y -= inc;
                if(x == -1) stage++;
                break;
            case 2:
                x += inc;
                y -= inc;
                if(x == 0) stage++;
                break;
            case 3:
                x += inc;
                y += inc;
                if(x == 1) stage = 0;
                break;
        }

        driveTrain.mecanumDrive_Cartesian(x * speed, y * speed, 0, 0);
        Timer.delay(0.3);
        String s = "Stage: "+stage+" X: "+x+" Y: "+y;
        msg.printLn(s);
    }
    


   
    /**
     * This function is called periodically during autonomous
     */

    public void autonomousPeriodic() {

        circle();

    }

    
    /**
     * This function is called periodically during TeleOp mode
     */
    
    public void teleopPeriodic() {
        driveTrain.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        try {
            ColorImage image = AxisCamera.getInstance().getImage();
        } catch (AxisCameraException ex) {
        } catch (NIVisionException ex) {
        }

    }
}
