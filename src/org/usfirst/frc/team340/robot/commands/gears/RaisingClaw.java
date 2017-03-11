package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The claw will move upwards and the arm will remain closed on the gear.
 * The pusher will continue to remain retracted.
 * The rollers will remain stopped.
 * 
 * The command ends when the claw is in the upward position.
 */
public class RaisingClaw extends Command {

    public RaisingClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize RaisingClaw");
    	Robot.claw.goUp();
    	Robot.claw.goRetract();
    	Robot.claw.goClose();
    	Robot.claw.spinStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.isUp();
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end RaisingClaw");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
