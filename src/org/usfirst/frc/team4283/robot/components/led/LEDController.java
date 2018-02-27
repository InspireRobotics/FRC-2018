package org.usfirst.frc.team4283.robot.components.led;

import org.usfirst.frc.team4283.robot.HardwareMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDController {

	private static LEDController instance;
//	private Spark spark = new Spark(HardwareMap.PWM.BLINKY);
	private double val;

	private LEDController() {

	}

	public void update() {
//		spark.set(val);
		SmartDashboard.putNumber("LED Value", val);
	}

	public static void init() {
		instance = new LEDController();
	}

	public static LEDController getInstance() {
		return instance;
	}

	public static void setColor(double val) {
		if (instance != null)
			getInstance().val = val;
	}

	public static class Colors {
		public static final double ORANGE = .65;
		public static final double BLACK = .99;
		public static final double RED = .61;
		public static final double BLUE = .87;
		public static final double WHITE = .93;
		public static final double YELLOW = .93;
		public static final double GREEN = .77;
		public static final double GRAY = .95;
	}

}
