package org.usfirst.frc.team4283.robot.components.wrist;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.HardwareMap;
import org.usfirst.frc.team4283.robot.util.POVButton;
import org.usfirst.frc.team4283.robot.util.SmartDouble;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class WristManual extends Command {

	private static final SmartDouble wristSpeed = new SmartDouble(.55, "Wrist Speed");
	
	private final Joystick aux = HardwareMap.Joysticks.AUX;
	private final Wrist wrist = Components.WRIST;
	
	public WristManual() {
		this.requires(Components.WRIST);
		this.setInterruptible(true);
	}
	
	@Override
	public void execute() {
		if(aux.getPOV() == POVButton.TOP) {
			wrist.setWristSpeed(wristSpeed.get());
		}else if(aux.getPOV() == POVButton.BOTTOM){
			wrist.setWristSpeed(-wristSpeed.get());
		}else {
			wrist.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
