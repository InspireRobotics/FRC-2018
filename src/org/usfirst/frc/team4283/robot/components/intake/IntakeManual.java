package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeManual extends Command {
	
	private final Joystick aux = HardwareMap.Joysticks.AUX;
	private final Intake intake = Components.INTAKE;
	
	public IntakeManual() {
		this.setInterruptible(true);
		this.requires(Components.INTAKE);
	}
	
	@Override
	protected void execute() {		
		if(aux.getRawButton(4)){
			intake.setIntakeSpeed(1);
		}else {
			intake.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
