package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.CompressorRegulation;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorSub extends Subsystem {
	private AnalogPotentiometer transducer;
	private Compressor compressor;
	private DigitalInput altPressureSwitch;
	private Relay spike;
	
	public CompressorSub() {
		transducer = new AnalogPotentiometer(RobotMap.TRANSDUCER_CHANNEL, 250, -25);
		compressor = new Compressor();
		altPressureSwitch = new DigitalInput(RobotMap.ALT_SWITCH_CHANNEL);
		spike = new Relay(RobotMap.COMPRESSOR_SPIKE_CHANNEL, Direction.kForward);
	}

	/**
	 * Constantly regulates compressor
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CompressorRegulation());
	}
	
	/**
	 * Enable or disable the spike
	 * @param isOn turn spike on/off
	 */
	public void setSpike(boolean isOn) {
		if(isOn) {
			spike.set(Value.kForward);
		} else {
			spike.set(Value.kOff);
		}
	}
	
	/**
	 * Enable or disable the compressor
	 * @param isOn turn compressor on/off
	 */
	public void setCompressor(boolean isOn) {
		if(isOn) {
			compressor.start();
		} else {
			compressor.stop();
		}
	}
	
	/**
	 * @return true/false if compressor is enabled/disabled
	 */
	public boolean getCompressorStatus() {
		return compressor.enabled();
//		return false;
	}
	
	/**
	 * @return value of alt switch
	 */
	public boolean getAltPressureSwitchStatus() {
		return altPressureSwitch.get();
	}
	
	/**
	 * @return true/false if Spike is forward/off
	 */
	public boolean getSpikeStatus() {
		return spike.get() == Value.kForward;
	}
	
	/**
	 * @return the current pressure reading
	 */
	public double getPressure() {
		return transducer.get();
	}
	
	/**
	 * @return the transducer object
	 */
	public AnalogPotentiometer getTransducer() {
		return transducer;
	}
	
	/**
	 * @return the compressor object
	 */
	public Compressor getCompressor() {
		return compressor;
	}
	
	/**
	 * @return the alternate switch object
	 */
	public DigitalInput getAltPressureSwitch() {
		return altPressureSwitch;
	}
	
	/**
	 * @return the Spike (relay) object
	 */
	public Relay getSpike() {
		return spike;
	}
}
