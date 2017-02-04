package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><em>Claw</em></h1>
 * <br>
 * This class handles the mechanisms of the claw:
 * the pusher piston, arm, and claw itself.
 */
public class Claw extends Subsystem {
	private static final double ROLLER_IN_SPEED = .75; //TODO: perfect this
	private static final double ROLLER_OUT_SPEED = -.75; //TODO: perfect this too
	
	private static final Value ARM_UP = Value.kReverse;
	private static final Value ARM_DOWN = Value.kForward;
	private static final Value CLAW_OPEN = Value.kForward; //TODO: see if forward is open or closed
	private static final Value CLAW_CLOSED = Value.kReverse; //TODO: see if reverse is open or closed
	private static final Value PUSHER_OUT = Value.kForward;
	private static final Value PUSHER_IN = Value.kReverse;
	
	private DigitalInput gearSensorLeft;
	private DigitalInput gearSensorRight;
	private DoubleSolenoid claw;
	private DoubleSolenoid hinge;
	private DoubleSolenoid pusher;
	private TalonSRX rollers;
	
	/**
	 * Constructs a {@link Claw} and sets all
	 * the instance variables = they being a
	 * speed controller and three double
	 * solenoids
	 */
	public Claw() {
		gearSensorLeft = new DigitalInput(RobotMap.GEAR_SENSOR_LEFT_CHANNEL);
		gearSensorRight = new DigitalInput(RobotMap.GEAR_SENSOR_RIGHT_CHANNEL);
		claw = new DoubleSolenoid(RobotMap.CLAW_SOLENOID_FORWARD_CHANNEL, RobotMap.CLAW_SOLENOID_REVERSE_CHANNEL);
		hinge = new DoubleSolenoid(RobotMap.ARM_SOLENOID_FORWARD_CHANNEL, RobotMap.ARM_SOLENOID_REVERSE_CHANNEL);
		pusher = new DoubleSolenoid(RobotMap.PUSHER_SOLENOID_FORWARD_CHANNEL, RobotMap.PUSHER_SOLENOID_REVERSE_CHANNEL);
		rollers = new TalonSRX(RobotMap.CLAW_ROLLERS_PORT);
	}
    
	/**
	 * Raise the "arm" (the entire claw)
	 */
	public void goUp() {
		hinge.set(ARM_UP);
	}

	/**
	 * @return if the "arm" (the entire claw) is
	 * pulled up
	 */
	public boolean isUp() {
		return hinge.get().equals(ARM_UP);
	}
	
	/**
	 * Drop the "arm" (the entire claw)
	 */
	public void goDown() {
		hinge.set(ARM_DOWN);
	}
	
	/**
	 * @return if the "arm" (the entire claw) is
	 * pushed down
	 */
	public boolean isDown() {
		return hinge.get().equals(ARM_DOWN);
	}
	
	/**
	 * Open the claw
	 */
	public void goOpen() {
		claw.set(CLAW_OPEN);
	}
	
	/**
	 * @return if the claw if open
	 */
	public boolean isOpened() {
		return claw.get().equals(CLAW_OPEN);
	}
	
	/**
	 * Close the claw
	 */
	public void goClose() {
		claw.set(CLAW_CLOSED);
	}
	
	/**
	 * @return if the claw is closed
	 */
	public boolean isClosed() {
		return claw.get().equals(CLAW_CLOSED);
	}
	
	/**
	 * Retract the pusher
	 */
	public void goRetract() {
		pusher.set(PUSHER_IN);
	}
	
	/**
	 * @return if the pusher is
	 * retracted
	 */
	public boolean isRetracted() {
		return pusher.get().equals(PUSHER_IN);
	}
	
	/**
	 * Extend the pusher
	 */
	public void goExtend() {
		pusher.set(PUSHER_OUT);
	}
	
	/**
	 * @return if the pusher is
	 * extended
	 */
	public boolean isExtended() {
		return pusher.get().equals(PUSHER_OUT);
	}
	
	/**
	 * Stop the rollers
	 */
	public void spinStop() {
		rollers.set(0); //0 because no speed is stopped
	}
	
	/**
	 * @return if the rollers are stopped
	 */
	public boolean isStopped() {
		return rollers.getSpeed() == 0;
	}
	
	/**
	 * Spin the rollers to pull in a gear
	 */
	public void spinIn() {
		rollers.set(ROLLER_IN_SPEED);
	}
	
	/**
	 * Tests both gear sensors
	 * to determine gear status<br>
	 * <b><em>TODO: rename this</b></em>
	 * @return if the gear has
	 * successfully been acquired
	 */
	public boolean whenGearIsAcquired() {
		return gearSensorLeft.get() || gearSensorRight.get();
	}
	
	/**
	 * Spin the rollers to
	 * regurgitate the gear
	 */
	public void spinOut() {
		rollers.set(ROLLER_OUT_SPEED); //Negative to push out
	}
	
	/**
	 * @return if the gear has NOT
	 * successfully been acquired
	 */
	public boolean whenGearIsNotAcquired() {
		return !whenGearIsAcquired();
	}
	
	/**
	 * Sets the default command by
	 * not setting a default command
	 */
    public void initDefaultCommand() {
        //No default command needed
    }
}
