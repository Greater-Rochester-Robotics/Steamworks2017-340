package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveRaiseWheels;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class handles the pneumatic drop aka
 * the omni-drop aka the parallel drop aka the
 * not-so-perpendicular drop
 */
public class PneumaticDrop extends Subsystem {
	private Solenoid drop;
	
	/**
	 * Constructs a new instance of {@link PneumaticDrop}
	 * by assigning the drop's solenoid to the correct
	 * channel
	 */
	public PneumaticDrop() {
		drop = new Solenoid(RobotMap.DROP_SOLENOID_CHANNEL);
	}
	
	/**
     * Switch the value of the solenoid
     */
    public void toggleWheels() {
    	drop.set(!drop.get());
    }
    
    /**
     * Set the value of the solenoid
     * @param isDown true for the wheel
     * down position and vice versa
     */
    private void setDrop(boolean isDown) {
    	drop.set(isDown);
    }
    
    /**
     * Drop the omnis
     */
    public void dropOmni() {
    	setDrop(true);
    }
    
    /**
     * Raise the omnis
     */
    public void raiseOmni() {
    	setDrop(false);
    }

    /**
     * Sets the default command by raising the
     * drop on-enable
     */
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveRaiseWheels());
    }
}

