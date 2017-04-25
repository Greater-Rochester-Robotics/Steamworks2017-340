package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightNoVision extends CommandGroup {

    public AutoStraightNoVision() {
    	addSequential(new DriveRails(-0.3), 2.7);
    	addSequential(new DoNothing(), 0.69);
    	addSequential(new DriveRails(-0.3), 0.7);
    	addSequential(new DoNothing(), 0.5);
    	addSequential(new ScoreGear(), 0.3);
    	addSequential(new DoNothing(), 0.3);
    	addSequential(new DriveRails(0.3), 1);
//    	addSequential(new DriveRails(0.5, -0.5), 0.7);
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
