package org.usfirst.frc.team340.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
	//FIXME: talk to electrical about the correct numbers
	public static final int LEFT_DRIVE_PORT = 0;
	public static final int RIGHT_DRIVE_PORT = 1;
	public static final int CLAW_ROLLERS_PORT = 2;
	public static final int DROP_SOLENOID_CHANNEL = 3;
	public static final int ARM_SOLENOID_FORWARD_CHANNEL = 4;
	public static final int ARM_SOLENOID_REVERSE_CHANNEL = 5;
	public static final int CLAW_SOLENOID_FORWARD_CHANNEL = 7;
	public static final int CLAW_SOLENOID_REVERSE_CHANNEL = 8;
	public static final int PUSHER_SOLENOID_FORWARD_CHANNEL = 9;
	public static final int PUSHER_SOLENOID_REVERSE_CHANNEL = 10;
	public static final int CLIMBER_DRUM_PORT_ONE = 11;
	public static final int CLIMBER_DRUM_PORT_TWO = 12;

	public static final double CLIMB_MOTORS_ENGAGEMENT_SPIN_UP_TIME = 0.5;
}
