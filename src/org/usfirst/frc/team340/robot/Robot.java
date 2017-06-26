package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.auto.AutoBlueSidePegThenNothing;
import org.usfirst.frc.team340.robot.commands.auto.AutoSwitchBasedSelector;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoAngledSideBlue;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoAngledSideRed;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoLeftSideGear;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoMobility;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoRightSideGear;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoStraightNoVision;
import org.usfirst.frc.team340.robot.commands.auto.old.AutoStraightOnGear;
import org.usfirst.frc.team340.robot.subsystems.Claw;
import org.usfirst.frc.team340.robot.subsystems.Climber;
import org.usfirst.frc.team340.robot.subsystems.CompressorSub;
import org.usfirst.frc.team340.robot.subsystems.Drive;
import org.usfirst.frc.team340.robot.subsystems.NoSub;
import org.usfirst.frc.team340.robot.subsystems.PneumaticDrop;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

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
    
    private Command autonomousCommand;
    
    private static AutoSwitchBasedSelector autoMode;
    
    public static NetworkTable visionTable;
	public static NetworkTable ledTable;
	public static NetworkTable cameraTable;
	
	public static PiLED led;
	
	private UsbCamera camera;
	
	private SendableChooser<Command> chooser;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Construct all the Subsystems
		drive = new Drive();
	    claw = new Claw();
	    climber = new Climber();
	    compressor = new CompressorSub();
	    drop = new PneumaticDrop();
	    noSub = new NoSub();	
	    //AFTER subsystem construction, construct the OI with buttons
	    oi = new OI();
	    
	    //pass subsystems to Dashboard for debug
	    SmartDashboard.putData(drive);
	    SmartDashboard.putData(claw);
	    SmartDashboard.putData(climber);
	    SmartDashboard.putData(compressor);
	    SmartDashboard.putData(drop);
	    SmartDashboard.putData(noSub);
	    
	    //Construct the autonomous selector that runs off switches.
	    autoMode = new AutoSwitchBasedSelector();
	    
	    visionTable = NetworkTable.getTable("vision");
	    ledTable = NetworkTable.getTable("led");
		led = new PiLED(ledTable);
		
		//create a camera server object
		camera = CameraServer.getInstance().startAutomaticCapture();
		//set the resolution to 640x360, I want wide screen resolution
		camera.setResolution(640, 360);
		//set frame rate to 7, keep it at 7 or lower, there is a limit
		camera.setFPS(7);
			
		chooser = new SendableChooser<Command>();
		
		chooser.addDefault("Do nothing", new DoNothing());
		chooser.addObject("LEFT One gear", new AutoLeftSideGear());
		chooser.addObject("STRAIGHT ON one gear", new AutoStraightOnGear());
		chooser.addObject("RIGHT One gear ", new AutoRightSideGear());
		chooser.addObject("Forward Blue Side", new AutoAngledSideBlue());
		chooser.addObject("Forward Red Side", new AutoAngledSideRed());
//		chooser.addObject("Two Gear Right", new LoadingStationTwoGearAuto());
//		chooser.addObject("Center Right side Generic Two Gear Auto", new GenericTwoGearAuto());
//		chooser.addObject("Left side Generic Two Gear Auto", new GenericTwoGearLeft());
		chooser.addObject("Straight on No Vision", new AutoStraightNoVision());
		chooser.addObject("Mobility No gear", new AutoMobility());
	    SmartDashboard.putData("Auto Modes", chooser);
		
	}
	
	public static double avgValue() {	
		return visionTable.getNumber("avg", 0);
	}
	
	public static double distValue() {
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
		//force the Dashboard to display the camera 
		SmartDashboard.putString("Camera Selection", "USB Camera 0");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//send sensor values to dashboard to test them for PreMatch
		drive.getRightIRSensor();
		drive.getLeftIRSensor();
		drive.pushEncoderToDashboard();
		claw.leftSensorState();
		claw.rightSensorState();
		
		//left over from the SendableChoser method, left if we want to go back
		SmartDashboard.putString("Auto Modes/selected",
				SmartDashboard.getString("Auto Modes-selected",
						SmartDashboard.getString("Auto Modes/selected", "")));
		SmartDashboard.putString("Auto Selected", SmartDashboard.getString("Auto Modes/selected",""));
		
		//autoMode switches read and put command in autonomousCommand
        autonomousCommand = autoMode.getSelected();//(Command) chooser.getSelected();
        //Push the name of the auto command to run back to the dashboard
		SmartDashboard.putString("Auto To Be Run", autonomousCommand.getName());
	}

	/**
	 * This is autonomous 
	 */
	@Override
	public void autonomousInit() {
		//if the auto command is null, like if the robot code restarts in auto, command is pulled again
    	if(autonomousCommand == null){
    		autonomousCommand = autoMode.getSelected();//(Command) chooser.getSelected();
    	}
    	// sart the autonomous command, don't start if it is null
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
		// teleop starts running. 
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
