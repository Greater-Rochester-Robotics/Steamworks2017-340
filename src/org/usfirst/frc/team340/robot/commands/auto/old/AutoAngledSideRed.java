package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.DrivePIDTurn;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.DriveResetGyro;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAngledSideRed extends CommandGroup {

    public AutoAngledSideRed() {
    	
    	/*
    	 * Stage 1: drive forward and turn
    	 */
    	addSequential(new DriveResetGyro());
    	addSequential(new DriveRails(-1), 6); // first number is speed (-1 to 1) second is time (seconds)
    	addSequential(new DrivePIDTurn(-20, false)); // first number is angle (positive to the left, negative to the right)
    	
    	addSequential(new DoNothing(), 0.5); // delay for pilot
    	
    	/*
    	 * Stage 2: finish forward and score
    	 */
    	addSequential(new DriveRails(-0.3), 1); // first number is speed (-1 to 1) second is time (seconds)
    	addSequential(new ScoreGear(), 0.5); // score gear
    	
    	/*
    	 * Stage 3: move backwards
    	 */
    	addSequential(new DriveRails(0.4), 0.5); // first number is speed (-1 to 1) second is time (seconds)
    }
}
