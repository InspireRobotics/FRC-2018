package org.usfirst.frc.team4283.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	
	private Compressor compressor;
	private Subsystems subs;
	
	@Override
	public void robotInit() {
		compressor.setClosedLoopControl(true);
		compressor.setName("Compressor");
		compressor.start();
	}
	
	@Override
	public void disabledInit() {
		subs.disable();
	}
	
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		subs.autoInit();
	}

	@Override
	public void autonomousPeriodic() {
		subs.autoPeriodic();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
		subs.teleOpInit();
	}
	
	@Override
	public void teleopPeriodic() {
		subs.teleOpPeriodic();
	}
}
