 package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team340.robot.commands.climb.ClimbMotorsOn;
import org.usfirst.frc.team340.robot.commands.climb.DriveIntoRope;
import org.usfirst.frc.team340.robot.commands.climb.StayAtTop;
import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.climb.Climb;

/**
 * Drive motors are under human control and climb motors turn on. 
 * Driver drives into rope and climb motors are at engage speed.
 * Once the climb has started the drive motors stop and climb motors run at climb speed.
 */
public class Climbing extends CommandGroup {
    public Climbing() {
    	addSequential(new ClimbMotorsOn(RobotMap.CLIMB_MOTORS_ENGAGEMENT_SPIN_UP_TIME));
    	addSequential(new DriveIntoRope());
    	addSequential(new Climb());
    	addSequential(new StayAtTop());
    }
}
