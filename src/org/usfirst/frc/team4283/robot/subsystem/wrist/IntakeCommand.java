package org.usfirst.frc.team4283.robot.subsystem.wrist;

import org.usfirst.frc.team4283.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {

	private final double direction;
	private final Wrist wrist = Subsystems.WRIST;
	
	public IntakeCommand(double direction) {
		this.direction = direction;
	}
	
	@Override
	public synchronized void start() {
		wrist.setIntakeSpeed(direction * .5);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
