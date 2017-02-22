package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.MoveToGear;
import org.usfirst.frc.team340.robot.commands.MoveToPeg;
import org.usfirst.frc.team340.robot.commands.ResetGyro;
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
    	addSequential(new ResetGyro());
    	addSequential(new StartMovingForward(), 0.4);
//		addSequential(new TurnTowardsPeg());
    	addSequential(new DriveRails(-0.45, -0.75, 50, 4), 1.4);
		addSequential(new MoveToPeg(), 2.5); // vision
		addSequential(new ScoreGear());
		addSequential(new StartMovingBack(), 0.5);
		addSequential(new ResetGyro());
		addParallel(new HarvestGear());
		addSequential(new DriveRails(0.4, -0.4, 93, 4), 1.2);
		addSequential(new DriveRails(-0.5, -0.5), 0.6);
		addSequential(new MoveToGear(), 2); // vision
		addSequential(new DoNothing(), 0.3);
		addSequential(new AbortHarvest(), 0.2);
		addSequential(new DoNothing(), 0.2);
//		addSequential(new DriveRails(0.6, 1), 1.1);
//		addSequential(new ResetGyro());
		addSequential(new DriveRails(0.5, 0.5), 1.1);
		addSequential(new DriveRails(-0.3, 1, -3, 4), 0.3);
		addSequential(new MoveToPeg(), 2.3);
		addSequential(new ScoreGear());
		addSequential(new StartMovingBack(), 0.5);
		addSequential(new ReverseInArc());
		addSequential(new DriveRails(0.8, 0.8));
    }
}
