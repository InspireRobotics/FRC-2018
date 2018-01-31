package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.wrist.Wrist;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeManual extends Command {
	
	private static final SmartDouble intakeSpeed = new SmartDouble(.5, "Intake Speed");
	
	private final Joystick aux = HardwareMap.Joysticks.AUX;
	private final Wrist wrist = Components.WRIST;
	
	public IntakeManual() {
		this.setInterruptible(true);
		this.setSubsystem(Subsystems.INTAKE);
	}
	
	@Override
	protected void execute() {
		if(aux.getRawButton(4)) {
			wrist.setWristSpeed(intakeSpeed.get());
		}else if(aux.getRawButton(3)){
			wrist.setWristSpeed(-intakeSpeed.get());
		}else {
			wrist.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
