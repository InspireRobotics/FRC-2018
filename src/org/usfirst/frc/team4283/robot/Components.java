package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.components.Component;
import org.usfirst.frc.team4283.robot.components.arm.Arm;
import org.usfirst.frc.team4283.robot.components.climber.Climber;
import org.usfirst.frc.team4283.robot.components.drivetrain.Drivetrain;
import org.usfirst.frc.team4283.robot.components.intake.Intake;

public class Components implements Component{
	
	/*
	 * Components
	 */
	
	public static final Drivetrain DRIVE = new Drivetrain();
	public static final Arm ARM = new Arm();
	public static final Climber CLIMB = new Climber();
	public static final Intake INTAKE = new Intake();
	
	/*
	 * List of Subsystems
	 */
	private static final Component[] components = {DRIVE, INTAKE, ARM, CLIMB};

	@Override
	public void robotInit() {
		for (Component s : components) {
			s.robotInit();
		}
	}

	@Override
	public void teleOpInit() {
		for (Component s : components) {
			s.teleOpInit();
		}
	}

	@Override
	public void teleOpPeriodic() {
		for (Component s : components) {
			s.teleOpPeriodic();
		}
	}

	@Override
	public void autoInit() {
		for (Component s : components) {
			s.autoInit();
		}
	}

	@Override
	public void autoPeriodic() {
		for (Component s : components) {
			s.autoPeriodic();
		}
	}

	@Override
	public void disable() {
		for (Component s : components) {
			s.disable();
		}
	}
	
	
}
