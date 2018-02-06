package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeManual extends Command {
	
	private static final SmartDouble intakeSpeed = new SmartDouble(.5, "Intake Speed");
	
	private final Joystick aux = HardwareMap.Joysticks.AUX;
	private final Intake intake = Components.INTAKE;
	
	public IntakeManual() {
		this.setInterruptible(true);
		this.requires(Components.INTAKE);
	}
	
	@Override
	protected void execute() {
		if(aux.getRawButton(4)) {
			intake.setIntakeSpeed(intakeSpeed.get());
		}else if(aux.getRawButton(3)){
			intake.setIntakeSpeed(-intakeSpeed.get());
		}else {
			intake.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
