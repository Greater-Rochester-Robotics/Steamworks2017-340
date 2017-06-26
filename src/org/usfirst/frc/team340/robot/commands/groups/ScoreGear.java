package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.gears.PlaceGear;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;

/**
 *  To score a gear the arm opens and pusher extends out.
 *  When the gear is not in the claw, the pusher retracts back.
 */
public class ScoreGear extends CommandGroup {

    public ScoreGear() {
    	
    	addSequential(new PlaceGear());
    	addSequential(new DoNothing(), 1);
    	addSequential(new ReturnToStart());
    	
    }
}
