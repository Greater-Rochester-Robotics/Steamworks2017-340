package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompressorOff extends Command {
	public CompressorOff() {
		requires(Robot.compressor);
		setRunWhenDisabled(true);
	}
	
	@Override
	protected void initialize() {
		Robot.compressor.setCompressor(false);
		Robot.compressor.setSpike(false);
	}
	
	@Override
	protected void execute() {
		Robot.compressor.setCompressor(false);
		SmartDashboard.putNumber("Pressure", Math.round(Robot.compressor.getPressure()));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
