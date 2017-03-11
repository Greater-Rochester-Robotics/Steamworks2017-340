package org.usfirst.frc.team340.robot.commands.climb;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AbortClimb extends Command {

    public AbortClimb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize AbortClimb");
    	Robot.climber.goStopped();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.isStopped();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end AbortClimb");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
