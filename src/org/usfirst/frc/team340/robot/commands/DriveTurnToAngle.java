package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team340.robot.Robot;

/**
 * This can be more robust in different situations
 *  rather than a PID based systems. For instance 
 *  the difference between comp bot and practise 
 *  bot. This is also good at the PID based turn 
 *  (as of the writing of this command) will output
 *   values to small for the motors to turn the 
 *   robot.
 */
public class DriveTurnToAngle extends Command {
	double batteryV = 14.5;
	double targetAngle;
	double tolerance = .25;
	boolean stop; 
	
    public DriveTurnToAngle(double angle) {
    	this.targetAngle = angle;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//reset the gyro
    	Robot.drive.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println(Robot.drive.getYaw());
    	//store the current angle of the robot to compare with the target angle 
    	double currAngle = Robot.drive.getYaw();
    	if (currAngle < (targetAngle)){
    		//this series of ifs is for when the robot must turn left
    		if(currAngle < (targetAngle - 60)){
    			Robot.drive.arcadeDrive(0,(-.5 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if(currAngle < (targetAngle - 30)){
    			Robot.drive.arcadeDrive(0,(-.4 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if(currAngle < (targetAngle - 15)){
    			Robot.drive.arcadeDrive(0,(-.35 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if(currAngle < (targetAngle - 8)){
    			Robot.drive.arcadeDrive(0,(-.3 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if(currAngle < (targetAngle - 4)){
    			Robot.drive.arcadeDrive(0,(-.27 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else{
    			Robot.drive.arcadeDrive(0,(-.27 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}
    	}else if(currAngle > (targetAngle)){
    		//this series of ifs is for when the robot must turn right
    		if(currAngle > (targetAngle + 60)){
    			Robot.drive.arcadeDrive(0,(.5 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if(currAngle > (targetAngle + 30)){
    			Robot.drive.arcadeDrive(0,(.4 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if( currAngle > (targetAngle + 15)){
    			Robot.drive.arcadeDrive(0,(.35 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if( currAngle > (targetAngle + 8)){
    			Robot.drive.arcadeDrive(0,(.3 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else if( currAngle > (targetAngle + 4)){
    			Robot.drive.arcadeDrive(0,(.27 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}else{
    			Robot.drive.arcadeDrive(0,(.27 * batteryV) / ControllerPower.getInputVoltage());
    			
    		}
    	}
    	else{
    		Robot.drive.arcadeDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {	
    	//if the robot is in the very small range of the targetAngle, stop the command
    	if(Robot.drive.getYaw() > targetAngle - tolerance && Robot.drive.getYaw() < targetAngle + tolerance){
    		System.out.println("target turn done");
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.drive.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
