package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.OI.Axis;
import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for driving with Xbox 360 controller
 */
public class DriveXbox extends Command {
	private final double moveSlowScale = 0.6;
	private final double turnSlowScale = 0.7;
	
    /**
     * Constructs a new instance of
     * {@link DriveXbox}, and all it does
     * is set the required subsystem
     */
    public DriveXbox() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
    	Robot.drive.setBothDrive(0);
//    	Robot.drive.resetGyro();
    }

    @Override
    protected void execute() {
//    	System.out.println(Robot.drive.getYaw());\][
//    	Robot.drive.getRightIRSensor();//this was used to see the sensor via dashboard for testing purposes
//    	Robot.drive.getLeftIRSensor();//this was used to see the sensor via dashboard for testing purposes
//    	System.out.println(Robot.drive.getBackUltrasonic() + " , " + Robot.drive.getFrontUltrasonic());
    	if(Math.abs(Robot.oi.getDriverAxis(Axis.LEFT_X)) > 0.1 || //At least 10% away from center of left X
    			Math.abs(Robot.oi.getDriverAxis(Axis.LEFT_Y)) > 0.1) { //At least 10% away from center of left Y
    			Robot.drive.arcadeDrive(Robot.oi.getDriverAxis(Axis.LEFT_Y), //Movement speed
    					Robot.oi.getDriverAxis(Axis.LEFT_X)); //Rotation speed
    	} else if(Math.abs(Robot.oi.getDriverAxis(Axis.RIGHT_X)) > 0.1 || //At least 10% away from center of right X
    			Math.abs(Robot.oi.getDriverAxis(Axis.RIGHT_Y)) > 0.1) { //At least 10% away from center of right Y
    			Robot.drive.arcadeDrive(Robot.oi.getDriverAxis(Axis.RIGHT_Y) * moveSlowScale,  //Move speed (slowed)
    					Robot.oi.getDriverAxis(Axis.RIGHT_X) * turnSlowScale); //Rotation speed (slowed) 
    	} else {
    		Robot.drive.arcadeDrive(0, 0);
    	}
    	
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    	Robot.drive.setBothDrive(0);
    }
}
