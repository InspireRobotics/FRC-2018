package org.usfirst.frc.team4283.robot.intake;


import javax.swing.plaf.basic.BasicLabelUI;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Colin
 */
public class Intake {
	
	private AnalogInput cubeDetector = new AnalogInput(1);
	private Joystick auxController = HardwareMap.Joysticks.AUX;
	private Spark intakeLeft = new Spark(HardwareMap.PWM.INTAKE_LEFT);
	private Spark intakeRight = new Spark(HardwareMap.PWM.INTAKE_RIGHT);

	private Compressor compressor = new Compressor();
	private TwoValvePneumatic left = new TwoValvePneumatic(HardwareMap.Pneumatic.LEFT_INTAKE, 0, "Left Pneumatic");
	private TwoValvePneumatic right = new TwoValvePneumatic(HardwareMap.Pneumatic.RIGHT_INTAKE, 0, "Right Pneumatic");
	
	private double maxSpeedRight = 0.5, maxSpeedLeft = 0.5;
	private long startAuto;
	
	public Intake() {
		compressor.setClosedLoopControl(true);
		compressor.start();
	
//		SmartDashboard.putNumber("Intake Max Speed Left", 1);
//		SmartDashboard.putNumber("Intake Max Speed Right", 1);
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
		double rightSpeed = auxController.getRawAxis(5);
		
		// Check the max speed
		if (rightSpeed > maxSpeedRight)
			rightSpeed = maxSpeedRight;
		else if (rightSpeed < -maxSpeedRight)
			rightSpeed = -maxSpeedRight;

		if (leftSpeed > maxSpeedLeft)
			leftSpeed = maxSpeedLeft;
		else if (leftSpeed < -maxSpeedLeft)
			leftSpeed = -maxSpeedLeft;

//		if(cubeDetector.getValue() > 180){
//			rightSpeed /= 1.2;
//			leftSpeed /= 1.2;
//		}
		
		// Set the intake
		intakeLeft.set(leftSpeed);
		intakeRight.set(rightSpeed);
			
		// Put variables on the Smart Dashboard
		SmartDashboard.putNumber("Max Speed Right", maxSpeedRight);
		SmartDashboard.putNumber("Max Speed Left", maxSpeedLeft);
	}

	public void autoInit() {
		left.raise();
		right.raise();
		
		startAuto = System.currentTimeMillis();
	}

	public void updateIntake() {
		if(startAuto + 5000 > System.currentTimeMillis()){
			intakeLeft.set(-.5);
			intakeRight.set(.5);
		}else if(startAuto + 7500 > System.currentTimeMillis()){
			intakeLeft.set(.5);
			intakeRight.set(-.5);
		}else{
			intakeLeft.stopMotor();
			intakeRight.stopMotor();
		}
	}

}