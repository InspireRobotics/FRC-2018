package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.components.arm.LowerArm;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	// Arm Buttons
	private static JoystickButton raiseButton, lowerButton;

	public static void init() {
		// Lower Arm
		lowerButton = new JoystickButton(HardwareMap.Joysticks.AUX, 0);
		lowerButton.toggleWhenPressed(new LowerArm(Components.ARM.getRight(), Components.ARM.getLeft()));

		// Raise Arm
		raiseButton = new JoystickButton(HardwareMap.Joysticks.AUX, 1);
		raiseButton.toggleWhenPressed(new LowerArm(Components.ARM.getRight(), Components.ARM.getLeft()));
	}

}
