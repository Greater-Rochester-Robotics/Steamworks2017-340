package org.usfirst.frc.team340.robot.commands.climb.manual;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Rolls the climbing drum in case the robot
 * needs to be nudged up the rope
 */
public class ManualRollDrum extends Command {
    public ManualRollDrum() {
        requires(Robot.climber);
    }

    protected void execute() {
    	Robot.climber.goAtClimbSpeed();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.climber.goStopped();
    }
}
