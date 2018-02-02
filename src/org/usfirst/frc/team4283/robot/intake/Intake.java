package org.usfirst.frc.team4283.robot.intake;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	private AnalogInput cubeDetector = new AnalogInput(1);
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
		int rawCubeSensorValue = cubeDetector.getValue();
		if (rawCubeSensorValue > 500) {
			intakeFull = true;
		}
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

		// Set the intake, prevent intake from sucking in when full
		if (intakeFull()) {
			if (leftSpeed > 0.0) {
				intakeLeft.set(leftSpeed);
			}
			if (rightSpeed > 0.0) {
				intakeRight.set(rightSpeed);
			}
		} else if (!intakeFull()) {
			intakeLeft.set(leftSpeed);
			intakeRight.set(rightSpeed);
		}

		// Put variables on the Smart Dashboard
		SmartDashboard.putNumber("Max Speed Right", maxSpeedRight);
		SmartDashboard.putNumber("Max Speed Left", maxSpeedLeft);
		SmartDashboard.putBoolean("Power Cube in Intake", intakeFull());
	}

}