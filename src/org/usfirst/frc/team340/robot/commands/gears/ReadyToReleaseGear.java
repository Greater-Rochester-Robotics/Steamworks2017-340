package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw moves to the downward position and the arm is closed.
 * The pusher is retracted.
 * The rollers are stopped.
 * 
 * The command ends when the claw is in the downward position.
 */
public class ReadyToReleaseGear extends Command {

    public ReadyToReleaseGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize ReadyToRelease");
    	Robot.claw.goDown();
    	Robot.claw.goClose();
    	Robot.claw.spinStop();
    	Robot.claw.goRetract();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.isDown();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end ReadyToRelease");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
