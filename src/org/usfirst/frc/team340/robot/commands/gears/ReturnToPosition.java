package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw returns to the upward position and the arm closes.
 * The pusher is still retracted.
 * The rollers are now stopping.
 * 
 * The command ends when the claw is in the upward position, the arm has closed,
 * and the rollers have stopped spinning.
 */
public class ReturnToPosition extends Command {

    public ReturnToPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize ReturnToPosition");
    	Robot.claw.goUp();
    	Robot.claw.goClose();
    	Robot.claw.spinStop();
    	Robot.claw.goRetract();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.isUp() && Robot.claw.isClosed() && Robot.claw.isStopped();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end ReturnToPosition");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
