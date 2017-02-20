package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.MoveToPeg;
import org.usfirst.frc.team340.robot.commands.auto.ReverseInArc;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingBack;
import org.usfirst.frc.team340.robot.commands.auto.StartMovingForward;
import org.usfirst.frc.team340.robot.commands.gears.AbortHarvest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LoadingStationTwoGearAuto extends CommandGroup {

    public LoadingStationTwoGearAuto() {
    	addSequential(new StartMovingForward());
//		addSequential(new TurnTowardsPeg());
    	addSequential(new DriveRails(-0.4, -0.65), 0.85);
		addSequential(new MoveToPeg(), 2.5);
		addSequential(new ScoreGear());
		addSequential(new StartMovingBack(), 0.5);
		addParallel(new HarvestGear());
		addSequential(new DriveRails(0.4, -0.4), 0.9);
		addSequential(new DriveRails(-0.5, -0.5), 1.2);
		addSequential(new DoNothing(), 0.3);
		addSequential(new AbortHarvest(), 0.2);
		addSequential(new DoNothing(), 0.2);
//		addSequential(new DriveRails(0.6, 1), 1.1);
		addSequential(new DriveRails(0.5, 0.5), 1.1);
		addSequential(new DriveRails(-0.3, 1), 0.3);
		addSequential(new MoveToPeg(), 2.3);
		addSequential(new ScoreGear());
		addSequential(new StartMovingBack(), 0.5);
		addSequential(new ReverseInArc());
		addSequential(new DriveRails(0.8, 0.8));
    }
}
