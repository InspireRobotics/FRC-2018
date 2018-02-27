package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;

import edu.wpi.first.wpilibj.command.Command;

public class RunIntake extends Command {
	
	private Intake intake = Components.INTAKE;
	
	public RunIntake() {
		this.requires(Components.INTAKE);
		this.setInterruptible(true);
	}
	
	@Override
	protected void initialize() {
	
	}
	
	@Override
	protected void execute() {
		intake.setIntakeSpeed(-1);
	}
	
	@Override
	protected boolean isFinished() {
		return intake.getCubeSensor() > Intake.getCubesensorlimit().get() || HardwareMap.Joysticks.AUX.getRawButton(4);
	}

}
