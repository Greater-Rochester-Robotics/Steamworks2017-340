package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveXbox;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that controls the drive train
 */
public class Drive extends Subsystem {
	
    //Makes maths easier when deciding what speed to set the motors to
    private double leftMotorSpeed;
    private double rightMotorSpeed;
    
    private double[] logVals = new double[3];
    
    private AnalogInput frontUltrasonic;
    private AnalogInput backUltrasonic;
    private Talon leftDrive;
    private Talon rightDrive;
    private Encoder encoder;
    private DigitalInput rightIRSensor;
    private DigitalInput leftIRSensor;
    
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
		
		encoder = new Encoder(RobotMap.ENCODER_FWD_CHANNEL,RobotMap.ENCODER_REV_CHANNEL);
		encoder.setDistancePerPulse(.049);
		
		rightIRSensor = new DigitalInput(RobotMap.IR_RIGHT_SENSOR);
		leftIRSensor = new DigitalInput(RobotMap.IR_LEFT_SENSOR);
		
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
    
    /**
     * return the distance
     * @return
     */
    public double getDistance(){
    	return encoder.getDistance();
    }
    /**
     * reset the encoder.
     */
    public void resetEncoder(){
    	encoder.reset();
    }
    /**
     * For testing purposes, push the encoder value to the dashboard.
     */
    public void pushEncoderToDashboard(){
    	SmartDashboard.putNumber("encoder", this.getDistance());
    }
    
    public boolean getRightIRSensor(){
    	SmartDashboard.putBoolean("rightIRSensor", !rightIRSensor.get());
    	return !rightIRSensor.get();
    }
    
    public boolean getLeftIRSensor(){
    	SmartDashboard.putBoolean("leftIRSensor", !leftIRSensor.get());
    	return !leftIRSensor.get();
    }
    
    /**
     * retrieve the angle of the robot from the gyro
     * @return angle
     */
    public double getYaw() {
    	logVals[(int) Math.random() * 3] = imu.getAngleX();
    	return (logVals[0] + logVals[1] + logVals[2]); // ghetto averaging
    }
    public void resetGyro() {
    	imu.reset();
    }
    
    public int getFrontUltrasonic() {
    	return frontUltrasonic.getValue();
    }
    
    public int getBackUltrasonic() {
    	return backUltrasonic.getAverageValue();
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
     * 
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
    	setBothDrive(0);
    }
}
