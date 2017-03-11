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
		Robot.compressor.setSpike(Robot.compressor.getAltPressureSwitchStatus());
//		System.out.println(Robot.compressor.getCompressorStatus());
		SmartDashboard.putNumber("Pressure", Math.round(Robot.compressor.getPressure()/5) * 5);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
