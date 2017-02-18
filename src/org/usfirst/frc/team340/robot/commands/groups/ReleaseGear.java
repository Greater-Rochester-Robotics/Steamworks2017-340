package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.gears.ReadyToReleaseGear;
import org.usfirst.frc.team340.robot.commands.gears.RegurgitateGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To release a gear the robot moves the claw down, opens
 * arm and starts spinning the rollers out until the gear 
 * is out. Then it raises the claw back into a safe position.
 */
public class ReleaseGear extends CommandGroup {

    public ReleaseGear() {
    	
    	addSequential(new ReadyToReleaseGear());
    	addSequential(new DoNothing(), 0.5);
    	addSequential(new RegurgitateGear());
    }
}
