package org.usfirst.frc.team4283.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDouble {
	
	private double value = 1.0;
	private final String name;
	
	public SmartDouble(double value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	public double get(){
		return value;
	}
	
	public void update(){
		SmartDashboard.getNumber(name, value);
		SmartDashboard.putNumber(name, value);
	}
	
}
