package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.subsystem.StandardSubsystem;
import org.usfirst.frc.team4283.robot.subsystem.arm.Arm;
import org.usfirst.frc.team4283.robot.subsystem.drivetrain.Drivetrain;
import org.usfirst.frc.team4283.robot.subsystem.wrist.Wrist;

public class Subsystems implements StandardSubsystem{
	
	/*
	 * Subsystems
	 */
	
	public static final Drivetrain DRIVE = new Drivetrain();
	public static final Arm ARM = new Arm();
	public static final Wrist WRIST = new Wrist();

	/*
	 * List of Subsystems
	 */
	private static final StandardSubsystem[] subsystems = {DRIVE, ARM, WRIST};

	@Override
	public void robotInit() {
		for (StandardSubsystem s : subsystems) {
			s.robotInit();
		}
	}

	@Override
	public void teleOpInit() {
		for (StandardSubsystem s : subsystems) {
			s.teleOpInit();
		}
	}

	@Override
	public void teleOpPeriodic() {
		for (StandardSubsystem s : subsystems) {
			s.teleOpPeriodic();
		}
	}

	@Override
	public void autoInit() {
		for (StandardSubsystem s : subsystems) {
			s.autoInit();
		}
	}

	@Override
	public void autoPeriodic() {
		for (StandardSubsystem s : subsystems) {
			s.autoPeriodic();
		}
	}

	@Override
	public void disable() {
		for (StandardSubsystem s : subsystems) {
			s.disable();
		}
	}
	
	
}
