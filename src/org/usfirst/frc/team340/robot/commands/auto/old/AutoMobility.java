package org.usfirst.frc.team340.robot.commands.auto.old;

import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMobility extends CommandGroup {

    public AutoMobility() {
    	addSequential(new DriveStraightToDistance(95,.7), 2.5);
    }
}
