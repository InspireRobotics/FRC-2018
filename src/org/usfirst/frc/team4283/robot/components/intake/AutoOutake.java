package org.usfirst.frc.team4283.robot.components.intake;

import org.usfirst.frc.team4283.robot.Components;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class AutoOutake extends TimedCommand {

	private static final Intake intake = Components.INTAKE;
	
	/**
	 * @param timeout in seconds
	 */
	public AutoOutake(double timeout) {
		super(timeout);

		this.requires(intake);
	}
	
	@Override
	protected void execute() {
		//Outtake
		intake.setIntakeSpeed(.5);
	}

}
