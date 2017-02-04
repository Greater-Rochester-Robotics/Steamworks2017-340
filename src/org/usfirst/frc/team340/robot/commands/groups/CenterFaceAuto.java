package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.auto.ReverseInArc;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingBack;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterFaceAuto extends CommandGroup {
	public CenterFaceAuto(){
		
		addSequential(new StartMovingForward());
		addSequential(new ScoreGear());
		addSequential(new ReverseInArc());
		addSequential(new StartMovingBack());
		
		
	}
	
}
