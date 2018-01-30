package org.usfirst.frc.team4283.robot.subsystem.wrist;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.subsystem.StandardSubsystem;

import edu.wpi.first.wpilibj.Spark;

public class Wrist implements StandardSubsystem {
	
	private Spark intakeLeft;
	private Spark intakeRight; 
	
	@Override
	public void robotInit() {
		intakeLeft = new Spark(HardwareMap.PWM.INTAKE_LEFT);
		intakeRight = new Spark(HardwareMap.PWM.INTAKE_RIGHT);
	}

	@Override
	public void teleOpInit() {
		intakeLeft.stopMotor();
		intakeRight.stopMotor();	
		}

	@Override
	public void teleOpPeriodic() {
		
	}

	@Override
	public void autoInit() {
		
	}

	@Override
	public void autoPeriodic() {
		
	}

	@Override
	public void disable() {
		intakeLeft.stopMotor();
		intakeRight.stopMotor();
	}
	
	public void setSpeed(double speed) {
		intakeLeft.set(speed);
		intakeRight.set(speed);
	}

}
