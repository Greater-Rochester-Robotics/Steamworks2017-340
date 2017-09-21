package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.VotableInput;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <h1><em>Claw</em></h1>
 * <br>
 * This class handles the mechanisms of the claw:
 * the pusher piston, arm, and claw itself.
 */
public class Claw extends Subsystem {
    private static final double ROLLER_IN_SPEED = 1.0; //TODO: perfect this
    private static final double ROLLER_OUT_SPEED = -1.0; //TODO: perfect this too
    	
    private static final Value ARM_UP = Value.kForward;
    private static final Value ARM_DOWN = Value.kReverse;
    private static final Value CLAW_OPEN = Value.kForward;
    private static final Value CLAW_CLOSED = Value.kReverse;
    private static final boolean PUSHER_OUT = true;
    private static final boolean PUSHER_IN = false;
    	
    private DoubleSolenoid claw;
    private DoubleSolenoid hinge;
    private Solenoid pusher;
    private Talon rollers;
    private VotableInput gearSensorLeft;
    private VotableInput gearSensorRight;
    private Relay leds;
    	
    /**
     * Constructs a {@link Claw} and sets all
     * the instance variables = they being a
     * speed controller and three double
     * solenoids
     */
    public Claw() {
    	claw = new DoubleSolenoid(RobotMap.CLAW_SOLENOID_FORWARD_CHANNEL, RobotMap.CLAW_SOLENOID_REVERSE_CHANNEL);
    	hinge = new DoubleSolenoid(RobotMap.ARM_SOLENOID_FORWARD_CHANNEL, RobotMap.ARM_SOLENOID_REVERSE_CHANNEL);
    	pusher = new Solenoid(RobotMap.PUSHER_SOLENOID_CHANNEL);
    	rollers = new Talon(RobotMap.CLAW_ROLLERS_PORT);
    	gearSensorLeft = new VotableInput(RobotMap.GEAR_SENSOR_LEFT_CHANNEL);
    	gearSensorRight = new VotableInput(RobotMap.GEAR_SENSOR_RIGHT_CHANNEL);
    	leds = new Relay(0);
    }
    
    public void ledsOn() {
    	leds.set(edu.wpi.first.wpilibj.Relay.Value.kForward);
    }
    
    public void ledsOff() {
    	leds.set(edu.wpi.first.wpilibj.Relay.Value.kOff);
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
		return !pusher.get();
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
		return pusher.get();
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
		/*If GearSensor1 is T, then make UseGearSensor1 F;
		*If GearSensor2 is T, then make UseGearSensor2 F;
		*Else set either one to T
		*/
		return gearSensorLeft.get() || gearSensorRight.get();
//		return VotableInput.vote(new VotableInput[] {gearSensorLeft, gearSensorRight});
	}
	
	/**
	 * Spin the rollers to
	 * regurgitate the gear
	 */
	public void spinOut() {
		rollers.set(ROLLER_OUT_SPEED);
	}
	
	/**
	 * @return if the gear has NOT
	 * successfully been acquired
	 */
	public boolean whenGearIsNotAcquired() {
		return !whenGearIsAcquired();
	}
	
	/**
	 * @return the left sensor object
	 */
	public VotableInput getLeftSensor() {
		return gearSensorLeft;
	}
	
	/**
	 * @return the right sensor object
	 */
	public VotableInput getRightSensor() {
		return gearSensorRight;
	}
    	
    public boolean leftSensorState() {
    	SmartDashboard.putBoolean("leftGearSensor",gearSensorLeft.get());
    	return gearSensorLeft.get();
    }
    	
    public boolean rightSensorState() {
    	SmartDashboard.putBoolean("rightGearSensor",gearSensorRight.get());
    	return gearSensorRight.get();
    }
    	
    /**
     * Prints the sensor values, their DIO port
     * numbers, and their side
     */
    public void printSensors() {
    	System.out.println("Left (9): " + gearSensorLeft.get() + "; right (8): " + gearSensorRight.get());
//    	SmartDashboard.putString("Gear Sensors", "Left (9): " + gearSensorLeft.get() + "; right (8): " + gearSensorRight.get());
    }
    
    /**
	 * Sets the default command by
	 * not setting a default command
	 */
    public void initDefaultCommand() {
        //No default command needed
    }
}
