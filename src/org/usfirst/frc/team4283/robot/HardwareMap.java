package org.usfirst.frc.team4283.robot;

import edu.wpi.first.wpilibj.Joystick;

public class HardwareMap {

	public static class PWM{
		public static final int DRIVE_FL = 0;
		public static final int DRIVE_BL = 1;
		public static final int DRIVE_FR = 2;
		public static final int DRIVE_BR = 3;
		public static final int INTAKE_LEFT = 8;
		public static final int INTAKE_RIGHT = 9;
		public static final int BLINKY = 6;
	}
	
	public static class CAN{
		public static final int WRIST = 2;
	}
	
	public static class Joysticks{
		public static final Joystick DRIVE = new Joystick(0);
		public static final Joystick AUX = new Joystick(1);
	}
	
	public static class Pneumatic{
		public static final int RIGHT_INTAKE = 0;
		public static final int LEFT_INTAKE = 1;
		public static final int BOTTOM_CLIMBER = 3;
		public static final int TOP_CLIMBER = 2;
	}
	
	public static class Analog{
		public static final int GYRO = 0;
		public static final int BALL_SENSOR = 1;
	}
	
	
}
