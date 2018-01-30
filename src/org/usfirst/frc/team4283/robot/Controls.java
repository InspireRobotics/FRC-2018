package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.subsystem.arm.LowerArm;
import org.usfirst.frc.team4283.robot.subsystem.wrist.IntakeCommand;
import org.usfirst.frc.team4283.robot.subsystem.wrist.WristMove;
import org.usfirst.frc.team4283.robot.util.POVButton;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	// Arm Buttons
	private static JoystickButton raiseButton, lowerButton;

	// Intake Buttons
	private static JoystickButton intakeButton, outtakeButton;
	
	//Wrist Buttons
	private static POVButton wristUpButton, wristDownButton;

	public static void init() {
		// Lower Arm
		lowerButton = new JoystickButton(HardwareMap.Joysticks.AUX, 0);
		lowerButton.toggleWhenPressed(new LowerArm(Subsystems.ARM.getRight(), Subsystems.ARM.getLeft()));

		// Raise Arm
		raiseButton = new JoystickButton(HardwareMap.Joysticks.AUX, 1);
		raiseButton.toggleWhenPressed(new LowerArm(Subsystems.ARM.getRight(), Subsystems.ARM.getLeft()));

		// Intake
		intakeButton = new JoystickButton(HardwareMap.Joysticks.AUX, 3);
		intakeButton.whileHeld(new IntakeCommand(1));

		// Outtake
		outtakeButton = new JoystickButton(HardwareMap.Joysticks.AUX, 4);
		outtakeButton.whileHeld(new IntakeCommand(-1));
		
		//Wrist Up Button
		wristUpButton = new POVButton(HardwareMap.Joysticks.AUX, POVButton.TOP);
		wristUpButton.whileHeld(new WristMove(.1));
		 
		//Wrist Down Button
		wristUpButton = new POVButton(HardwareMap.Joysticks.AUX, POVButton.BOTTOM);
		wristUpButton.whileHeld(new WristMove(-.1));

	}

}
