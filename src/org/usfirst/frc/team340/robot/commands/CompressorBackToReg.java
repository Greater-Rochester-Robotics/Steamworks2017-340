package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class CompressorBackToReg extends Command {

    public CompressorBackToReg() {
        super();
		requires(Robot.compressor);
		setRunWhenDisabled(true);
    }

    public boolean isFinished(){
    	double time = DriverStation.getInstance().getMatchTime(); 
    	return time <= 0 || time > 20;
    }

}
