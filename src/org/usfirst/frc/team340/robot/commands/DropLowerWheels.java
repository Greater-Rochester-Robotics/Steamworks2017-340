package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DropLowerWheels extends InstantCommand {
    
    @Override
    protected void initialize() {
    	requires(Robot.drop);
    }
    
    @Override
    protected void execute() {
    	Robot.drop.dropOmni();
    }
}
