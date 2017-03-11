package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AbortScore extends Command {

    public AbortScore() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize AbortScore");
    	Robot.claw.goUp();
    	Robot.claw.goClose();
    	Robot.claw.goRetract();
    	Robot.claw.spinStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.claw.isUp() && Robot.claw.isClosed() && Robot.claw.isStopped() && Robot.claw.isRetracted();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end AbortScore");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
