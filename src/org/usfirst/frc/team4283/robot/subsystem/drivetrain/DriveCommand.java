package org.usfirst.frc.team4283.robot.subsystem.drivetrain;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	private Joystick controller = HardwareMap.Joysticks.AUX;
	private Drivetrain drive = Subsystems.DRIVE;
	
	private SmartDouble leftMax, rightMax;
	
	public DriveCommand() {
		this.setInterruptible(true);
		
		leftMax = new SmartDouble(1, "Left Drive Max");
		rightMax = new SmartDouble(1, "Right Drive Max");
	}
	
	@Override
	protected void execute() {
		double leftSpeed = controller.getRawAxis(1);
		double rightSpeed = controller.getRawAxis(5);
		
		double leftMaxDouble = leftMax.get();
		double rightMaxDouble = rightMax.get();
		
		//Check if the drivetrain needs to get incremented
		if(controller.getRawButton(5)){//Left
			rightMaxDouble -= .1;
			leftMaxDouble -= .1;
		}
		if(controller.getRawButton(6)){//Right
			leftMaxDouble += .1;
			rightMaxDouble += .1;
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
