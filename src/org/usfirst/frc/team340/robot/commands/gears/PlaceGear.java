package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw is upward and the arm is opened.
 * The pusher has been extended to move the gear.
 * The rollers are not spinning.
 * 
 * The command ends after a time delay and when a sensor
 * has read that the gear is no longer acquired.
 */
public class PlaceGear extends Command {

    public PlaceGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize PlaceGear");
    	Robot.claw.goExtend();
    	Robot.claw.goUp();
    	Robot.claw.goOpen();
    	Robot.claw.spinStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.whenGearIsNotAcquired();
    }

    // Called once after isFinished returns true
    protected void end() {    	
//    	System.out.println("end PlaceGear");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
