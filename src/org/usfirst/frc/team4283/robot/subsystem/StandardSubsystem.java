package org.usfirst.frc.team4283.robot.subsystem;

public interface StandardSubsystem {
	
	public void robotInit();
	public void teleOpInit();
	public void teleOpPeriodic();
	public void autoInit();
	public void autoPeriodic();
	public void disable();
	
}
