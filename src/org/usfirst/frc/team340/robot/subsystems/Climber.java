package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;

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
	
	private static final double ENGAGEMENT_SPEED = 0.5; //TODO: perfect this
	private static final double CLIMB_SPEED = 0.75; //TODO: perfect this
	private static final double STAY_SPEED = 0.5; //TODO: figure out what the speed is
	
	private CANTalon drumOne;
	private CANTalon drumTwo; //TODO: see if we need to worry about both (check for sync)
	
	public Climber() {
		drumOne = new CANTalon(RobotMap.CLIMBER_DRUM_PORT_ONE);
		drumTwo = new CANTalon(RobotMap.CLIMBER_DRUM_PORT_TWO);
		
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
	}
	
	/**
	 * Check to see if the drum
	 * has successfully latched
	 * onto the rope
	 * @return true if the drum
	 * latched onto the rope
	 */
	public boolean isEngagedWithRope(){
		return getCurrent()>this.LIFTOFF_CURRENT; //TODO: make this
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
		return getCurrent()>this.TOUCHPAD_CURRENT;
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
	
	public boolean ifLiftingOff() {
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
