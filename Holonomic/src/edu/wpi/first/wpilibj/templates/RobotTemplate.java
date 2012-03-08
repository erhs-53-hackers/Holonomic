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
    Controls controls;
  

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */   

    public void robotInit() {
        driveTrain = new RobotDrive(1,3,2,4);
        joystick = new Joystick(1);
        msg = new Messager();
        controls = new Controls(joystick);
        
        msg.printLn("Mecanum Bot 2/25/12");
        getWatchdog().setExpiration(10);
        driveTrain.setSafetyEnabled(false);
        driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveTrain.setExpiration(10);

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       // circle(stage, speed, time, inc, x, y);
       
    }

    /**
     * This function is called periodically during TeleOp mode
     */
    public void teleopPeriodic() {
        

         if (controls.button1()) {//trigger reverses drive
            driveTrain.mecanumDrive_Cartesian(-joystick.getX(), -joystick.getY(), -MathX.pow(joystick.getTwist(), 3), 0);
            
        } else {
            driveTrain.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), MathX.pow(joystick.getTwist(), 3), 0);
            
        }
        System.out.println("1:"+joystick.getRawAxis(1));
        System.out.println("2:"+joystick.getRawAxis(2));
        System.out.println("3:"+joystick.getRawAxis(3));
        System.out.println("4:"+joystick.getRawAxis(4));
        System.out.println("5:"+joystick.getRawAxis(5));
        System.out.println("6:"+joystick.getRawAxis(6));
        System.out.println("-------------------------------------");

        
        
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
