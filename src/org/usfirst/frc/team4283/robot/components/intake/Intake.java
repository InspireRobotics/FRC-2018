package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements Component {
	
	private Spark intakeLeft;
	private Spark intakeRight; 
	
	@Override
	public void robotInit() {
		intakeLeft = new Spark(HardwareMap.PWM.INTAKE_LEFT);
		intakeRight = new Spark(HardwareMap.PWM.INTAKE_RIGHT);
		
		this.setName(Subsystems.INTAKE);
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
		intakeLeft.set(speed);
		intakeRight.set(-speed);
	}
	
	public void stop() {
		intakeLeft.stopMotor();
		intakeRight.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new IntakeManual());
	}

}
