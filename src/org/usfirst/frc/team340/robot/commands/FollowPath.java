package org.usfirst.frc.team340.robot.commands;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowPath extends Command {
    
	private double length = 103.699;
    private double dydx(double s) {
//    	double t = s/77.24;
//    	s = s - 10;
//    	System.out.println(t);
    	if(s == 0) {
    		s = s+0.01;
    	}
    	
    	return (-0.230851 * (Math.pow(s, 2) - 79.5487 * s - 282.685))/(s - 286.027);
//    	return 2*s;
//    	return (-2.68852*(Math.pow(s, 2) - 82.6 * s + 1815.55))/(Math.pow(s, 2)-85.2157*s - 406.763);
//    	return (-4 * (41 * Math.pow(t, 2) - 43 * t + 12))/(61 * Math.pow(t, 2) - 66*t-4);
//    	return (144 + -516 * t + 492 * Math.pow(t, 2))/ (12 + 198 * t + -183 * Math.pow(t, 2));
//    	return (201 + -624 * t + 594 * Math.pow(t, 2))/ (-168 + 936 * t + -792 * Math.pow(t, 2));
//    	return (-4.81561*(Math.pow(s, 2)-108.163*s+2368.45))/(Math.pow(s, 2)-108.169*s+337.814);
//    	return -Math.tan(s/36);
//    	if (s < -50) {
//    		return -2;
//    	} else {
//    		return 2;
//    	}
    }
    
    double lastangle = -1000.69;
    
    private double desiredAngle() {
//    	System.out.println("Distance: " + Robot.drive.getDistance() + ", dydx: " + dydx(Robot.drive.getDistance()));
//    	double angle = Math.atan(dydx(Robot.drive.getDistance()))*(180/Math.PI);
//    	if(lastangle == -1000.69) {
//    		lastangle = angle;
//    	} else if (Math.abs(lastangle- angle) > 60) {
//    		if(angle > 0) {
//    			angle = -180 + angle;
//    		} else {
//    			angle = 180 + angle;
//    		}
//    		lastangle = angle;
//    	}
    	
//    	if (angle > 60) {
//    		int tempAngle = (int) angle;
//    		tempAngle = tempAngle % 180;
//    		angle = (double) tempAngle;
//    	}
    	
    	return 0;
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(Robot.drive.getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + Robot.drive.getDistance() + " dydx: " + dydx(Robot.drive.getDistance()));
    	return angle;
    }

    private double leftSpeed = 0;
	private double rightSpeed = 0;
	private double startAngle = 0;
	private boolean goStraight = true;
	private double arcDivisor = 45;
	private double lastTime = 0;
	private boolean save = false;
    
    public FollowPath(double speed) {
    	requires(Robot.drive);
    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    	this.goStraight = true;
    }

    private double offset = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	Robot.drive.resetGyro();
    	Robot.drive.resetEncoder();
    	offset = 0;//Math.atan(dydx(0));
    	startAngle = Robot.drive.getYaw()+offset;
    	lastTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    
    protected void execute() {
//    	if(timeSinceInitialized() < 2) {
//    		startAngle = startAngle + (timeSinceInitialized()-lastTime)*15;    		
//    	} else {
//    		startAngle = startAngle - (timeSinceInitialized()-lastTime)*30;
//    	}
    	lastTime = timeSinceInitialized();
//    	System.out.println(Robot.drive.getYaw());
    	startAngle = startAngle + deltaAngle(Robot.drive.getYaw()+offset);
    	System.out.println("Raw angle: " + Robot.drive.getYaw() + " offset: " + offset);
    	System.out.println("Angle: " + (Robot.drive.getYaw() + offset));
    	double error = -deltaAngle(Robot.drive.getYaw()+offset);
//    	if(error > 180) {
//    		error = 180 - error;
//    	} else if (error < -180) {
//    		error = 180 + error;
//    	}
//    	System.out.println(error);
    	System.out.println("error: " + error);
    	if(goStraight && Math.abs(Robot.drive.getDistance()) > 3) {
    		double speed = leftSpeed;
        	Robot.drive.setBothDrive(
        			leftSpeed-((error)/(arcDivisor/Math.abs(speed))), 
        			rightSpeed+(((error)/(arcDivisor/Math.abs(speed)))));
    	} else {
        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if(useAngle && Math.abs(Robot.drive.getAngle() - endAngle) < angleTolerance) {
////    		System.out.println("GOT ANGLE");
//    	}
//    	if(useDistance && Robot.drive.getBackUltrasonic() - distance >= 0) {
//    		goodCount++;
//    		if(goodCount >= 10) {
//    			return true;
//    		}
//    	}
        return Robot.drive.getDistance() > length; // timeSinceInitialized() >= 3;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("YAW: " + Robot.drive.getAngle());
    	Robot.drive.setBothDrive(0, 0);
    	if(save) {
//        	SmartDashboard.putNumber("final dist", Robot.drive.getBackUltrasonic());
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
