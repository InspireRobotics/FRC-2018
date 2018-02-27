package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements Component {
	
	private static final SmartDouble intakeSpeed = new SmartDouble(.5, "Intake Speed");
	private static final SmartDouble cubeSensorLabel = new SmartDouble(0, "Cube Sensor");
	private static final SmartDouble cubeSensorLimit = new SmartDouble(200, "Cube Limit");

	private Spark intakeLeft;
	private Spark intakeRight; 
	private AnalogInput cubeSensor = new AnalogInput(HardwareMap.Analog.BALL_SENSOR); 
	
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
		cubeSensorLabel.set(getCubeSensor());
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
		speed *= intakeSpeed.get();
		intakeLeft.set(speed + (0.1 * Math.signum(speed)));
		intakeRight.set(-speed);
	}
	
	public double getCubeSensor(){
		return cubeSensor.getValue();
	}
	
	public void stop() {
		intakeLeft.stopMotor();
		intakeRight.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new IntakeManual());
	}

	public static SmartDouble getCubesensorlimit() {
		return cubeSensorLimit;
	}
	
}
