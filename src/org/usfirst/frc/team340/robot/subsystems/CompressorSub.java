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

/**
 * This Subsystem gives use access to the compressor,
 * allows the compressor to be wired via a relay and
 * an independent switch and is the subsystem for the
 * pressure transducer. 
 * @author robotics
 */
public class CompressorSub extends Subsystem {
    private AnalogPotentiometer transducer;
    private Compressor compressor;
    private Relay spike;
    
    public CompressorSub() {
    	transducer = new AnalogPotentiometer(RobotMap.TRANSDUCER_CHANNEL, 250, -25);
    	compressor = new Compressor();
    	spike = new Relay(RobotMap.COMPRESSOR_SPIKE_CHANNEL, Direction.kForward);
    }
    
    /**
     * Constantly runs compressor
     *  passes pressure value to dashboard
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
    }

    /**
     * @return value of alt pressure switch
     */
    public boolean getAltPressureSwitchStatus() {
    	return true;//altPressureSwitch.get();
    }

    /**
     * @return true/false if Spike is forward/off
     */
    public boolean getSpikeStatus() {
    	return spike.get() == Value.kForward;
    }

    /**
     * @return the current pressure reading in PSI
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
     * @return the Spike (relay) object
     */
    public Relay getSpike() {
    	return spike;
    }
}
