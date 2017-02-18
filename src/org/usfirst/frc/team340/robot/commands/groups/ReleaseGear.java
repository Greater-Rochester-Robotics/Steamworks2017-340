package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team340.robot.commands.gears.ReadyToReleaseGear;
import org.usfirst.frc.team340.robot.commands.gears.RegurgitateGear;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToPosition;

/**
 * To release a gear the robot moves the claw down, opens
 * arm and starts spinning the rollers out until the gear 
 * is out. Then it raises the claw back into a safe position.
 */
public class ReleaseGear extends CommandGroup {

    public ReleaseGear() {
    	
    	addSequential(new ReadyToReleaseGear());
    	addSequential(new RegurgitateGear());
    	//addSequential(new ReturnToPosition());

    }
}