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
public class AutoGenericTwoGearLeft extends CommandGroup {
	
    public AutoGenericTwoGearLeft() {
    	
    	addSequential(new DriveResetGyro());
    	addSequential(new DriveRails(-0.75), 1.15);
    	addSequential(new DriveRails(-.4, .4, -65, 7), 1);
    	addSequential(new DoNothing(), .25);
    	addSequential(new DriveRails(-0.3), 0.2);
    	addSequential(new MoveToPeg(), .75);
    	addSequential(new ScoreGear(), 0.5);	//First Gear Scored
    	addSequential(new DriveRails(0.4), 0.6);
    	
    	addSequential(new DriveRails(-.5, .5, -180, 5), 2);
    	addSequential(new DriveRails(-0.75), 1);
    	
    	addParallel(new HarvestGear(), 2.5);
    	addSequential(new DriveRails(-0.25,.2), .4);
    	addSequential(new DoNothing(), .25);
    	//addParallel(new MoveToGear(), 2.5);
    	addParallel(new DriveRails(-.5),.75);
    	addSequential(new HarvestGear(), 1); //Second Gear Pick Up
    	addSequential(new DoNothing(), .25);
    	addSequential(new HarvestGear(), 1); //Second Gear Pick Up
    	addSequential(new DoNothing(), .25);
    	addSequential(new AbortHarvest());
    	
    	
    	addSequential(new DriveRails(.5),.4);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new DriveRails(-3, .3, -360, 10), 2);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new DriveRails(-0.5), 1);

    	addSequential(new DoNothing(), 0.3);
    	addSequential(new DriveRails(-.3, .3, -420, 10), 1);
    	addSequential(new DriveRails(-0.35), 0.4);
    	addSequential(new MoveToPeg(), 1.5);
    	addSequential(new ScoreGear(), 0.5);	//Second Gear Scored
    	addSequential(new DriveRails(0.4), 0.6);
    	
    	addSequential(new DriveRails(0,.7, -540, 10), 1);
    	addSequential(new DriveRails(.75), 3);

    	
    	
    	
    	
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
