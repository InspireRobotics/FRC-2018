package org.usfirst.frc.team4283.robot.subsystem.wrist;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.subsystem.StandardSubsystem;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem implements StandardSubsystem {
	
	private Spark intakeLeft;
	private Spark intakeRight; 
	private Spark wrist;
	
	@Override
	public void robotInit() {
		intakeLeft = new Spark(HardwareMap.PWM.INTAKE_LEFT);
		intakeRight = new Spark(HardwareMap.PWM.INTAKE_RIGHT);
		
		this.setName(toString());
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
	
	public void setIntakeSpeed(double speed) {
		intakeLeft.set(-speed);
		intakeRight.set(speed);
	}
	
	public void stop() {
		intakeLeft.stopMotor();
		intakeRight.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	@Override
	public String toString() {
		return "Wrist";
	}

	public void setWristSpeed(double d) {
		wrist.set(d);
	}

}
