package org.usfirst.frc.team4283.robot.components.drivetrain;

import org.usfirst.frc.team4283.robot.Components;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurn extends Command{

	private static final double GYRO_THRESHOLD = 1.5;
	
	private Drivetrain drivetrain = Components.DRIVE;
	
	private final double speed = .6;
	
	private double targetAngle;
	
	public AutoTurn(double targetAngle) {
		this.targetAngle = targetAngle;
	}
	
	@Override
	protected void initialize() {
		this.targetAngle = drivetrain.getGyroValue() + targetAngle;
		System.out.println("Target Angle: " + this.targetAngle);
	}
	
	@Override
	public synchronized void start() {
		
	}
	
	@Override
	protected void execute() {
		double error = targetAngle - drivetrain.getGyroValue(); 
		
//		System.out.println(String.format("Target: %f, Current: %f, Error: %f", targetAngle, this.drivetrain.getGyroValue(), error));
		
		double right = 0, left = 0;
		
		if(error < -GYRO_THRESHOLD){
			right = speed;
			left = -speed;
		}else if(error > GYRO_THRESHOLD){
			right = -speed;
			left = speed;
		}
		
		drivetrain.tankDrive(left, right);
	}
	
	@Override
	protected boolean isFinished() {
		double error = -(drivetrain.getGyroValue() - targetAngle); 
		return error < GYRO_THRESHOLD && error > -GYRO_THRESHOLD;
	}

}
