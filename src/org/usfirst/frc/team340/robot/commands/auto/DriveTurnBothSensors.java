package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnBothSensors extends Command {

	double speed;
	boolean rightSensor;
	boolean stop = false;
	
	int sweepStage = 0;
	double stage1Time = 0.5;
	double stage2Time = 1.5;
	
    public DriveTurnBothSensors(double speed, boolean rightSensor) {
    	this.speed = speed;
    	this.rightSensor = rightSensor;
    	sweepStage = 0;
    	stop = false;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	sweepStage = 0;
    	stop = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(rightSensor){
//    		stop = Robot.drive.getRightIRSensor();
//    	}else{
//    		stop = Robot.drive.getLeftIRSensor();
//    	}
    	if((sweepStage == 0 || sweepStage == 1)) {
    		if(Robot.drive.getLeftIRSensor() && Robot.drive.getRightIRSensor()) {
        		stop = true;
    		}
    	} else {
    		if(rightSensor){
        		stop = Robot.drive.getRightIRSensor();
        	}else{
        		stop = Robot.drive.getLeftIRSensor();
        	}
    	}
    	if(stop){
    		Robot.drive.arcadeDrive(0, 0);
    	}else{
    		if(sweepStage == 0) {
    			Robot.drive.arcadeDrive(0, speed);
    		} else if (sweepStage == 1){
    			Robot.drive.arcadeDrive(0, -speed);
    		} else {
    			Robot.drive.arcadeDrive(0, speed);
    		}
    	}
    	if(timeSinceInitialized() >= stage1Time && sweepStage == 0) {
    		sweepStage = 1;
    	}
    	if(timeSinceInitialized() >= stage1Time + stage2Time && sweepStage == 1) {
    		sweepStage = 2;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stop;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
