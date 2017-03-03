package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompressorRegulation extends Command {
	public CompressorRegulation() {
		requires(Robot.compressor);
		setRunWhenDisabled(true);
	}
	
	@Override
	protected void initialize() {
		Robot.compressor.setCompressor(true);
	}
	
	@Override
	protected void execute() {
		if(Robot.compressor.getCompressor().getPressureSwitchValue() || Robot.compressor.getAltPressureSwitchStatus()) {
			Robot.compressor.setSpike(true);
		} else {
			Robot.compressor.setSpike(false);
		}
		
		SmartDashboard.putNumber("Pressure", Robot.compressor.getPressure());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
