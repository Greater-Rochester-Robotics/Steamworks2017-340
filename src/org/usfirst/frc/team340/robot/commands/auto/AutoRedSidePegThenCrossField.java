package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.DriveTurnToAngle;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualPusherRetract;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRedSidePegThenCrossField extends CommandGroup {

    public AutoRedSidePegThenCrossField() {
    	//close gear
    	addParallel(new ManualClawClose());
    	//drive forward
    	addSequential(new DriveStraightToDistance(78,.4),6);
    	//wait a moment
    	addSequential(new WaitCommand(.25));
    	//turn toward the peg
    	addSequential(new DriveTurnToAngle(-63),5);
    	//wait again
    	addSequential(new WaitCommand(.25));
    	//drive close to peg
    	addSequential(new DriveStraightToDistance(58,.4),6);
    	//ir sensor turn
    	addSequential(new DriveTurnTillSensor(.3,false),2);
    	//TODO:add camera targeting peg position.
    	//wait for pilot to move peg
    	addSequential(new WaitCommand(.5));
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(9,.4),6);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	addSequential(new ReturnToStart(), 0.1);
    	//move away 
    	addSequential(new DriveStraightToDistance(-50,-.4), 5);
    	//retract pusher
    	addParallel(new ReturnToStart());
    	//turn rear toward other alliance
    	addSequential(new DriveTurnToAngle(-117),5);
    	//wait again
    	addSequential(new WaitCommand(.25));
    	//drive toward other alliance
    	//TODO:Make this distance much longer, short for in shop test
    	addSequential(new DriveStraightToDistance(-10,-8),5);
    }
}
