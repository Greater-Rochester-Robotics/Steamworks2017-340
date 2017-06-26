package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * <h1><em>GearSensorTrain</em></h1>
 * <br>
 * Trains the gear sensors for voting.<br>
 * <em>NOTE: this is <b>not</b> a 4-cycle diesel engine</em>
 */
public class GearSensorTrain extends TimedCommand {
	
	/**
	 * Constructs a new {@link GearSensorTrain} with a
	 * timeout, default three seconds
	 */
	public GearSensorTrain() {
		this(3);
	}
	
	/**
	 * Constructs a new {@link GearSensorTrain} with a
	 * timeout
	 * @param timeout seconds before ending
	 */
	public GearSensorTrain(double timeout) {
		super(timeout);
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		Robot.claw.getLeftSensor().train();
	}
	
	@Override
	protected void end() {
		Robot.claw.getRightSensor().train();
//		System.out.println("-TRAIN COMPLETE-");
	}
}
