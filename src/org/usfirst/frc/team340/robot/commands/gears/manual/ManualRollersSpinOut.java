package org.usfirst.frc.team340.robot.commands.gears.manual;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualRollersSpinOut extends Command {

    public ManualRollersSpinOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize ManualRollersSpinOut");
    	Robot.claw.spinOut();
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
//    	System.out.println("end ManualRollersSpinOut");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
