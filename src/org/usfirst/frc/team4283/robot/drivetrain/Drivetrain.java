package org.usfirst.frc.team4283.robot.drivetrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {
	
	private static final int FR_PORT = 0, BR_PORT = 1, FL_PORT = 3, BL_PORT = 2;
	
	private DifferentialDrive drive;
	
	private final Spark fr, br, fl, bl;
	
	private double maxSpeedRight = 1.0, maxSpeedLeft = 1.0;
	
	private SpeedControllerGroup leftGroup, rightGroup;
	
	private Joystick driveController = new Joystick(0);
	
	private double leftSpeed;
	
	private double rightSpeed;
	
	private double increment;
	
	private AnalogGyro gyro = new AnalogGyro(0);
	
	private double gyroAngle;
	
	private double initialGyroDirection;
	
	private boolean isDrivingStraight = false;
	
	private double turnError;
	
	private long endTime;
	
	private double leftAuto, rightAuto;
	
	public Drivetrain() {
		//Create motor controller objects
		fr = new Spark(FR_PORT);
		fl = new Spark(FL_PORT);
		br = new Spark(BR_PORT);
		bl = new Spark(BL_PORT);
		
		//Create the side modules
		leftGroup = new SpeedControllerGroup(bl, fl);
		rightGroup = new SpeedControllerGroup(br, fr);
	
		//Init the the drive train
		drive = new DifferentialDrive(leftGroup, rightGroup);
		drive.setName("Drivetrain");
		//Just like Alex likes it
		
		//Add Stuff to the Dashboardt55
		SmartDashboard.putData(drive);
		SmartDashboard.putNumber("Speed Increment", .01);
	}
	
	public void teleopInit(){
		//Reset the gyro everytime TeleOp is restarted
		gyro.reset();
	}
	
	public void updateTeleOp(){
		//Get the input from the joysticks
		leftSpeed = driveController.getRawAxis(1);
		rightSpeed = driveController.getRawAxis(5);
		
		//Get the angle of the gyro
		gyroAngle = gyro.getAngle();
		
		//Get variables from the SmartDashboard
		maxSpeedRight = SmartDashboard.getNumber("Max Speed Right", 1);
		maxSpeedLeft = SmartDashboard.getNumber("Max Speed Left", 1);
		increment = SmartDashboard.getNumber("Speed Increment", .1);
		
		//Check if the drivetrain needs to get incremented
		if(driveController.getRawButton(5)){//Left
			maxSpeedLeft -= increment;
			maxSpeedRight -= increment;
		}
		if(driveController.getRawButton(6)){//Right
			maxSpeedLeft += increment;
			maxSpeedRight += increment;
		}
		
		//Check the max speed
		if(rightSpeed > maxSpeedRight)
			rightSpeed = maxSpeedRight;
		else if(rightSpeed < -maxSpeedRight)
			rightSpeed = -maxSpeedRight;
		
		if(leftSpeed > maxSpeedLeft)
			leftSpeed = maxSpeedLeft;
		else if(leftSpeed < -maxSpeedLeft)
			leftSpeed = -maxSpeedLeft;
		
		drive.tankDrive(leftSpeed, rightSpeed);
		
		//If driving straight
		if((Math.round(rightSpeed * 10.0) / 10.0) == (Math.round(leftSpeed * 10.0) / 10.0) && rightSpeed > 0.05 && leftSpeed > 0.05){
			if(!isDrivingStraight){//We just finished turning, get the angle 
				initialGyroDirection = gyroAngle;
				isDrivingStraight = true;
			}else{//Update to drive straight
				turnError = Math.abs(Math.abs(initialGyroDirection) - Math.abs(gyroAngle));
				if(gyroAngle < initialGyroDirection){
					drive.tankDrive(-leftSpeed + turnError * 0.01, -rightSpeed);
				}else if (gyroAngle > initialGyroDirection){
					drive.tankDrive(-leftSpeed, -rightSpeed + turnError * 0.01);
				}
			}
			
		}else{//We are turning so just drive normally
			drive.tankDrive(-leftSpeed, -rightSpeed);
			isDrivingStraight = false;
		}

		SmartDashboard.putNumber("Max Speed Right", maxSpeedRight);
		SmartDashboard.putNumber("Max Speed Left", maxSpeedLeft);
		SmartDashboard.putNumber("Gyro Angle", gyroAngle);
		SmartDashboard.putNumber("Driving Direction", initialGyroDirection);
		SmartDashboard.putBoolean("Driving Straight", isDrivingStraight);
		SmartDashboard.putNumber("Turn Error", turnError);
		SmartDashboard.putData(drive);
	}

	
	
	public void autoInit() {
		long time = (long) SmartDashboard.getNumber("Auto Time", 0);
		System.out.println(time);
		leftAuto = SmartDashboard.getNumber("Auto Left", 0.1);
		rightAuto = SmartDashboard.getNumber("Auto Right", 0.1);
		this.endTime = System.currentTimeMillis() + time;
//		 SmartDashboard.putNumber("Auto Left", 0);
//		 SmartDashboard.putNumber("Auto Right", 0);
	}
	
	public void autoUpdate(){
		if(endTime > System.currentTimeMillis()){
			System.out.printf("Left: %f, Right: %f\n", leftAuto, rightAuto);
			drive.tankDrive(leftAuto, rightAuto);
		}else{
			drive.tankDrive(0, 0);
		}
	}
}
