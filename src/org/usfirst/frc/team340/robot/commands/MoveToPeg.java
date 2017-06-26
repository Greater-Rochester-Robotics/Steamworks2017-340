package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToPeg extends Command {

	double speed = -0.65;
	double tolerance = 30;
	double forwardMod = 6;
	double fastMod = 8;
	
	int center = 310;
	
	double dist = 0;
	double targetDeg = 0;
	boolean hasReset = false;
	
	public MoveToPeg() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		hasReset = false;
		Robot.setMode(0);
	}

	public double pixelToDeg(double pixel) {
		return (pixel - center)/640 * 61;
	}
	
	Timer t = new Timer();
	boolean tstarted = false;
	double gyro = 0;
	boolean lined = true;
	double endDist = 100;
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double avg = Robot.avgValue();
//		dist = Robot.distValue();
		dist = Robot.drive.getFrontUltrasonic();
		lined = Robot.linedValue();
//		System.out.println(dist);
		if(!lined) { 
			Robot.oi.rumbleDriver(0.9f);
		} else {
			Robot.oi.rumbleDriver(0.0f);
		}
//		if(dist < endDist) {
//			Robot.drive.setLeftDrive(0);
//			Robot.drive.setRightDrive(0);
//			return;
//		}
		if(center - avg > tolerance*fastMod) {
			Robot.drive.setRightDrive(speed/3);
			Robot.drive.setLeftDrive(-speed/3);
		} else if (center - avg > tolerance) {
			if(center - avg <= tolerance * forwardMod) {
				Robot.drive.setRightDrive(speed/2); // arcing motion
				Robot.drive.setLeftDrive(speed/7);
			} else {
				Robot.drive.setRightDrive(speed/4);
				Robot.drive.setLeftDrive(-speed/4); // slower spin on point
			}
		} else if(avg - center > tolerance*fastMod) {
			Robot.drive.setRightDrive(-speed/3); // spin on point
			Robot.drive.setLeftDrive(speed/3);
		} else if (avg - center > tolerance) {
			if(avg - center <= tolerance * forwardMod) {
				Robot.drive.setRightDrive(speed/7); // arcing
				Robot.drive.setLeftDrive(speed/2);
			} else {
				Robot.drive.setRightDrive(-speed/4); // slower spin on point
				Robot.drive.setLeftDrive(speed/4);
			}
		} else if (Math.abs(center - avg) <= tolerance * forwardMod) {
			Robot.drive.setLeftDrive(speed/2); // go straight forward at half speed
			Robot.drive.setRightDrive(speed/2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.oi.rumbleDriver(0.0f);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
