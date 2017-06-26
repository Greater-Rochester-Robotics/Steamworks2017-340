package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw is moving downwards and the arm is opening in preparation to grab the gear.
 * The pusher is still retracted inside the claw.
 * The rollers are spinning inwards to bring in the gear.
 * 
 * The command ends when a sensor reads that the gear has been acquired.
 */
public class HarvestMode extends Command {

    public HarvestMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.led.hookLEDs();
//    	System.out.println("initialize HarvestMode");
    	
    	if(Robot.claw.whenGearIsNotAcquired()) {
    		Robot.claw.goDown();
        	Robot.claw.goOpen();
        	Robot.claw.spinIn();
        	Robot.claw.goRetract();
        	Robot.oi.rumbleDriver(1.0f);
    	} else {
//    		System.out.println("gear is already acquired do nothing");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.claw.leftSensorState()) {
    		Robot.led.setLeftColor(0, 0, 255, 1000, true);
    	} else {
    		Robot.led.setLeftColor(255, 0, 0);
    	}
    	if(Robot.claw.rightSensorState()) {
    		Robot.led.setRightColor(0, 0, 255, 1000, true);
    	} else {
    		Robot.led.setRightColor(255, 0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.whenGearIsAcquired();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.led.unhookLEDs();
    	Robot.claw.spinStop();
    	Robot.oi.rumbleDriver(0f);
//    	System.out.println("end HarvestMode");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.led.unhookLEDs();
    	end();
    }
}
