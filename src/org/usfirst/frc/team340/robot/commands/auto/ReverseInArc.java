package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseInArc extends Command {

    public ReverseInArc() {
        requires(Robot.drive);
    	setTimeout(2); //Two seconds
    }

    protected void initialize() {
    	Robot.drive.setBothDrive(-1, 0.5);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
