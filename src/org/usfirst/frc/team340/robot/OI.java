package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.DPad.Direction;
import org.usfirst.frc.team340.robot.commands.DriveXbox;
import org.usfirst.frc.team340.robot.commands.DropLowerWheels;
import org.usfirst.frc.team340.robot.commands.DropRaiseWheels;
import org.usfirst.frc.team340.robot.commands.DropToggleWheels;
import org.usfirst.frc.team340.robot.commands.MoveToPeg;
import org.usfirst.frc.team340.robot.commands.climb.manual.ManualClimberGoAtEngagementSpeed;
import org.usfirst.frc.team340.robot.commands.climb.manual.ManualClimberGoStopped;
import org.usfirst.frc.team340.robot.commands.climb.manual.ManualGoAtClimbSpeed;
import org.usfirst.frc.team340.robot.commands.climb.manual.ManualRollDrum;
import org.usfirst.frc.team340.robot.commands.gears.AbortHarvest;
import org.usfirst.frc.team340.robot.commands.gears.AbortRelease;
import org.usfirst.frc.team340.robot.commands.gears.AbortScore;
import org.usfirst.frc.team340.robot.commands.gears.GST;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualArmClose;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualArmOpen;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawDown;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualClawUp;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualRollersSpinIn;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualRollersSpinOut;
import org.usfirst.frc.team340.robot.commands.gears.manual.ManualSpinStop;
import org.usfirst.frc.team340.robot.commands.groups.HarvestGear;
import org.usfirst.frc.team340.robot.commands.groups.ReleaseGear;
import org.usfirst.frc.team340.robot.commands.groups.ScoreGear;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("unused")
public class OI {
	
	//DRIVER
	private Joystick driver = new Joystick(0);
	private Button driverA = new JoystickButton(driver, 1);
	private Button driverB = new JoystickButton(driver, 2);
	private Button driverX = new JoystickButton(driver, 3);
	private Button driverY = new JoystickButton(driver, 4);
	private Button driverLB = new JoystickButton(driver, 5);
	private Button driverRB = new JoystickButton(driver, 6);
	private Button driverBack = new JoystickButton(driver, 7);
	private Button driverStart = new JoystickButton(driver, 8);
	private Button driverL3 = new JoystickButton(driver, 9);
	private Button driverR3 = new JoystickButton(driver, 10);
	private Button driverDPadUp = new DPad(driver, Direction.up);
	private Button driverDPadDown = new DPad(driver, Direction.down);
	private Button driverDPadRight = new DPad(driver, Direction.right);
	private Button driverDPadLeft = new DPad(driver, Direction.left);
	
	//CO-DRIVER
	private Joystick coDriver = new Joystick(1);
	private Button coDriverA = new JoystickButton(coDriver, 1);
	private Button coDriverB = new JoystickButton(coDriver, 2);
	private Button coDriverX = new JoystickButton(coDriver, 3);
	private Button coDriverY = new JoystickButton(coDriver, 4);
	private Button coDriverLB = new JoystickButton(coDriver, 5);
	private Button coDriverRB = new JoystickButton(coDriver, 6);
	private Button coDriverBack = new JoystickButton(coDriver, 7);
	private Button coDriverStart = new JoystickButton(coDriver, 8);
	private Button coDriverL3 = new JoystickButton(coDriver, 9);
	private Button coDriverR3 = new JoystickButton(coDriver, 10);
	
	//MANUAL BOARD
	private Joystick board = new Joystick(2);
	private Button climbSwitch = new JoystickButton(board, 1);
	
	public OI() {
		
		//Manual testing for climber
		coDriverBack.whenPressed(new ManualClimberGoAtEngagementSpeed());
		coDriverBack.whenReleased(new ManualClimberGoStopped());
		coDriverStart.whenPressed(new ManualGoAtClimbSpeed());
		coDriverStart.whenReleased(new ManualClimberGoStopped());
		
		driverL3.whenPressed(new DropLowerWheels());
		driverL3.whenReleased(new DropRaiseWheels());
		driverR3.whenPressed(new DropToggleWheels());
		
		//Manual testing for claw
		driverX.whenPressed(new ManualArmClose());
		driverY.whenPressed(new ManualArmOpen());
		driverA.whenPressed(new ManualClawDown());
		driverB.whenPressed(new ManualClawUp());
		driverBack.whenPressed(new MoveToPeg());
		driverBack.whenReleased(new DriveXbox());
		driverStart.whenPressed(new HarvestGear());
		driverStart.whenReleased(new AbortHarvest());
//		driverStart.whenPressed(new ManualPusherExtend());
//		driverBack.whenPressed(new ManualPusherRetract());
		
		//Manual testing for rollers
		driverLB.whenPressed(new ManualRollersSpinIn());
		driverLB.whenReleased(new ManualSpinStop());
		driverRB.whenPressed(new ManualRollersSpinOut());
		driverRB.whenReleased(new ManualSpinStop());
		
		//Manual override climbing
		climbSwitch.whileHeld(new ManualRollDrum());
		
		// Claw command testing
		driverDPadDown.whenPressed(new HarvestGear());
		driverDPadDown.whenReleased(new AbortHarvest());
		
		driverDPadLeft.whenPressed(new ScoreGear());
		driverDPadLeft.whenReleased(new AbortScore());
		
		driverDPadUp.whenPressed(new ReleaseGear());
		driverDPadUp.whenReleased(new AbortRelease());
		
		driverDPadRight.whileHeld(new GST());
	}
	
	/**
	 * Enumerates the raw numbers assigned to
	 * the stick axes
	 */
	public enum Axis {
	    LEFT_X(0),
	    LEFT_Y(1),
	    LEFT_TRIGGER(2),
	    RIGHT_TRIGGER(3),
	    RIGHT_X(4),
	    RIGHT_Y(5);
	    
	    private int axis;
	    
	    private Axis(int axis) {
	    	this.axis = axis;
	    }
	    
	    public int getAxis() {
	    	return axis;
	    }
	}
	
	/**
	 * Get the raw value of the any of
	 * the driver's axes
	 * @param axis the axis
	 * @return the raw axis value, or 0 if
	 * in the range [-.05, .05]
	 * @see Axis
	 */
	public double getDriverAxis(Axis axis) {
	    return (driver.getRawAxis(axis.getAxis()) < -.05 || driver.getRawAxis(axis.getAxis()) > .05) ? driver.getRawAxis(axis.getAxis()) : 0;
	}
	
	/**
	 * Get the raw value of the any of
	 * the co-driver's axes
	 * @param axis the axis
	 * @return the raw axis value, or 0 if
	 * in the range [-.05, .05]
	 * @see Axis
	 */
	public double getCoDriverAxis(Axis axis) {
	    return (coDriver.getRawAxis(axis.getAxis()) < -.05 || coDriver.getRawAxis(axis.getAxis()) > .05) ? coDriver.getRawAxis(axis.getAxis()) : 0;
	}
	
	/**
	 * Rumble the driver's controller at the specified intensity
	 * @param intensity the intensity
	 */
	public void rumbleDriver(float intensity) {
		driver.setRumble(RumbleType.kLeftRumble, intensity);
		driver.setRumble(RumbleType.kRightRumble, intensity);
	}
}
//>>>>>>> code changes after testing
