package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team340.robot.commands.gears.HarvestMode;
import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.gears.GearClamp;
import org.usfirst.frc.team340.robot.commands.gears.RaisingClaw;

/**
 * Robot moves claw down, opens arm, and spins rollers
 * until it sees a gear. When it sees a gear it closes 
 * the claw and stops rollers. It then raises the claw
 * to secure the gear. 
 */
public class HarvestGear extends CommandGroup {

    public HarvestGear() {
    	
    	addSequential(new HarvestMode());
    	addSequential(new GearClamp());
    	addSequential(new DoNothing(), 0.2);
    	addSequential(new RaisingClaw());

    }
}
