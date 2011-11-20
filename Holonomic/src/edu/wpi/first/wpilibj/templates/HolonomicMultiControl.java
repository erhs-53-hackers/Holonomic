/**
 * Testing the use of multiple controllers at the same time
 * And possibly the use of sensors and/or end effectors
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The Java Virtual Machine will already run these functions without calling them
 * Don't Change Their Names!
 */

public class HolonomicMultiControl extends IterativeRobot {

    RobotDrive driveTrain;
    Joystick joystick;
    Joystick joystick2;
    Messager msg;
    Jaguar motor;

    int stage = 0;
    float speed = .5f;
    float time = 1.0f;
    
    public void robotInit() {
        driveTrain = new RobotDrive(1, 2, 3, 4);
        joystick = new Joystick(1);
        joystick2 = new Joystick(2);
        msg = new Messager();
        msg.printLn("Testing the use of multiple controllers");
        getWatchdog().setExpiration(10);
        driveTrain.setSafetyEnabled(false);

    }


    public void autonomousPeriodic() {

        driveTrain.mecanumDrive_Cartesian(speed, 0, 0, 0);
        Timer.delay(time);
        driveTrain.stopMotor();        
        driveTrain.mecanumDrive_Cartesian(0, speed, 0, 0);
        Timer.delay(time);
        driveTrain.stopMotor();
        driveTrain.mecanumDrive_Cartesian(-speed, 0, 0, 0);
        Timer.delay(time);
        driveTrain.stopMotor();
        driveTrain.mecanumDrive_Cartesian(0, -speed, 0, 0);
        Timer.delay(time);
        driveTrain.stopMotor();
        

    }

    
    public void teleopPeriodic() {
        driveTrain.mecanumDrive_Cartesian(joystick.getX(), joystick2.getY(), joystick.getZ(), 0);
       // driveTrain.mecanumDrive_Cartesian(speed, speed, 0, 0);
        
    }


}
