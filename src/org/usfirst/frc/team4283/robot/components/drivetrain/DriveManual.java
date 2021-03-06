package org.usfirst.frc.team4283.robot.components.drivetrain;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveManual extends Command {

	private Joystick controller = HardwareMap.Joysticks.DRIVE;
	private Drivetrain drive = Components.DRIVE;
	
	private SmartDouble leftMax, rightMax;
	
	public DriveManual() {
		this.setInterruptible(true);
		
		leftMax = new SmartDouble(.77, "Left Drive Max");
		rightMax = new SmartDouble(.65, "Right Drive Max");
		
		this.requires(Components.DRIVE);
	}
	
	@Override
	protected void execute() {
		double leftSpeed = -controller.getRawAxis(1);
		double rightSpeed = -controller.getRawAxis(5);
		
		double leftMaxDouble = leftMax.get();
		double rightMaxDouble = rightMax.get();
		
		if(leftMaxDouble < 0){
			leftMaxDouble = 0;
		}
		
		if(rightMaxDouble < 0){
			rightMaxDouble = 0;
		}
			
		if(controller.getRawButton(5) && controller.getRawButton(6)){
			leftMaxDouble = 0.77;
			rightMaxDouble = 0.65;
		}else{
			//Check if the drivetrain needs to get incremented
			if(controller.getRawButton(5)){//Left
				rightMaxDouble -= .01;
				leftMaxDouble -= .01;
			}
			if(controller.getRawButton(6)){//Right
				leftMaxDouble += .01;
				rightMaxDouble += .01;
			}
		}
		
		
		
		//Check the max speed
		if(rightSpeed > rightMaxDouble)
			rightSpeed = rightMaxDouble;
		else if(rightSpeed < -rightMaxDouble)
			rightSpeed = -rightMaxDouble;
		
		if(leftSpeed > leftMaxDouble)
			leftSpeed = leftMaxDouble;
		else if(leftSpeed < -leftMaxDouble)
			leftSpeed = -leftMaxDouble;
		
		leftMax.set(leftMaxDouble);
		rightMax.set(rightMaxDouble);
		
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
