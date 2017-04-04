package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.DoNothing;
import org.usfirst.frc.team340.robot.commands.gears.LeftSideGearAuto;
import org.usfirst.frc.team340.robot.commands.groups.AutoMobility;
import org.usfirst.frc.team340.robot.commands.groups.AutoStraightNoVision;
import org.usfirst.frc.team340.robot.commands.groups.RightSideGearAuto;
import org.usfirst.frc.team340.robot.commands.groups.StraightOnGearAuto;
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
    
    public static NetworkTable visionTable;
	public static NetworkTable ledTable;
	public static NetworkTable cameraTable;
	
	public static PiLED led;
	
	private UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	
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
	    
	    visionTable = NetworkTable.getTable("vision");
	    ledTable = NetworkTable.getTable("led");
		led = new PiLED(ledTable);
		
//		camera.setResolution(640, 320);
		camera.setFPS(10);
		
		chooser = new SendableChooser<Command>();
		
		chooser.addDefault("Do nothing", new DoNothing());
		chooser.addObject("LEFT One gear", new LeftSideGearAuto());
		chooser.addObject("STRAIGHT ON one gear", new StraightOnGearAuto());
		chooser.addObject("RIGHT One gear ", new RightSideGearAuto());
//		chooser.addObject("Two Gear Right", new LoadingStationTwoGearAuto());
//		chooser.addObject("Center Right side Generic Two Gear Auto", new GenericTwoGearAuto());
//		chooser.addObject("Left side Generic Two Gear Auto", new GenericTwoGearLeft());
		chooser.addObject("Straight on No Vision", new AutoStraightNoVision());
		chooser.addObject("Mobility No gear", new AutoMobility());
	    SmartDashboard.putData("Auto Modes", chooser);
		//in order to make the MJPEG streamer work, need a table called Camera Publisher
//		cameraTable = NetworkTable.getTable("CameraPublisher");
		//pass address of camera to dashboard
//		cameraTable.putStringArray("RasPi Camera/streams", new String[]{"mjpg:http://roborio-340-frc.local:5800/?action=stream&type=.mjpg", "mjpg:http://10.3.40.21:5800/?action=stream"});
		//it doesn't matter that this is wrong, we need to tell the Dash top use usb
//		cameraTable.putString("RasPi Camera/source", "usb:/dev/video0");
		//not sure if needed, but just say it is connected
//		cameraTable.putBoolean("RasPi Camera/connected", true);
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
		SmartDashboard.putString("Camera Selection", "USB Camera 0");
//		SmartDashboard.putString("Auto Selected", SmartDashboard.getString("Auto Modes/selected",""));
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("Auto Modes/selected",
				SmartDashboard.getString("Auto Modes-selected",
						SmartDashboard.getString("Auto Modes/selected", "")));
		SmartDashboard.putString("Auto Selected", SmartDashboard.getString("Auto Modes/selected",""));
	}

	/**
	 * This is autonomous 
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutoStraightNoVision();
//        autonomousCommand = (Command) chooser.getSelected();
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
//		if(autonomousCommand != null) {
//			autonomousCommand.start();
//		}
		
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
