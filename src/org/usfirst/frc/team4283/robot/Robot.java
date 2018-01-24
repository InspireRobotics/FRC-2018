package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team4283.robot.intake.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	/*
	 * Subsystems
	 */
	private final boolean intakeEnabled = false;
	private final boolean blinkyEnabled = false;
	
	/*
	 * Auto Choosing 
	 */
	private static final String middleAuto = "Middle";
	private static final String leftAuto = "Left";
	private static final String rightAuto = "Right";
	private String autoSelected;
	private SendableChooser<String> autoChooser = new SendableChooser<>();
	
	/*
	 * Subsystems
	 */
	private Intake intake;
	private Drivetrain drive;
	
	@Override
	public void robotInit() {
		autoChooser.addDefault("Default Auto", middleAuto);
		autoChooser.addObject("Left Auto", leftAuto);
		autoChooser.addObject("Right Auto", rightAuto);
		SmartDashboard.putData("Auto choices", autoChooser);
		
		drive = new Drivetrain();
		
		if(intakeEnabled)
			intake = new Intake();
	}

	
	@Override
	public void autonomousInit() {
		autoSelected = autoChooser.getSelected();
		drive.autoInit();
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		drive.autoUpdate();
	}

	@Override
	public void teleopInit() {
		if(intakeEnabled)
			intake.updateTeleOp();
		drive.teleopInit();
	}
	
	@Override
	public void teleopPeriodic() {
		drive.updateTeleOp();
	}

	@Override
	public void testPeriodic() {
	}
}
