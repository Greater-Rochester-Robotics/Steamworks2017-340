package org.usfirst.frc.team340.robot.commands.gears;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Gear Sensor Test
 */
@Deprecated
public class GST extends Command {
    public GST() {
        requires(Robot.claw);
    }

    protected void execute() {
    	Robot.claw.printSensors();
    }

    protected boolean isFinished() {
        return false;
    }
}
