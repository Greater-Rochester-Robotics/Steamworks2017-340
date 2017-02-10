package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveRaiseWheels;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticDrop extends Subsystem {
	private Solenoid drop;
	
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
    public void setDrop(boolean isDown) {
    	drop.set(isDown);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveRaiseWheels());
    }
}

