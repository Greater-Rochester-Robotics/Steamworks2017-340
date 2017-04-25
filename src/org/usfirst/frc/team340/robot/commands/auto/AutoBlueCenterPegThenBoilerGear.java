package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.DriveStraightToDistance;
import org.usfirst.frc.team340.robot.commands.DriveTurnTillSensor;
import org.usfirst.frc.team340.robot.commands.DriveTurnToAngle;
import org.usfirst.frc.team340.robot.commands.gears.ReturnToStart;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualPusherRetract;
import org.usfirst.frc.team340.robot.commands.groups.HarvestGear;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoBlueCenterPegThenBoilerGear extends CommandGroup {

    public AutoBlueCenterPegThenBoilerGear() {
    	//close claw
    	addParallel(new ManualClawClose());
    	//drive forward
    	addSequential(new DriveStraightToDistance(69,.6),6);
    	//wait a moment
    	addSequential(new WaitCommand(.75));
    	//turn to peg using the IR sensor, time out at 2 seconds
    	addSequential(new DriveTurnTillSensor(-.25,true),2);
    	//move gear on to peg
    	addSequential(new DriveStraightToDistance(9,.4),2);
    	//score the gear
    	addSequential(new ScoreGear(), 0.5);
    	//Back up a little bit
    	addSequential(new DriveStraightToDistance(-20,-.6),6);
    	//pull in the gear pusher
    	addParallel(new ReturnToStart());
    	//turn 90 degrees toward the blue boiler
        addSequential(new DriveTurnToAngle(90),4);
        //Let's drop the harvester while we can
        addParallel(new HarvestGear());
        //drive toward the blue boiler
    	addSequential(new DriveStraightToDistance(95,.6),6);
    	//turn back towards the driver's station
    	addSequential(new DriveTurnToAngle(90),4);
    	//drive toward the alliance wall
    	addSequential(new DriveStraightToDistance(35,.4),6);
        
    }
}
