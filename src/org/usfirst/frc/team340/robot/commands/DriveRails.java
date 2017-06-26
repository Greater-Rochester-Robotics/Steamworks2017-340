package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive each rail at a certain speed
 */
public class DriveRails extends Command {

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private double endAngle = 0;
	private boolean useAngle = false;
	private double angleTolerance = 10;
	private double startAngle = 0;
	private boolean goStraight = false;
	private double arcDivisor = 15;
	private double distance = 0;
	private boolean useDistance = false;
	private int goodCount = 0;
	
	private boolean save = false;
    public DriveRails(double leftSpeed, double rightSpeed) {
        requires(Robot.drive);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    }
    public DriveRails(double leftSpeed, double rightSpeed, double endAngle, double angleTolerance) {
        requires(Robot.drive);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	this.endAngle = endAngle;
    	useAngle = true;
    	this.angleTolerance = angleTolerance;
    }
    
    public DriveRails(double speed) {
    	requires(Robot.drive);
    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    	this.goStraight = true;
    }
    public DriveRails(double speed, double distance, boolean stop) {
    	requires(Robot.drive);
    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    	this.goStraight = true;
    	this.distance = distance;
    	this.useDistance = true;
    	goodCount = 0;
    }
    
    public DriveRails(double speed, boolean save) {
    	requires(Robot.drive);
    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    	this.goStraight = true;
    	this.save = save;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	startAngle = Robot.drive.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.drive.getYaw());
    	if(goStraight) {
        	Robot.drive.setBothDrive(leftSpeed-((Robot.drive.getYaw()-startAngle)/(arcDivisor/Math.abs(leftSpeed))), 
        			rightSpeed+(((Robot.drive.getYaw()-startAngle)/(arcDivisor/Math.abs(leftSpeed)))));
    	} else {
        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(useAngle && Math.abs(Robot.drive.getYaw() - endAngle) < angleTolerance) {
//    		System.out.println("GOT ANGLE");
    	}
    	if(useDistance && Robot.drive.getBackUltrasonic() - distance >= 0) {
    		goodCount++;
    		if(goodCount >= 10) {
    			return true;
    		}
    	}
        return useAngle && Math.abs(Robot.drive.getYaw() - endAngle) < angleTolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("YAW: " + Robot.drive.getYaw());
    	Robot.drive.setBothDrive(0, 0);
    	if(save) {
        	SmartDashboard.putNumber("final dist", Robot.drive.getBackUltrasonic());
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
