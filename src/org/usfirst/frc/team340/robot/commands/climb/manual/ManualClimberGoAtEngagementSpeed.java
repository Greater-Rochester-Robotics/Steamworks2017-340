package org.usfirst.frc.team340.robot.commands.climb.manual;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualClimberGoAtEngagementSpeed extends Command {

    public ManualClimberGoAtEngagementSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize ManualClimberGoAtEngagmentSpeed");
    	Robot.climber.goAtEngagementSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("current draw motor 1 ;" + Robot.climber.getCurrentDrumOne() 
//    		+ "; motor 2 ;" + Robot.climber.getCurrentDrumTwo() + ";"); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end ManualClimberGoAtEngagmentSpeed");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
