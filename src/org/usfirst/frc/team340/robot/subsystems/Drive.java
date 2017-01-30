package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveXbox;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls the drive train
 */
public class Drive extends Subsystem {
	private double leftMotorSpeed = 0;
    private double rightMotorSpeed = 0;
    
    private Solenoid drop;
    private Talon leftDrive;
    private Talon rightDrive;
    
    /**
     * Sets the variables for each of the
     * drive base's objects to the necessary
     * ports on the PDP
     */
    public Drive() {
		drop = new Solenoid(RobotMap.DROP_SOLENOID_CHANNEL);
		leftDrive = new Talon(RobotMap.LEFT_DRIVE_PORT);
		rightDrive = new Talon(RobotMap.RIGHT_DRIVE_PORT);
    }
    
    /**
     * Set the default command to
     * driving via X360 controller
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveXbox());
    }
    
    /**
     * Switch the value of the solenoid
     */
    public void toggleWheelsDown() {
    	drop.set(!drop.get());
    }
    
    /**
     * Set the value of the solenoid
     * @param isDown true for the wheel
     * down position and vice versa
     */
    public void setWheelsDown(boolean isDown) {
    	drop.set(isDown);
    }
    
    /**
     * Set the left drive speed
     * @param speed percentage of full
     * speed to go at [-1 ~ 1]
     */
    public void setLeftDrive(double speed) {
    	if(speed < -1) {
    		speed = -1;
    	} else if(speed > 1) {
    		speed = 1;
    	}
	
    	leftDrive.set(speed);
    }
    
    /**
     * Set the right drive speed; accounts
     * for negation
     * @param speed percentage of full
     * speed to go at [-1 ~ 1]
     */
    public void setRightDrive(double speed) {
    	if(speed < -1) {
    		speed = -1;
    	} else if(speed > 1) {
    		speed = 1;
    	}
	
    	rightDrive.set(-speed);
    }
    
    /**
     * Set both drive rails to the same
     * speed
     * @param speed the speed to set both
     * rails to
     */
    public void setBothDrive(double speed) {
    	setBothDrive(speed, speed);
    }
    
    /**
     * Set each drive rail's speed
     * separately
     * @param lSpeed left speed
     * @param rSpeed right speed
     */
    public void setBothDrive(double lSpeed, double rSpeed) {
    	setLeftDrive(lSpeed);
    	setRightDrive(rSpeed);
    }
    
    /**
	 * One joystick drive mode.
u	 * 
	 * @param moveValue
	 * @param rotateValue
	 */
	public void arcadeDrive(double moveValue, double rotateValue) {
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		
		setBothDrive(leftMotorSpeed, rightMotorSpeed);
	}
}
