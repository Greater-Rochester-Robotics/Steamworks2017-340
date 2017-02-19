package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.GearSensorTrain;
import org.usfirst.frc.team340.robot.commands.SaveGearSensorVotes;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearSensorTraining extends CommandGroup {
	public GearSensorTraining() {
		addSequential(new GearSensorTrain());
		addSequential(new DoNothing(), 1); //This will be light-based notification
		addSequential(new GearSensorTrain());
		addSequential(new DoNothing(), 1); //This will be light-based notification
		addSequential(new GearSensorTrain());
		addSequential(new SaveGearSensorVotes());
	}
}
