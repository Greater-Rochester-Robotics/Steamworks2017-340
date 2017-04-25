package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.auto.ReverseInArc;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingBack;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingForward;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterFace extends CommandGroup {
	public AutoCenterFace(){
		
		addSequential(new StartMovingForward());
		addSequential(new ScoreGear());
		addSequential(new ReverseInArc());
		addSequential(new StartMovingBack());
		
		
	}
	
}
