package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.MoveToPeg;
import org.usfirst.frc.team340.robot.commands.DriveResetGyro;
import org.usfirst.frc.team340.robot.commands.gears.AbortHarvest;
import org.usfirst.frc.team340.robot.commands.groups.HarvestGear;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLoadingStationTwoGear extends CommandGroup {

    public AutoLoadingStationTwoGear() {
    	addSequential(new DriveResetGyro());
    	addSequential(new DriveRails(-0.5), 1.8);
    	addSequential(new DriveRails(.3, -.3, 65, 4), 1);
    	addSequential(new DriveRails(-0.3), 0.6);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new MoveToPeg(), 1.25);
    	addSequential(new ScoreGear(), 0.5);
    	addSequential(new DriveRails(0.4), 0.6);
    	
    	addSequential(new DriveRails(-0.5, 0.5, -60, 5), 2);
    	addSequential(new DoNothing(), 0.5);
    	addParallel(new HarvestGear(), 2.5);
    	addSequential(new DriveRails(-0.3), 0.5);
    	addParallel(new DriveRails(-.5,-.3),.6);
    	//addParallel(new MoveToGear(), 2.5);
    	addSequential(new HarvestGear(), 2.5);
    	addSequential(new AbortHarvest());
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new DriveRails(0.6), 0.69);
    	addSequential(new DriveRails(0.5, -0.5, 53, 6));
    	addSequential(new DriveRails(-0.4), 0.55);
    	addSequential(new MoveToPeg(), 1);
    	addSequential(new ScoreGear(), 1);
    	addSequential(new DriveRails(0.5), 0.5);
    	addSequential(new DriveRails(0.75, .1, 215, 10), 2);
    	addSequential(new DriveRails(0.7),3);
//    	
		/*addSequential(new MoveToPeg(), 2.5); // vision
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
		addSequential(new DriveRails(0.8, 0.8));*/
    }
}
