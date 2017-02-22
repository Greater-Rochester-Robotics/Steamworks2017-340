package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveXbox;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem that controls the drive train
 */
public class Drive extends Subsystem {
	
	//Makes maths easier when deciding what speed to set the motors to
	private double leftMotorSpeed;
    private double rightMotorSpeed;
    
    private AnalogInput frontUltrasonic;
    private AnalogInput backUltrasonic;
    private Talon leftDrive;
    private Talon rightDrive;
    
    private ADIS16448_IMU imu;   
    /**
     * Sets the variables for each of the
     * drive base's objects to the necessary
     * ports on the PDP
     */
    public Drive() {
    	leftMotorSpeed = 0;
    	rightMotorSpeed = 0;
    	
    	frontUltrasonic = new AnalogInput(RobotMap.FRONT_ULTRASONIC_PORT);
		backUltrasonic = new AnalogInput(RobotMap.BACK_ULTRASONIC_PORT);
		leftDrive = new Talon(RobotMap.LEFT_DRIVE_PORT);
		rightDrive = new Talon(RobotMap.RIGHT_DRIVE_PORT);
		
		imu = new ADIS16448_IMU(Axis.kX);
    }
    
    /**
     * Set the default command to
     * driving via X360 controller
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveXbox());
    }
    
    private double[] vals = new double[] {0, 0, 0};
    
    public double getYaw() {
    	vals[(int) Math.random() * 3] = imu.getAngleX();
    	return (vals[0] + vals[1] + vals[2]); // ghetto averaging
    }
    public void resetGyro() {
    	imu.reset();
    }
    
    public int getFrontUltrasonic() {
    	return frontUltrasonic.getValue();
    }
    
    public int getBackUltrasonic() {
    	return backUltrasonic.getValue();
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
	
    	leftDrive.set(-speed);
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
	
    	rightDrive.set(speed);
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

	public void goStop() {
		// TODO Auto-generated method stub
		
	}
}
