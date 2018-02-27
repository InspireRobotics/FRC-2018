package org.usfirst.frc.team4283.robot.components.drivetrain;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem implements Component {

	private DifferentialDrive drive;

	private Spark fr, br, fl, bl;

	private SpeedControllerGroup leftGroup, rightGroup;

	// Create the gyro and gyro variables
	private AnalogGyro gyro = new AnalogGyro(0);

	@Override
	public void robotInit() {
		// Create motor controller objects
		fr = new Spark(HardwareMap.PWM.DRIVE_FR);
		fl = new Spark(HardwareMap.PWM.DRIVE_FL);
		br = new Spark(HardwareMap.PWM.DRIVE_BR);
		bl = new Spark(HardwareMap.PWM.DRIVE_BL);
		
		// Create the side modules
		leftGroup = new SpeedControllerGroup(bl, fl);
		rightGroup = new SpeedControllerGroup(br, fr);

		// Init the the drive train
		drive = new DifferentialDrive(leftGroup, rightGroup);
		
		//Init the gyro
		gyro.calibrate();
		SmartDashboard.putData("Gyro", gyro);
		
		drive.setSafetyEnabled(false);
		this.setName(Subsystems.DRIVE);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	public void stop() {
		drive.tankDrive(0, 0);
	}
		
	
	@Override
	public void teleOpInit() {
		stop();
	}

	@Override
	public void teleOpPeriodic() {
		this.tankDrive(0, 0);
	}

	@Override
	public void autoPeriodic() {
		this.tankDrive(0, 0);
	}

	@Override
	public void disable() {
		stop();
	}

	@Override
	public void autoInit() {

	}
	
	public double getGyroValue() {
		return gyro.getAngle();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveManual());
	}
}

// Autonomous Code
/*
 * lift autoInit() leftAuto = fast rightAuto = fast autoUpdate(TRUE) wait3-5
 * seconds launch block
 */
