package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualPusherRetract;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCenterPegThenNothing extends CommandGroup {

    public AutoCenterPegThenNothing() {
    	//close claw
    	addParallel(new ManualClawClose());
    	//drive forward
    	addSequential(new DriveStraightToDistance(69,.4),6);
    	//wait a moment
    	addSequential(new WaitCommand(.75));
    	//turn to peg
    	addSequential(new DriveTurnTillSensor(-.25,true),2);
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(9,.4),2);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	//pull the pusher back in
    	addParallel(new ReturnToStart());
    	//drive back off the peg
    	addSequential(new DriveStraightToDistance(-20,-.4),6);
    	
    }
}
