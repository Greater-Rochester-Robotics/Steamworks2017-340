package org.usfirst.frc.team340.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Speed scaler
	public static final double SPEED_SCALE = .92;
	
	//PWM Channels
    public static final int LEFT_DRIVE_PORT = 0;
    public static final int RIGHT_DRIVE_PORT = 1;
    public static final int CLAW_ROLLERS_PORT = 2;
    
    //Solenoid Output Channels
    public static final int DROP_SOLENOID_CHANNEL = 4;
    public static final int ARM_SOLENOID_FORWARD_CHANNEL = 2;
    public static final int ARM_SOLENOID_REVERSE_CHANNEL = 3;
    public static final int CLAW_SOLENOID_FORWARD_CHANNEL = 0;
    public static final int CLAW_SOLENOID_REVERSE_CHANNEL = 1;
    public static final int PUSHER_SOLENOID_CHANNEL = 5;
    
    //CAN Talon IDs
    public static final int CLIMBER_DRUM_ONE_ID = 6;
    public static final int CLIMBER_DRUM_TWO_ID = 5;
    
    //Digital IO ports
    public static final int AUTO_SWITCH_ALLIANCE_COLOR = 0;
    public static final int AUTO_SWITCH_AIRSHIP_POS = 1;
    public static final int AUTO_SWITCH_TWO = 2;
    public static final int AUTO_SWITCH_THREE = 3;
    public static final int IR_LEFT_SENSOR = 5;
    public static final int IR_RIGHT_SENSOR = 4;
    public static final int ENCODER_FWD_CHANNEL = 6;
    public static final int ENCODER_REV_CHANNEL = 7;
    public static final int GEAR_SENSOR_LEFT_CHANNEL = 9;
    public static final int GEAR_SENSOR_RIGHT_CHANNEL = 8;
    
    //Spike Relay Channels
    public static final int COMPRESSOR_SPIKE_CHANNEL = 1; //TODO: get the channel for this, not used if compressor uses PCM
    
    //Analog Input ports
    public static final int TRANSDUCER_CHANNEL = 3;
    
	public static final double CLIMB_MOTORS_ENGAGEMENT_SPIN_UP_TIME = 0.5;
	
	//Analog ports
	public static final int FRONT_ULTRASONIC_PORT = 0;
	public static final int BACK_ULTRASONIC_PORT = 1;
}
