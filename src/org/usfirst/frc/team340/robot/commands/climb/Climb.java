package org.usfirst.frc.team340.robot.commands.climb;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The robot is no longer being driven by human control, it is on it's own.
 * The climb motors are now going to get to their climb speed.
 * 
 * The command ends when the robot is engaged with the touch pad,
 * which is marked with a current spike.
 */
public class Climb extends Command {

    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize Climb");
    	Robot.climber.goAtClimbSpeed();
    	Robot.drive.goStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.isEngagedWithTouchPad();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end Climb");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
