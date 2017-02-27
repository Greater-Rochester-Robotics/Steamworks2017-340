package org.usfirst.frc.team340.robot.commands;

import java.io.IOException;
import java.io.PrintWriter;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SaveGearSensorVotes extends InstantCommand {
	public SaveGearSensorVotes() {
		requires(Robot.claw);
	}
	
	@Override
	protected void initialize() {
		try {
			PrintWriter writer = new PrintWriter("/home/lvuser/sensor_vote_results/sensor_votes.txt", "UTF-8");
			
			writer.print(Robot.claw.getLeftSensor().getChannel() + ":" + Robot.claw.getLeftSensor().getVotes() + "\n");
			writer.print(Robot.claw.getRightSensor().getChannel() + ":" + Robot.claw.getLeftSensor().getVotes());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
