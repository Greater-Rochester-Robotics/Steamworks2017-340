package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw is in the downward position and the arm will close on the gear.
 * The pusher is still retracted.
 * The rollers will stop spinning.
 * 
 * The command ends after a time delay and a sensor reads that the gear is still acquired.
 */
public class GearClamp extends Command {

    public GearClamp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize GearClamp");
    	
    	if(Robot.claw.whenGearIsNotAcquired()) {
    		Robot.claw.goClose();
        	Robot.claw.spinStop();
        	Robot.claw.goRetract();
        	Robot.claw.goDown();
    	} else {
//    		System.out.println("gear still acquired; do nothing");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.whenGearIsAcquired();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end GearClamp");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
