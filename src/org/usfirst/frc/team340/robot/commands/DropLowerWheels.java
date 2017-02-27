package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DropLowerWheels extends InstantCommand {
    
	public DropLowerWheels() {
		requires(Robot.drop);
	}
	
    @Override
    protected void initialize() {
    	
    }
    
    @Override
    protected void execute() {
    	Robot.drop.dropOmni();
    }
}
