package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
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
    	addSequential(new DriveStraightToDistance(75,.55*RobotMap.SPEED_SCALE),6);
    	//wait a moment
    	addSequential(new WaitCommand(.75));
    	//turn to peg
    	addSequential(new DriveTurnTillSensor(-.25*RobotMap.SPEED_SCALE,true),2);
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(9,.65*RobotMap.SPEED_SCALE),2);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	//pull the pusher back in
    	addParallel(new ReturnToStart());
    	//drive back off the peg
    	addSequential(new DriveStraightToDistance(-25,-.7*RobotMap.SPEED_SCALE),6);
    	
    }
}
