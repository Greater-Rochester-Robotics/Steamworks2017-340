package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><em>Climber</em></h1>
 * <br>
 * This class handles the mechanism of the climber,
 * more specifically the controlled rotation of the
 * drum
 */
@SuppressWarnings("unused")
public class Climber extends Subsystem {
	private static final int TOUCHPAD_CURRENT = 120; //TODO: perfect this
	private static final int LIFTOFF_CURRENT = 100; //TODO: perfect this
	
	private static final double ENGAGEMENT_SPEED = 0.5; //TODO: perfect this
	private static final double CLIMB_SPEED = 0.75; //TODO: perfect this
	
	private Spark drumOne;
	private Spark drumTwo; //TODO: see if we need to worry about both (check for sync)
	
	public Climber() {
		drumOne = new Spark(RobotMap.CLIMBER_DRUM_PORT_ONE);
		drumTwo = new Spark(RobotMap.CLIMBER_DRUM_PORT_TWO);
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
		return false; //TODO: make this
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
		return false;
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
