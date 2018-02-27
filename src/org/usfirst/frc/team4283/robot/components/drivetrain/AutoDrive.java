package org.usfirst.frc.team4283.robot.components.drivetrain;

import org.usfirst.frc.team4283.robot.Components;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class AutoDrive extends TimedCommand {
	
	private static final double GYRO_THRESHOLD = 1.6;
	private static final double ERR_MULTIPLIER = 0.0055;
	
	//Max error of 40
	private static final double MAX_ERROR = 20;
	
	private Drivetrain drivetrain = Components.DRIVE;
	
	private final double speed;
	private double startAngle;
	
	/**
	 * The timeout in seconds
	 */
	public AutoDrive(double timeout, double speed) {
		super(timeout);
		
		System.out.println(timeout);
		
		this.speed = speed;
		
		this.requires(drivetrain);
	}

	@Override
	protected void initialize() {
		this.startAngle = drivetrain.getGyroValue();
	}
	
	@Override
	protected void execute() {
		double left = speed;
		double right = speed;
		
		double currentAngle = drivetrain.getGyroValue();
		
		//Error is negative if we need go left, positive if we need to go right
		double error = -(startAngle - currentAngle);
		
//		System.out.println(String.format("Target: %f, Current: %f, Error: %f", startAngle, this.drivetrain.getGyroValue(), error));

		
		if(error > MAX_ERROR){
			error = MAX_ERROR;
		}else if(error < -MAX_ERROR){
			error = -MAX_ERROR;
		}
		
		if(error < -GYRO_THRESHOLD){
			right += error * ERR_MULTIPLIER;
		}else if(error > GYRO_THRESHOLD){
			left += error * ERR_MULTIPLIER;
		}
		
		drivetrain.tankDrive(left + .03, right);
	}

}
