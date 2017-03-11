package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw is in the downward position and the arm is opening.
 * The pusher stays retracted in the claw.
 * The rollers begin to spin out in order to remove the gear.
 * 
 * The command ends after a time delay and when a sensor has read that the 
 * gear is no longer acquired.
 */
public class RegurgitateGear extends Command {

    public RegurgitateGear() {
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize RegurgitateGear");
    	Robot.claw.goOpen();
    	Robot.claw.spinOut();
    	Robot.claw.goDown();
    	Robot.claw.goRetract();
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
//    	System.out.println("end RegurgitateGear");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
