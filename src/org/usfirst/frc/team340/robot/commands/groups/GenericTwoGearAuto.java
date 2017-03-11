package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.MoveToPeg;
import org.usfirst.frc.team340.robot.commands.ResetGyro;
import org.usfirst.frc.team340.robot.commands.gears.AbortHarvest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GenericTwoGearAuto extends CommandGroup {

    public GenericTwoGearAuto() {
    	
    	addSequential(new ResetGyro());
    	addSequential(new DriveRails(-0.45), 1.2);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new MoveToPeg(), 2);
    	addSequential(new ScoreGear(), 0.5); //First Gear Scored
    	
    	addSequential(new DriveRails(.5),1);
    	addSequential(new DriveRails(-.5, 0.5, -130, 5), 3.5);
    	addSequential(new DriveRails(-.5),.5);

    	
    	addParallel(new DriveRails(-.4),.9);
    	addSequential(new HarvestGear(), 2.5); //Second Gear Pick Up
    	addSequential(new AbortHarvest());
    	
    	addSequential(new DriveRails(.5),.7);
    	addSequential(new DriveRails(.5, -0.5, 10, 5), 3.5);
    	
    	addSequential(new DriveRails(-0.45), .8);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new MoveToPeg(), 2);
    	addSequential(new ScoreGear(), 0.5); //Second Gear Scored
    	
    	addSequential(new DriveRails(0.4), 0.6);
    	addSequential(new DriveRails(0.3, 0.7, -180, 7), 3.5);
    	addSequential(new DriveRails(0.7), 3);
    	
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
