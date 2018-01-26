package org.usfirst.frc.team4283.robot.intake;


import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Colin
 */
public class Intake {

	private Joystick auxController = HardwareMap.Joysticks.AUX;
	private Spark intakeLeft = new Spark(HardwareMap.PWM.INTAKE_LEFT);
	private Spark intakeRight = new Spark(HardwareMap.PWM.INTAKE_LEFT);

	private Compressor compressor = new Compressor();
	private TwoValvePneumatic left = new TwoValvePneumatic(HardwareMap.Pneumatic.LEFT_INTAKE, 0, "Left Pneumatic");
	private TwoValvePneumatic right = new TwoValvePneumatic(HardwareMap.Pneumatic.RIGHT_INTAKE, 0, "Right Pneumatic");
	
	private double maxSpeedRight = 1.0, maxSpeedLeft = 1.0;
	
	public Intake() {
		compressor.setClosedLoopControl(true);
		compressor.start();
	}
	
	public void updateTeleOp() {
		updateWheels();
		
		left.updateDashboard();
		right.updateDashboard();
	}
	
	public boolean intakeFull() {
		boolean intakeFull = false;
		
		return intakeFull;
	}
	
	private void updateWheels() {
		double leftSpeed = -auxController.getRawAxis(1);
		double rightSpeed = -auxController.getRawAxis(5);

		// Get variables from the SmartDashboard
		maxSpeedRight = SmartDashboard.getNumber("Max Speed Right", 1);
		maxSpeedLeft = SmartDashboard.getNumber("Max Speed Left", 1);

		// Check the max speed
		if (rightSpeed > maxSpeedRight)
			rightSpeed = maxSpeedRight;
		else if (rightSpeed < -maxSpeedRight)
			rightSpeed = -maxSpeedRight;

		if (leftSpeed > maxSpeedLeft)
			leftSpeed = maxSpeedLeft;
		else if (leftSpeed < -maxSpeedLeft)
			leftSpeed = -maxSpeedLeft;

		// Set the intake
		intakeLeft.set(leftSpeed);
		intakeRight.set(rightSpeed);

		// Put variables on the Smart Dashboard
		SmartDashboard.putNumber("Max Speed Right", maxSpeedRight);
		SmartDashboard.putNumber("Max Speed Left", maxSpeedLeft);
	}

}