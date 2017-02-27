package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive each rail at a certain speed
 */
public class DriveRails extends Command {

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private double endAngle = 0;
	private boolean useAngle = false;
	private double angleTolerance = 10;
	
    public DriveRails(double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    }
    public DriveRails(double leftSpeed, double rightSpeed, double endAngle, double angleTolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	this.endAngle = endAngle;
    	useAngle = true;
    	this.angleTolerance = angleTolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(useAngle && Math.abs(Robot.drive.getYaw() - endAngle) < angleTolerance) {
    		System.out.println("GOT ANGLE");
    	}
        return useAngle && Math.abs(Robot.drive.getYaw() - endAngle) < angleTolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("YAW: " + Robot.drive.getYaw());
    	Robot.drive.setBothDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
