package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.components.arm.LowerArm;
import org.usfirst.frc.team4283.robot.components.arm.RaiseArm;
import org.usfirst.frc.team4283.robot.components.climber.LowerClimber;
import org.usfirst.frc.team4283.robot.components.climber.PullClimber;
import org.usfirst.frc.team4283.robot.components.climber.RaiseClimber;
import org.usfirst.frc.team4283.robot.components.intake.RunIntake;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The map of all the non-default controls. This maps 
 * button inputs to the necessary to commands
 * 
 * @author Noah
 *
 */
public class Controls {

	// Arm Buttons
	private static JoystickButton raiseArmButton, lowerArmButton, intakeButton;

	// Climbing Buttons
	private static JoystickButton raiseClimberButton, lowerClimberButton, climbButton;

	public static void init() {
		// Lower Arm
		lowerArmButton = new JoystickButton(HardwareMap.Joysticks.AUX, 1);
		lowerArmButton.toggleWhenPressed(new LowerArm(Components.ARM.getRight(), Components.ARM.getLeft()));

		// Raise Arm
		raiseArmButton = new JoystickButton(HardwareMap.Joysticks.AUX, 2);
		raiseArmButton.toggleWhenPressed(new RaiseArm(Components.ARM.getRight(), Components.ARM.getLeft()));

		//Intake Button
		intakeButton = new JoystickButton(HardwareMap.Joysticks.AUX, 3);
		intakeButton.toggleWhenPressed(new RunIntake());
		
		// Raise Climber
		raiseClimberButton = new JoystickButton(HardwareMap.Joysticks.DRIVE, 3);
		raiseClimberButton.toggleWhenPressed(new RaiseClimber(Components.CLIMB.getBottom(), Components.CLIMB.getTop()));
		
		//Climb
		climbButton = new JoystickButton(HardwareMap.Joysticks.DRIVE, 2);
		climbButton.toggleWhenPressed(new PullClimber(Components.CLIMB.getBottom()));
		
		//Lower All Climb
		lowerClimberButton = new JoystickButton(HardwareMap.Joysticks.DRIVE, 1);
		lowerClimberButton.toggleWhenPressed(new LowerClimber(Components.CLIMB.getTop(), Components.CLIMB.getBottom()));
	}

}
