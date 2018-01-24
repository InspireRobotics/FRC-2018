package intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {

	private Joystick auxController = new Joystick(1);

	private Victor intakeLeft = new Victor(0);
	private Victor intakeRight = new Victor(0);
	private double maxSpeedRight = 1.0, maxSpeedLeft = 1.0;

	public void updateTeleOp() {
		double leftSpeed = -auxController.getRawAxis(1);
		double rightSpeed = -auxController.getRawAxis(5);

		// Get variables from the SmartDashboard
		maxSpeedRight = SmartDashboard.getNumber("Max Speed Right", 1);
		maxSpeedLeft = SmartDashboard.getNumber("Max Speed Left", 1);

		// Check the max speed
		if (rightSpeed > maxSpeedRight)
			rightSpeed = maxSpeedRight;
		else if (rightSpeed < -maxSpeedRight)
			rightSpeed = -maxSpeedRight;

		if (leftSpeed > maxSpeedLeft)
			leftSpeed = maxSpeedLeft;
		else if (leftSpeed < -maxSpeedLeft)
			leftSpeed = -maxSpeedLeft;

		// Set the intake
		intakeLeft.set(leftSpeed);
		intakeRight.set(rightSpeed);

		// Put variables on the Smart Dashboard
		SmartDashboard.putNumber("Max Speed Right", maxSpeedRight);
		SmartDashboard.putNumber("Max Speed Left", maxSpeedLeft);
	}

}