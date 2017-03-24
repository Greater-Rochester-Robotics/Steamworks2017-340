package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><em>Climber</em></h1>
 * <br>
 * This class handles the mechanism of the climber,
 * more specifically the controlled rotation of the
 * drum
 */
public class Climber extends Subsystem {
    private static final int TOUCHPAD_CURRENT = 120; //TODO: perfect this
    private static final int LIFTOFF_CURRENT = 100; //TODO: perfect this
    	
    private static final double ENGAGEMENT_SPEED = 8; //TODO: perfect this
    private static final double CLIMB_SPEED = 10; //TODO: perfect this
    private static final double STAY_SPEED = 8; //TODO: perfect this
    	
    private CANTalon drumOne;
    private CANTalon drumTwo;
    	
    public Climber() {
    	drumOne = new CANTalon(RobotMap.CLIMBER_DRUM_ONE_ID);
    	drumTwo = new CANTalon(RobotMap.CLIMBER_DRUM_TWO_ID);
    	drumOne.setControlMode(TalonControlMode.Voltage.value);
    	drumTwo.setControlMode(TalonControlMode.Voltage.value);
    }
    	
    public double getCurrent() {
    	return (drumOne.getOutputCurrent() + drumTwo.getOutputCurrent()) / 2;
    }
    	
    public double getCurrentDrumOne() {
    	return drumOne.getOutputCurrent();
    }
    	
    public double getCurrentDrumTwo() {
    	return drumTwo.getOutputCurrent();
    }
    	
    /**
     * Bring the drum to its
     * engagement speed, used
     * for latching onto the
     * rope
     */
    public void goAtEngagementSpeed() {
    	goToSpeed(ENGAGEMENT_SPEED);
    }
    	
    /**
     * Set the drum to a given
     * speed as a percentage
     * @param speed the speed
     * at which the drum should
     * spin [-1 ~ 1]
     */
    private void goToSpeed(double speed){
    	drumOne.set(speed);
    	drumTwo.set(speed);
    }
    	
    /**
     * Check to see if the drum
     * has successfully latched
     * onto the rope
     * @return true if the drum
     * latched onto the rope
     */
    public boolean isEngagedWithRope(){
    	return getCurrent() > LIFTOFF_CURRENT;
    }
    	
    /**
     * Set the drum's rotation speed
     * to its climbing speed, used to
     * efficiently scale the rope
     */
    public void goAtClimbSpeed(){
    	goToSpeed(CLIMB_SPEED);
    }
    	
    /**
     * @return if the robot has successfully
     * engaged the touchpad
     */
    public boolean isEngagedWithTouchPad(){
    	return getCurrent() > TOUCHPAD_CURRENT;
    }
    	
    public void goStayingSpeed(){
    	goToSpeed(STAY_SPEED);
    }
    	
    /**
     * Stop the drum's rotation
     */
    public void goStopped(){
    	goToSpeed(0); //0 because no speed is stopped
    }
    	
    /**
     * @return if the drum's not
     * rotating (more specifically,
     * if the speed controller is
     * not directing the motor to
     * apply a constant speed of
     * rotation)
     */
    public boolean isStopped(){
    	return drumOne.get() ==0;
    }
    	
    public boolean isLiftingOff() {
    	return false; //TODO: this
    }
    
    /**
     * Sets the default command by
     * not setting a default command
     */
    public void initDefaultCommand() {
        //No default command
    }
}
