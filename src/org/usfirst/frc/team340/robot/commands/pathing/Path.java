package org.usfirst.frc.team340.robot.commands.pathing;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class Path extends Command {
	

	private final double arcDivisor = 45;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	private double length = -1;
	
	public abstract double dydx(double s);
	
    private Path(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);

    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    }
    public Path(double speed, double length) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(speed);
    	this.length = length;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	Robot.drive.resetGyro();
    	Robot.drive.resetEncoder();
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(Robot.drive.getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + Robot.drive.getDistance() + " dydx: " + dydx(Robot.drive.getDistance()));
    	return angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = -deltaAngle(Robot.drive.getYaw());
    	
    	System.out.println("error: " + error);
    	if(Robot.drive.getDistance() > 3) {
    		double speed = leftSpeed;
        	Robot.drive.setBothDrive(
        			leftSpeed-((error)/(arcDivisor/Math.abs(speed))), 
        			rightSpeed+(((error)/(arcDivisor/Math.abs(speed)))));
    	} else {
        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.getDistance() > length;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setBothDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
