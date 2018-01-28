package org.usfirst.frc.team340.robot.commands.pathing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ObstacleCourse extends CommandGroup {

    public ObstacleCourse() {
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
    	addSequential(new Part1(0.3));
    	addSequential(new Part2(0.2));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new Part3(0.3));
    	addSequential(new Part4(0.2));
    	addSequential(new Part5(0.3));
    }
}
