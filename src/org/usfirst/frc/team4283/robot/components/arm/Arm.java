package org.usfirst.frc.team4283.robot.components.arm;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.components.Component;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem implements Component {

	private TwoValvePneumatic left;
	private TwoValvePneumatic right;
	
	public Arm() {
		this.setName(Subsystems.ARM);
	}

	@Override
	public void robotInit() {
		left =  new TwoValvePneumatic(HardwareMap.Pneumatic.LEFT_INTAKE, 0, "Left Pneumatic");
		right = new TwoValvePneumatic(HardwareMap.Pneumatic.RIGHT_INTAKE, 0, "Right Pneumatic");;
	}

	@Override
	public void teleOpInit() {
		
	}

	@Override
	public void teleOpPeriodic() {
		
	}

	@Override
	public void autoInit() {
		
	}

	@Override
	public void autoPeriodic() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public TwoValvePneumatic getLeft() {
		return left;
	}
	
	public TwoValvePneumatic getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return "Arm";
	}

}