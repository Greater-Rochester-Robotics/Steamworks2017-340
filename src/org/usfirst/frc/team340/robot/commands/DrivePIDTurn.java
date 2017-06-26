package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DrivePIDTurn extends PIDCommand {

	private static final double P = 0.04;
	private static final double I = 0.005;
	private static final double D = 0.02;
	
	private double angle;
	private boolean reset;
    public DrivePIDTurn(double angle, boolean reset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(P, I, D);
    	this.angle = angle;
    	this.reset = reset;
    	requires(Robot.drive);
    }
    
    @Override
    protected double returnPIDInput() {
    	// TODO Auto-generated method stub
    	return Robot.drive.getYaw();
    }
    
    @Override
    protected void usePIDOutput(double output) {
    	Robot.drive.setBothDrive(output, -output);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(reset)
    		Robot.drive.resetGyro();
    	setSetpoint(angle);
    	getPIDController().setOutputRange(-0.8, 0.8);
    	getPIDController().setAbsoluteTolerance(0.7);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
//        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
