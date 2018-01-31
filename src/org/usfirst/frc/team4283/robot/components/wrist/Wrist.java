package org.usfirst.frc.team4283.robot.components.wrist;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem implements Component {
	
	private Spark wrist;
	
	@Override
	public void robotInit() {
		wrist = new Spark(HardwareMap.PWM.WRIST);
		
		this.setName(Subsystems.WRIST);
	}

	@Override
	public void teleOpInit() {
		wrist.stopMotor();	
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
		wrist.stopMotor();
	}
	
	public void stop() {
		wrist.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new WristManual());
	}

	public void setWristSpeed(double d) {
		wrist.set(d);
	}

}
