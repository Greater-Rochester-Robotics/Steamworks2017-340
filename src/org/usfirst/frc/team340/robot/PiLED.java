package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PiLED {
	private NetworkTable table;
	
	/**
	 * Creates a new instance of LEDs running on the Raspberry Pi
	 * @param table a networktable for communicating with the pi, probably "led"
	 */
	public PiLED(NetworkTable table) {
		this.table = table;
	}
	
	/**
	 * Hook the LEDs, taking away control from the pi
	 */
	public void hookLEDs() {
		table.putBoolean("hooked", true);
	}
	
	/**
	 * Unhooks the LEDs allowing the pi to control them again
	 */
	public void unhookLEDs() {
		table.putBoolean("hooked", false);
		leftOff();
		rightOff();
	}
	
	/**
	 * Set the color of the left side LEDs
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 */
	public void setLeftColor(int r, int g, int b) {
		table.putNumber("leftR", r);
		table.putNumber("leftG", g);
		table.putNumber("leftB", b);
	}
	
	/**
	 * Turn off the whole left side of LEDs
	 */
	public void leftOff() {
		setLeftColor(0, 0, 0);
	}
	
	/**
	 * Set the color of the left side LEDs and turn them off after the specified time
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 * @param time turn off the LEDs after this time has elapsed (milliseconds)
	 */
	public void setLeftColor(int r, int g, int b, double time) {
		setLeftColor(r, g, b);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep((long) time);
					leftOff();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		t.start();
	}
	
	/**
	 * Set the color of the right side LEDs
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 */
	public void setRightColor(int r, int g, int b) {
		table.putNumber("rightR", r);
		table.putNumber("rightG", g);
		table.putNumber("rightB", b);
	}
	
	/**
	 * Turn off the whole right side of LEDs
	 */
	public void rightOff() {
		setRightColor(0, 0, 0);
	}
	
	/**
	 * Set the color of the right side LEDs and turn them off after the specified time
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 * @param time turn off the LEDs after this time has elapsed (milliseconds)
	 */
	public void setRightColor(int r, int g, int b, double time) {
		setRightColor(r, g, b);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep((long) time);
					rightOff();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		t.start();
	}
	
	/**
	 * Set the color of all the LEDs
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 */
	public void setAllColor(int r, int b, int g) {
		setLeftColor(r, g, b);
		setRightColor(r, g, b);
	}
	
	/**
	 * Turns off all the LEDs
	 */
	public void allOff() {
		setAllColor(0, 0, 0);
	}
	
	/**
	 * Set the color of the all the LEDs and turn them off after the specified time
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 * @param time turn off the LEDs after this time has elapsed (milliseconds)
	 */
	public void setAllColor(int r, int g, int b, double time) {
		setAllColor(r, g, b);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep((long) time);
					allOff();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		t.start();
	}
}