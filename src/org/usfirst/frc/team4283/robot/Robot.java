package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.drivetrain.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	/*
	 * Auto Choosing 
	 */
	private static final String middleAuto = "Middle";
	private static final String leftAuto = "Left";
	private static final String rightAuto = "Right";
	private String autoSelected;
	private SendableChooser<String> autoChooser = new SendableChooser<>();
	
	/*
	 * Drivetrain
	 */
	private Drivetrain drive;
	
	@Override
	public void robotInit() {
		autoChooser.addDefault("Default Auto", middleAuto);
		autoChooser.addObject("Left Auto", leftAuto);
		autoChooser.addObject("Right Auto", rightAuto);
		SmartDashboard.putData("Auto choices", autoChooser);
		
		drive = new Drivetrain();
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
