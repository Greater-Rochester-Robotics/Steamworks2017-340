package org.usfirst.frc.team340.robot;

//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;

import org.usfirst.frc.team340.robot.commands.groups.LoadingStationTwoGearAuto;
import org.usfirst.frc.team340.robot.subsystems.Claw;
import org.usfirst.frc.team340.robot.subsystems.Climber;
import org.usfirst.frc.team340.robot.subsystems.CompressorSub;
import org.usfirst.frc.team340.robot.subsystems.Drive;
import org.usfirst.frc.team340.robot.subsystems.NoSub;
import org.usfirst.frc.team340.robot.subsystems.PneumaticDrop;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static Drive drive;
    public static Claw claw;
    public static Climber climber;
    public static CompressorSub compressor;
    public static NoSub noSub;
    public static PneumaticDrop drop;
    public static OI oi;

    Command autonomousCommand;
    
    public static NetworkTable visionTable;
	public static NetworkTable ledTable;
	
	public static PiLED led;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	    drive = new Drive();
	    claw = new Claw();
	    climber = new Climber();
	    compressor = new CompressorSub();
	    drop = new PneumaticDrop();
	    noSub = new NoSub();
	    oi = new OI();
	    
	    visionTable = NetworkTable.getTable("vision");
		ledTable = NetworkTable.getTable("led");
		led = new PiLED(ledTable);
	    // chooser.addObject("My Auto", new MyAutoCommand());
//	    SmartDashboard.putData("Auto modes", chooser);
	}
	
	public static double avgValue() {
//		return 0;
		
		return visionTable.getNumber("avg", 0);
	}
	
	public static double distValue() {
//		return 0;
		return visionTable.getNumber("dist", 1);
	}
	
	public static boolean linedValue() {
		return visionTable.getBoolean("lined", true);
	}
	
	public static void setMode(int mode) {
		visionTable.putNumber("mode", mode);
	}
	
	/**
	 * Save a calibration picture to the pi sd card
	 */
	public static void calibrate() {
		visionTable.putBoolean("calibrate", true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new LoadingStationTwoGearAuto();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if(autonomousCommand != null) {
			autonomousCommand.start();
		}
		
//		try {
//			char[] chars = new char[8]; //char array to hold the chars in the file
//			FileReader reader = new FileReader("sensor_votes.txt"); //Object to read the chars in the file
//			
//			reader.read(chars); //Put all the file's chars into the proper array
//			reader.close(); //Close the reader
//			
//			String str = new String(chars); //String containing the full char array
//			String[] lines = str.split("\n"); //Splits the String into two - one per line
//			ArrayList<String> numsList = new ArrayList<String>(4); //ArrayList of Strings that will contain the numbers
//			
//			//Double for-each loop to set each String in numsList to the numbers in the file
//			for(String line : lines) {
//				for(String num : line.split(":")) {
//					numsList.add(num);
//				}
//			}
//			
//			String[] numsArray = numsList.toArray(new String[4]); //String array version of the String ArrayList
//			int[] nums = new int[4]; //int array that will contain the numbers from the file as ints
//			
//			//Puts all the numbers in the String array into the int array
//			for(int i = 0; i < 4; i++) {
//				nums[i] = Integer.parseInt(numsArray[i]);
//			}
//			
//			//Applies the vote numbers to the sensors
//			for(int i = 0; i < 4; i += 2) {
//				if(nums[i] == claw.getLeftSensor().getChannel()) {
//					claw.getLeftSensor().setVotes(nums[i + 1]);
//				} else if(nums[i] == claw.getRightSensor().getChannel()) {
//					claw.getRightSensor().setVotes(nums[i + 1]);
//				} else {
//					System.err.println("Mismatch between saved port and true port!");
//				}
//			}
//			
//			System.out.println("Left (9): " + claw.getLeftSensor().getVotes() + "; right (8): " + claw.getRightSensor().getVotes());
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
