package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DriveDropWheels extends InstantCommand {
    
    @Override
    protected void initialize() {
	requires(Robot.drive);
    }
    
    @Override
    protected void execute() {
	Robot.drive.setDrop(true);
    }
}
