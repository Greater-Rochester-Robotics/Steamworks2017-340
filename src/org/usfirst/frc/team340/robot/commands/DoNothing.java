package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does absolutely, completely,
 * entirely <em>nothing</em>
 */
public class DoNothing extends Command {
    public DoNothing() { requires(Robot.noSub); }
    protected boolean isFinished() { return false; }
}
