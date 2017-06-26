package org.usfirst.frc.team340.robot.commands.auto;

import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.DriveTurnToAngle;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualPusherRetract;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueSidePegThenNothing extends CommandGroup {

    public AutoBlueSidePegThenNothing() {
    	//close gear
    	addParallel(new ManualClawClose());
    	//drive forward
    	addSequential(new DriveStraightToDistance(78,.4),6);
    	//wait a moment
    	addSequential(new WaitCommand(.25));
    	//turn toward the peg
    	addSequential(new DriveTurnToAngle(63),5);
    	//wait again
    	addSequential(new WaitCommand(.25));
    	//drive close to peg
    	addSequential(new DriveStraightToDistance(58,.4),6);
    	//ir sensor turn
    	addSequential(new DriveTurnTillSensor(-.3,true),2);
    	//TODO:add camera targeting peg position.
    	//wait for pilot to move peg
    	addSequential(new WaitCommand(.5));
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(9,.4),2);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	addSequential(new ReturnToStart(), 0.1);
    	//move away 
    	addSequential(new DriveStraightToDistance(-50,-.4), 5);
    	//retract pusher
    	addParallel(new ReturnToStart());
    }
}
