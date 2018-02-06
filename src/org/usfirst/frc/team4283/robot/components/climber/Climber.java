package org.usfirst.frc.team4283.robot.components.climber;

import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.components.Component;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem implements Component {
	
	private TwoValvePneumatic top;
	private TwoValvePneumatic bottom;
	
	public Climber() {
		
	}

	@Override
	public void robotInit() {
		top =  new TwoValvePneumatic(HardwareMap.Pneumatic.TOP_CLIMBER, 0, "Top Pneumatic");
		bottom = new TwoValvePneumatic(HardwareMap.Pneumatic.BOTTOM_CLIMBER, 0, "Bottom Pneumatic");;
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

	public TwoValvePneumatic getBottom() {
		return bottom;
	}
	
	public TwoValvePneumatic getTop() {
		return top;
	}
	
}
