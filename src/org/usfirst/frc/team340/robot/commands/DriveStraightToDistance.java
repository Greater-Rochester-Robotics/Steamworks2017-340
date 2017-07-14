package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive to a distance in inches at speed based on percent of 12.8 volts
 * there is code to compensate for battery voltage drops. Speed should 
 * not exceed .7 because this will over shoot the target distance. This 
 * is likely due to slipage when the motor kicks on and/or the robot does
 * not slow fast enough. With out the drive train it might be hard to get 
 * a good set of PID constants.
 */
public class DriveStraightToDistance extends Command {
	//just as a standard, we will say a full battery is the following value
	double batteryV = 12.8;
	
	//values to pull from constructor. 
	double distance;
	double speed;
	
	//starting angle to keep robot straight
	double startAngle;
	boolean isReverse;
	
	//distance left to travel
	double distanceRemaining;
	
	/**
	 * Do not drive faster than .7 and distance is in inches.
	 * @param distanceInInches
	 * @param speed
	 */
    public DriveStraightToDistance(double distanceInInches, double speed) {
    	this.distance = distanceInInches;
    	//I am serious about not having a speed faster than .7
    	if(speed > .7*RobotMap.SPEED_SCALE){
    		this.speed = .7*RobotMap.SPEED_SCALE;
    	}else if(speed < -.7*RobotMap.SPEED_SCALE){
    		this.speed = -.7*RobotMap.SPEED_SCALE;
    	}else{
    		this.speed = speed*RobotMap.SPEED_SCALE;
    	}
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        //if driving backwards, condition for stopping is reversed
        if (speed < 0){
        	isReverse = true;
        }
    }

     // Called just before this Command runs the first time
     protected void initialize() {
    	 if(speed < 0 && distance > 0) {
    		 distance = -distance;
    	 }
    	 
    	 //reset the encoder, we will read distance from the starting point
    	 Robot.drive.resetEncoder();
    	 //to stop from drifting off course, we get the initial angle
      	 startAngle = Robot.drive.getYaw();
     }

     // Called repeatedly when this Command is scheduled to run
     protected void execute() {
    	 //start by compensating for any turn if the robot drifts
       	 double turnValue;
       	 double currentAngle = Robot.drive.getYaw();
       	 if (currentAngle > startAngle + 2){
       		 turnValue = 0.3;
       	 }else if (currentAngle > startAngle + 0.5){
       		 turnValue = 0.2;
       	 }else if (currentAngle < startAngle - 0.5){
       		 turnValue = -0.2;
       	 }else if (currentAngle < startAngle - 2){
       		 turnValue = -0.3;
       	 }else{
       		 turnValue = 0;
       	 }
       	 double voltageFactor = 12.8/ControllerPower.getInputVoltage();
       	 distanceRemaining = distance - Robot.drive.getDistance();
       	 if(isReverse)
       	 	distanceRemaining=-1*distanceRemaining;
       	 //drive at a speed, that is more static, based on current battery voltage
       	 double moveValue;	
//       	 if(distanceRemaining < -3){
//       		moveValue = .2*voltageFactor*speed/Math.abs(speed);
//       	 }else if((distanceRemaining <= 3) && (distanceRemaining >= -3)){
//       		 //but if we are at the target point stop
//       		 moveValue=0;
//       	 }else if(Math.abs(distanceRemaining) < 35){
//       		 //and if we are close slow down
//       		moveValue = -.35*speed*voltageFactor;
//       		if (Math.abs(moveValue) < .2*voltageFactor){
//       			moveValue = -.2*voltageFactor*speed/Math.abs(speed);
//            }
//       	 }else{
//       		 moveValue = -1*speed*voltageFactor;
//       	 }
       	 
       	 if(distanceRemaining >= 35) {
       		 moveValue = -speed * voltageFactor;
       	 } else if(distanceRemaining < 35 && distanceRemaining > 1.5) {
       		 moveValue = -.55 * speed * voltageFactor;
       	 } else if(distanceRemaining <= 1.5 && distanceRemaining >= -1.5) {
       		 moveValue = 0;
       	 } else if(distanceRemaining < -1.5 && distanceRemaining >= -10) {
       		 moveValue = .55 * speed * voltageFactor;
       	 } else {
       		 moveValue = speed * voltageFactor;
       	 }
       	 
       	 System.out.println("distance: "+Robot.drive.getDistance());
       	 //now use the turn speed and the forward speed
       	 Robot.drive.arcadeDrive(moveValue, turnValue);
     }

     // Make this return true when this Command no longer needs to run execute()
     protected boolean isFinished() {
//    	 if (isReverse){
//    		 if(Robot.drive.getDistance() < distance){
//    			 return true;
//    		 }
//    		 
//    		 if(distanceRemaining <= 3) {
//    			 return true
//    		 }
//    	 }else{
//    		 if(Robot.drive.getDistance() > distance){
//    			 return true;
//    		 }
//         }
    	 
    	 return Math.abs(distanceRemaining) <= 1.5;
    	 
//    	 return false;
     }

     // Called once after isFinished returns true
     protected void end() {
    	 //stop the robot if ended
    	 Robot.drive.arcadeDrive(0, 0);
     }

      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      protected void interrupted() {
    	  end();
      }
   }

