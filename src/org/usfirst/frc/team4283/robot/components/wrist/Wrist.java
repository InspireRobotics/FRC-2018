package org.usfirst.frc.team4283.robot.components.wrist;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem implements Component {
	
	private TalonSRX wrist;
	
	@Override
	public void robotInit() {
		wrist = new TalonSRX(HardwareMap.CAN.WRIST);
		
		this.setName(Subsystems.WRIST);
	}

	@Override
	public void teleOpInit() {
		wrist.set(ControlMode.PercentOutput, 0);
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
		wrist.set(ControlMode.PercentOutput, 0);
	}
	
	public void stop() {
		wrist.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new WristManual());
	}

	public void setWristSpeed(double d) {
		wrist.set(ControlMode.PercentOutput, d);
	}

}
