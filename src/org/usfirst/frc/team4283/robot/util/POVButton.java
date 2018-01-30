package org.usfirst.frc.team4283.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButton extends Button{

	public static final int TOP = 0, BOTTOM = 180, RIGHT = 90, LEFT = 270;
	
	private final Joystick joystick;
	private final int pos;

	public POVButton(Joystick joystick, int pos) {
		super();
		this.joystick = joystick;
		this.pos = pos;
	}

	@Override
	public boolean get() {
		return joystick.getPOV() == pos;
	}

}
