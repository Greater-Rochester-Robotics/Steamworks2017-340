package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DropToggleWheels extends InstantCommand {
    public DropToggleWheels() {
        requires(Robot.drop);
    }
    
    protected void execute() {
    	Robot.drop.toggleOmni();
    }
}
