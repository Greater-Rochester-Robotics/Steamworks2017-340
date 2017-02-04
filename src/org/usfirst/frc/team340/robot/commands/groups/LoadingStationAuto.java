package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.auto.ReverseInArc;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingForward;
import org.usfirst.frc.team340.robot.commands.auto.TurnTowardsPeg;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LoadingStationAuto extends CommandGroup {
	public LoadingStationAuto(){
		
		addSequential(new StartMovingForward());
		addSequential(new TurnTowardsPeg());
		addSequential(new ScoreGear());
		addSequential(new ReverseInArc());
	}

	
}
