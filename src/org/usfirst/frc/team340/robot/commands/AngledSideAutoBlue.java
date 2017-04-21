package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AngledSideAutoBlue extends CommandGroup {

    public AngledSideAutoBlue() {
    	
    	/*
    	 * Stage 1: drive forward and turn
    	 */
    	addSequential(new ResetGyro());
    	addSequential(new DriveRails(-1), 2); // first number is speed (-1 to 1) second is time (seconds)
    	addSequential(new PIDTurn(20, false)); // first number is angle (positive to the left, negative to the right)
    	
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
