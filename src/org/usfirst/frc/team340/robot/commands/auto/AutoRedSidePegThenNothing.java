package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.DriveRails;
import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.DriveTurnToAngle;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoRedSidePegThenNothing extends CommandGroup {

    public AutoRedSidePegThenNothing() {
    	//close gear
    	addParallel(new ManualClawClose());
    	//drive forward
    	addSequential(new DriveStraightToDistance(99,.6*RobotMap.SPEED_SCALE),5);
    	//wait a moment
    	addSequential(new WaitCommand(.5));
    	//turn toward the peg
    	addSequential(new DriveTurnToAngle(-59),5);
    	//wait again
    	addSequential(new WaitCommand(.25));
    	//drive close to peg
    	addSequential(new DriveStraightToDistance(25,.65*RobotMap.SPEED_SCALE),4);
    	//ir sensor turn
    	addSequential(new DriveTurnBothSensors(-.35, false));
//    	addSequential(new DriveTurnTillSensor(-.3*RobotMap.SPEED_SCALE,false),2);
    	//TODO:add camera targeting peg position.
    	//wait for pilot to move peg
    	addSequential(new WaitCommand(.25));
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(25,.4*RobotMap.SPEED_SCALE),1);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	addSequential(new ReturnToStart(), 0.1);
    	//move away 
    	addSequential(new DriveStraightToDistance(-30,-.4*RobotMap.SPEED_SCALE), 5);
    	//retract pusher
    	addParallel(new ReturnToStart()); 
//    	addSequential(new DriveTurnToAngle(0), 3);
//    	addSequential(new DriveRails(-0.5), 3);
    }
}
