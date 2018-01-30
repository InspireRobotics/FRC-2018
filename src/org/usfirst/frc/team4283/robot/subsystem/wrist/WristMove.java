package org.usfirst.frc.team4283.robot.subsystem.wrist;

import org.usfirst.frc.team4283.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class WristMove extends Command {

	private final double speed;
	private final Wrist wrist = Subsystems.WRIST;
	
	public WristMove(double speed) {
		this.speed = speed;
	}
	
	@Override
	public synchronized void start() {
		wrist.setWristSpeed(speed * .5);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
