package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.CalibrateVision;
import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DrivePIDTurn;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.DriveResetGyro;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideGear extends CommandGroup {

    public AutoRightSideGear() {
    	addSequential(new DriveResetGyro());
//    	addSequential(new DriveRails(-0.5, true), 1.67);
    	addSequential(new DriveRails(-0.3, 720, true));
    	addSequential(new DrivePIDTurn(60, false), 4);
//    	addSequential(new DriveRails(-.3, .3, -67, 4), 1);
    	addSequential(new DoNothing(), 0.5);
    	addSequential(new DriveRails(-0.3), 1);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new CalibrateVision());
//    	addSequential(new MoveToPeg(), 1.7);
    	addSequential(new ScoreGear(), 0.5);
    	addSequential(new DriveRails(0.4), 0.6);
//    	addSequential(new ResetGyro());
//    	addSequential(new DriveRails(-0.5), 1.77);
//    	addSequential(new DriveRails(.3, -.3, 63, 4), 1);
//    	addSequential(new DriveRails(-0.3), 0.6);
//    	addSequential(new DoNothing(), 0.3);
//    	addSequential(new CalibrateVision());
//    	addSequential(new MoveToPeg(), 1.7);
//    	addSequential(new ScoreGear(), 0.5);
//    	addSequential(new DriveRails(0.4), 0.6);
//    	addSequential(new DriveRails(0.95, 0.2, 220, 7), 2);
//    	addSequential(new DriveRails(0.7), 3);
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
